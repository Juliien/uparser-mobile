package com.esgi.uparser


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.esgi.uparser.api.user.model.LoginModel
import com.esgi.uparser.api.user.service.AuthenticationService
import com.google.gson.JsonObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val usernameInput = findViewById<EditText>(R.id.Login)
        val passwordInput = findViewById<EditText>(R.id.password)
        val submitButton = findViewById<Button>(R.id.ButtonLogin)

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
//                    this.startActivity(Intent(this, MainActivity::class.java))
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