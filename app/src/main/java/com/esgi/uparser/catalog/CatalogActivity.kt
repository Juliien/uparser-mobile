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
import com.esgi.uparser.api.provider.AppPreferences
import kotlinx.android.synthetic.main.activity_catalog_recycler.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatalogActivity : AppCompatActivity(), CatalogViewHolder.OnCatalogClickedListener {

    private var codes: List<CodeResponse>? = null

    companion object {
        fun navigateTo(context: Context) {
            context.startActivity(Intent(context, CatalogActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog_recycler)

        AppPreferences.clear("token")
        loader?.visibility = View.VISIBLE
        noContent?.visibility = View.GONE

        if (isNetworkConnected()) {
            CatalogService().getAllCodes(object : Callback<List<CodeResponse>> {
                override fun onResponse(
                    call: Call<List<CodeResponse>>,
                    response: Response<List<CodeResponse>>
                ) {
                    loader?.visibility = View.GONE
                    codes = response.body()

                    if (codes?.size == 0) {
                        catalogRecyclerView?.visibility = View.GONE
                        noContent?.visibility = View.VISIBLE
                    } else {
                        catalogRecyclerView.apply {
                            layoutManager = LinearLayoutManager(this@CatalogActivity)
                            adapter = codes?.let { CatalogAdapter(it, this@CatalogActivity) }
                        }
                    }
                }

                override fun onFailure(call: Call<List<CodeResponse>>, t: Throwable) {
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

    override fun onCatalogClicked(code: CodeResponse?) {
        if (code != null) {
            CatalogDetailActivity.navigateTo(this, code.id)
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