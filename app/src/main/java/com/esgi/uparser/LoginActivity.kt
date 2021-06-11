package com.esgi.uparser

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.esgi.uparser.api.user.model.LoginModel
import com.esgi.uparser.api.user.service.AuthenticationService
import com.google.gson.JsonObject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_activity)
        val usernameInput = findViewById<EditText>(R.id.outlined_email)
        val passwordInput = findViewById<EditText>(R.id.outlined_password)
        val submitButton = findViewById<Button>(R.id.login_submit)

        submitButton.setOnClickListener {
            login(usernameInput, passwordInput)
        }
    }

    private fun login(usernameInput: EditText, passwordInput: EditText) {
        val username = usernameInput.text.toString()
        val password = passwordInput.text.toString()

        if (username.isNotEmpty() && password.isNotEmpty()
            && username.isNotBlank() && password.isNotBlank()) {

            val loginService = AuthenticationService()
            var response: JsonObject?

            loginService.login(LoginModel(username, password)) { loginResponse ->
                response = loginResponse
                if (response !== null) {
//                    AppPreferences.isLogin = true
//                    AppPreferences.token = response?.get("token").toString()
                    this.startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Login Failed ! ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}