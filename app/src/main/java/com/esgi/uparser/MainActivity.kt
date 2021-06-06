package com.esgi.uparser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun login(view: View){
        val email = findViewById<EditText>(R.id.Login).text.toString()
        val password = findViewById<EditText>(R.id.password).text.toString()


        if (email =="belmajkarim29@gmail.com" && password == "123456789"){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }else {
            Toast.makeText(this,"Invalid mail or password",Toast.LENGTH_SHORT).show()
        }
    }
}