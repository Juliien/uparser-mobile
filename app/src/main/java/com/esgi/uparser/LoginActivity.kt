package com.esgi.uparser

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.esgi.uparser.api.authentication.model.LoginModel
import com.esgi.uparser.api.authentication.model.TokenResponse
import com.esgi.uparser.api.authentication.service.AuthenticationService
import com.esgi.uparser.api.provider.AppPreferences
import com.esgi.uparser.api.session.service.SessionService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    var isResultValid: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailInput = findViewById<EditText>(R.id.outlined_email)
        val passwordInput = findViewById<EditText>(R.id.outlined_password)
        val submitButton = findViewById<Button>(R.id.login_submit)

        submitButton.setOnClickListener {
            val result = login(emailInput, passwordInput)
        }
    }
    private fun login(emailInput: EditText, passwordInput: EditText): Boolean{
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()
            && email.isNotBlank() && password.isNotBlank()) {
            val loginService = AuthenticationService()

            loginService.login(LoginModel(email = email, password = password), object :
                Callback<TokenResponse> {
                override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                    if (response.body()?.token != null) {
                        AppPreferences.token = response.body()?.token!!
                        val tokenResponse = SessionService().retrieveUserInfoViaToken(AppPreferences.token);
                        AppPreferences.email = tokenResponse.email;
                        AppPreferences.id = tokenResponse.userId;
                        isResultValid = true
                        UserDetailActivity.navigateTo(this@LoginActivity)
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Login Failed ! ",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<TokenResponse>, t: Throwable) {

                    Toast.makeText(
                        applicationContext,
                        "Server error ! ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        } else {
            Toast.makeText(
                applicationContext,
                "Missing value in fields ! ",
                Toast.LENGTH_LONG
            ).show()
        }
        return isResultValid
    }

    companion object {
        fun navigateTo(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}