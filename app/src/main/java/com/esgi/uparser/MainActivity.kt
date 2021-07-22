package com.esgi.uparser

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.esgi.uparser.api.provider.AppPreferences
import com.esgi.uparser.api.session.service.SessionService


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppPreferences.init(this)
        setContentView(R.layout.activity_main)

        val sessionService = SessionService();
        sessionService.testUserToken(AppPreferences.token)

    }

    companion object {
        fun navigateTo(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}