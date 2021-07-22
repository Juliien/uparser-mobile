package com.esgi.uparser.catalog

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.esgi.uparser.R
import com.esgi.uparser.api.catalog.model.CodeResponse
import kotlinx.android.synthetic.main.activity_code_detail.*
import java.util.*

class CodeDetailActivity : AppCompatActivity() {

    private var codeValue: String? = null

    companion object {
        private val PARAM1: String = "codeValue"

        fun navigateTo(context: Context, param1: String) {
            val intent = Intent(context, CodeDetailActivity::class.java).apply {
                putExtra(PARAM1, param1)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_detail)

        val receivedCodeValue = intent?.getStringExtra("codeValue")
        Log.d("Uparser", "Success encoded $receivedCodeValue")
        val decodedCodeValue = android.util.Base64.decode(receivedCodeValue, android.util.Base64.DEFAULT)
        Log.d("Uparser", "Success decoded ${String(decodedCodeValue)}")
        codeContentPlaceHolder.text = String(decodedCodeValue)
    }
}