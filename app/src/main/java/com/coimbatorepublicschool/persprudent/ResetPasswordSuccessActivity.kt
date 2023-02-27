package com.coimbatorepublicschool.persprudent

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button

class ResetPasswordSuccessActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password_success)
        val btGoToLogin:Button = findViewById(R.id.bt_go_to_login)
        btGoToLogin.setOnClickListener {
            val intent:Intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}