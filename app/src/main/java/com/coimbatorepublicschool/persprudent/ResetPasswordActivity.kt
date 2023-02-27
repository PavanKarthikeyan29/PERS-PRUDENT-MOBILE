package com.coimbatorepublicschool.persprudent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView

class ResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        var button:Button = findViewById(R.id.button)
        val cvBody:CardView = findViewById(R.id.cv_body)
        cvBody.setBackgroundResource(R.drawable.stroke)
        button.setOnClickListener {
           var intent:Intent = Intent(applicationContext,ResetPasswordSuccessActivity::class.java)
            startActivity(intent)
        }
    }
}