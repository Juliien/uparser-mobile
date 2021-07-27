package com.esgi.uparser.catalog

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.esgi.uparser.R
import com.esgi.uparser.api.catalog.service.CatalogService
import com.esgi.uparser.api.catalog.model.CodeResponse
import com.esgi.uparser.api.code.service.CodeService
import com.esgi.uparser.api.provider.AppPreferences
import com.esgi.uparser.api.session.service.SessionService
import kotlinx.android.synthetic.main.activity_catalog_detail.*
import kotlinx.android.synthetic.main.activity_catalog_detail.loader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatalogDetailActivity: AppCompatActivity(){

    private var code: CodeResponse? = null

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

        if (isNetworkConnected()) {
            if (receivedCodeId != null) {
                CatalogService().getCodeById(receivedCodeId, object : Callback<CodeResponse>{
                    override fun onResponse(
                        call: Call<CodeResponse>,
                        response: Response<CodeResponse>
                    ) {
                        loader?.visibility = View.GONE
                        if (response.body() != null) {
                            code = response.body()
                            extensionStartValue?.text = code?.extensionStart
                            extensionEndValue?.text = code?.extensionEnd
                            languageValue?.text = code?.language
                            creatorValue?.text = "Anonymous"
                            if (code?.codeEncoded != null) {
                                seeCodeButton.setOnClickListener {
                                    CodeDetailActivity.navigateTo(this@CatalogDetailActivity, code!!.codeEncoded)
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<CodeResponse>, t: Throwable) {
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
}