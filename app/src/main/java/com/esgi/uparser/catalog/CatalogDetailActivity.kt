package com.esgi.uparser.catalog

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
import com.esgi.uparser.R
import com.esgi.uparser.api.catalog.service.CatalogService
import com.esgi.uparser.api.catalog.model.CodeResponse
import com.esgi.uparser.api.code.service.CodeService
import com.esgi.uparser.api.profile.service.ProfileService
import com.esgi.uparser.api.provider.AppPreferences
import com.esgi.uparser.api.session.service.SessionService
import kotlinx.android.synthetic.main.activity_catalog_detail.*
import kotlinx.android.synthetic.main.activity_catalog_detail.loader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatalogDetailActivity: AppCompatActivity(), CatalogViewHolder.OnCatalogClickedListener {

    private var code: CodeResponse? = null
    private var history: List<CodeResponse>? = null

    companion object {
        private val PARAM1: String = "codeId"

        fun navigateTo(context: Context, param1: String) {
            val intent = Intent(context, CatalogDetailActivity::class.java).apply {
                putExtra(PARAM1, param1)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog_detail)

        val sessionService = SessionService();
        sessionService.testUserToken(AppPreferences.token)

        val receivedCodeId = intent?.getStringExtra("codeId")
        loader?.visibility = View.VISIBLE
//        scrollView?.visibility = View.GONE
        noHistory?.visibility = View.GONE

        if (isNetworkConnected()) {
            if (receivedCodeId != null) {
                if(AppPreferences.token == "" || AppPreferences.email == "") {
                    noHistory?.visibility = View.VISIBLE
                } else {
                    CodeService().getCodeHistory(AppPreferences.token, receivedCodeId, object : Callback<List<CodeResponse>> {
                        override fun onResponse(
                            call: Call<List<CodeResponse>>,
                            response: Response<List<CodeResponse>>
                        ) {
                            loader?.visibility = View.GONE
                            Log.d("Uparser", "Success history ${response}")
                            history = response.body()
                            Log.d("Uparser", "Success history body ${response.body()}")
                            historyRecyclerView.apply {
                                layoutManager = LinearLayoutManager(this@CatalogDetailActivity)
                                adapter = history?.let { CatalogAdapter(it, this@CatalogDetailActivity) }
                            }
                        }

                        override fun onFailure(call: Call<List<CodeResponse>>, t: Throwable) {
                            Log.d("Uparser", "Fail $call |||||||| $t")
                            noHistory?.visibility = View.VISIBLE
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

                CatalogService().getCodeById(receivedCodeId, object : Callback<CodeResponse>{
                    override fun onResponse(
                        call: Call<CodeResponse>,
                        response: Response<CodeResponse>
                    ) {
                        loader?.visibility = View.GONE
                        Log.d("Uparser", "Success code ${response}")
                        if (response.body() != null) {
                            code = response.body()
                            extensionStartValue?.text = code?.extensionStart
                            extensionEndValue?.text = code?.extensionEnd
                            languageValue?.text = code?.language
                            creatorValue?.text = "Anonymous"
                            if (code?.codeEncoded != null) {
                                Log.d("Uparser", "code?.codeEncoded != null ${code!!.codeEncoded}")
                                seeCodeButton.setOnClickListener {
                                    CodeDetailActivity.navigateTo(this@CatalogDetailActivity, code!!.codeEncoded)
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<CodeResponse>, t: Throwable) {
                        Log.d("Uparser", "Fail $call |||||||| $t")
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
        } else {
            loader?.visibility = View.GONE
            val toast =
                Toast.makeText(applicationContext, "No internet connection", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            toast.show()
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

    override fun onCatalogClicked(code: CodeResponse?) {
        if (code != null) {
            navigateTo(this, code.id)
        }
    }
}