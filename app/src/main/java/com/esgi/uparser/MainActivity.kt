package com.esgi.uparser


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.esgi.uparser.api.Api
import com.esgi.uparser.models.LoginResponse
import com.esgi.uparser.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun login(view: View){
        val Button = findViewById<Button>(R.id.ButtonLogin)
        Button.setOnClickListener{
            val email = findViewById<EditText>(R.id.Login).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()


            if (email.isEmpty()){
                findViewById<EditText>(R.id.Login).error = "Email required"
                findViewById<EditText>(R.id.Login).requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                findViewById<EditText>(R.id.password).error = "Password required"
                findViewById<EditText>(R.id.password).requestFocus()
                return@setOnClickListener
            }

            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://10.0.2.2:3001/api/v1/").build()
            val jsonapi = retrofit.create(Api::class.java)
            val Logincall: Call<LoginResponse> = jsonapi.userLogin("julien@gmail.com","123ju")
            Logincall.enqueue(object : Callback<LoginResponse>
            {
                override fun onResponse(call: Call<LoginResponse>,response: Response<LoginResponse>)
                {
                    if(!response.body()?.error!!){

                    }else{
                        Toast.makeText(applicationContext,response.body()?.message, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                }


            })
        }



       /*if (email =="belmajkarim29@gmail.com" && password == "123456789"){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }else {
            Toast.makeText(this,"Invalid mail or password",Toast.LENGTH_SHORT).show()
        }*/
    }
}