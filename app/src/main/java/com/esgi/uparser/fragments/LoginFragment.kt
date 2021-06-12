package com.esgi.uparser.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.esgi.uparser.MainActivity
import com.esgi.uparser.R
import com.esgi.uparser.api.provider.AppPreferences
import com.esgi.uparser.api.user.model.LoginModel
import com.esgi.uparser.api.user.service.AuthenticationService
import com.google.gson.JsonObject

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppPreferences.init(view.context)

        val usernameInput = view.findViewById<EditText>(R.id.outlined_email)
        val passwordInput = view.findViewById<EditText>(R.id.outlined_password)
        val submitButton = view.findViewById<Button>(R.id.login_submit)

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
                    AppPreferences.isLogin = true
                    AppPreferences.token = response?.get("token").toString()
                    this.startActivity(Intent(context, MainActivity::class.java))
                } else {
                    Toast.makeText(
                        context,
                        "Login Failed ! ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}