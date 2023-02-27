package com.coimbatorepublicschool.persprudent

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val cvBody:CardView = findViewById(R.id.cv_body)
        cvBody.setBackgroundResource(R.drawable.stroke)
        val tvDontHaveAnAccount:TextView = findViewById(R.id.tv_dont_have_an_account)
        tvDontHaveAnAccount.setOnClickListener{
            var intent:Intent = Intent(applicationContext,ResetPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}