package com.esgi.uparser.catalog

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.esgi.uparser.R
import kotlinx.android.synthetic.main.activity_code_detail.*

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
        val decodedCodeValue = android.util.Base64.decode(receivedCodeValue, android.util.Base64.DEFAULT)
        codeContentPlaceHolder.text = String(decodedCodeValue)
    }
}