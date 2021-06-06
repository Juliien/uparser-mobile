package com.esgi.uparser

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.esgi.uparser.api.Api
import com.esgi.uparser.models.User
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://10.0.2.2:3001/api/v1/").build()
        val jsonapi = retrofit.create(Api::class.java)
        val mcall: Call<List<User>> = jsonapi.getInfoUser("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWxpZW5AZ21haWwuY29tIiwicm9sZXMiOlt7ImlkIjoiNjA5ODAxMDQ0NGViNjgxZTgwNDE4MTkyIiwicm9sZSI6IlVTRVIifV0sImlhdCI6MTYyMjkxODY5MSwiZXhwIjoxNjIyOTIyMjkxfQ.xi67lUmSgtxACqFlxGATc7JIj1z7_yXzCVsSGE3-8sVhp1Ns8T3K3xzxYbqhKPfYijU-4AJ5e438mXAf72a2Gg")

        mcall.enqueue(object : Callback<List<User>>
        {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("Error ",t.message.toString())
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                /*val mmodel:List<User> = response.body()!!
                val stringBuilder = StringBuilder()
                for (i in mmodel){
                    stringBuilder.append(i.id)
                    /*stringBuilder.append("\n")
                    stringBuilder.append(i.firstName)
                    stringBuilder.append("\n")
                    stringBuilder.append(i.lastName)
                    stringBuilder.append("\n")
                    stringBuilder.append(i.email)
                    stringBuilder.append("\n")
                    stringBuilder.append(i.Date)
                    stringBuilder.append("\n")
                    stringBuilder.append(i.createDate)
                    stringBuilder.append("\n")
                    stringBuilder.append(i.lastLoginDate)*/

                }*/

                val stringBuilder = response.body().toString()
                val textView: TextView = findViewById(R.id.personalinfos) as TextView
                textView.text = stringBuilder


                /*findViewById(R.id.personalinfos).setText(stringBuilder)*/


            }




        }
        )
        title = "Home Page"
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab().setText("History"))
        tabLayout.addTab(tabLayout.newTab().setText("Personal Info"))
        tabLayout.tabGravity= TabLayout.GRAVITY_FILL
        val adapter = MyAdapter(this, supportFragmentManager,
            tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}