package com.esgi.uparser

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.esgi.uparser.api.code.model.CodeHistoryResponse
import com.esgi.uparser.api.code.service.CodeService
import com.esgi.uparser.api.profile.model.UserResponse
import com.esgi.uparser.api.profile.service.ProfileService
import com.esgi.uparser.api.provider.AppPreferences
import com.esgi.uparser.api.session.service.SessionService
import com.esgi.uparser.catalog.CodeDetailActivity
import com.esgi.uparser.code.CodeAdapter
import com.esgi.uparser.code.CodeViewHolder
import kotlinx.android.synthetic.main.activity_user_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailActivity : AppCompatActivity(), CodeViewHolder.OnCodeClickedListener {

    private var userResponse: UserResponse? = null
    private var codes: List<CodeHistoryResponse>? = null
    private var shouldLogin: Boolean = false

    companion object {
        fun navigateTo(context: Context) {
            context.startActivity(Intent(context, UserDetailActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        loader?.visibility = View.VISIBLE
        noCodes?.visibility = View.GONE

        titleTextView?.text = "User detail page"
        codeListTitleTextView?.text = "User codes list"

        SessionService().testUserToken(AppPreferences.token)

        logOutButton.setOnClickListener{
            SessionService().disconnect(this);
        }

        if(AppPreferences.token == "") {
            LoginActivity.navigateTo(this)
        } else {
            if (isNetworkConnected()) {
                ProfileService().getUserByEmail(AppPreferences.token, AppPreferences.email, object :
                    Callback<UserResponse> {
                    override fun onResponse(
                        call: Call<UserResponse>,
                        response: Response<UserResponse>
                    ) {
                        loader?.visibility = View.GONE
                        if (response.body() != null) {
                            userResponse = response.body()
                            setUserResponseData()
                        } else {
                            shouldLogin = true
                        }
                    }
                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        shouldLogin = true
                    }
                })
                CodeService().getCodeHistory(AppPreferences.token, AppPreferences.id,object : Callback<List<CodeHistoryResponse>> {
                    override fun onResponse(
                        call: Call<List<CodeHistoryResponse>>,
                        response: Response<List<CodeHistoryResponse>>
                    ) {
                        Log.d("user", "${response.body()?.size}")
                        codes = response.body()
//                        getPossessCodeFromList(response.body())
                        loader?.visibility = View.GONE

                        if (codes?.size == 0) {
                            codeOfUserRecyclerView?.visibility = View.GONE
                            noCodes?.visibility = View.VISIBLE
                        } else {
                            codeOfUserRecyclerView.apply {
                                layoutManager = LinearLayoutManager(this@UserDetailActivity)
                                adapter = codes?.let { CodeAdapter(it, this@UserDetailActivity) }
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<CodeHistoryResponse>>, t: Throwable) {
                        loader?.visibility = View.GONE
                        val toast = Toast.makeText(
                            applicationContext,
                            "Error while loading list content",
                            Toast.LENGTH_SHORT
                        )
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                        toast.show()
                    }
                })

            }
        }
    }

    fun setUserResponseData() {
        if (userResponse != null && !shouldLogin) {
            UserFirstName?.text = userResponse?.firstName
            UserLastName?.text = userResponse?.lastName
            UserEmail?.text = userResponse?.email
        } else {
            LoginActivity.navigateTo(this)
        }
    }

    override fun onCodeClicked(code: CodeHistoryResponse?) {
        if (code != null) {
            CodeDetailActivity.navigateTo(this, code.codeEncoded)
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            true
            // TODO("VERSION.SDK_INT < M")
        }
    }

//    private fun getPossessCodeFromList(codes: List<CodeResponse>?) {
//        if (codes != null) {
//            this.codes = codes.filter { x: CodeResponse? -> x?.userId == userResponse?.id }
//        }
//    }
}