package com.coimbatorepublicschool.persprudent

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    lateinit var btLogout:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btLogout = findViewById(R.id.bt_logout)
        btLogout.setOnClickListener {
            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.remove("token")
            editor.commit()
            var intent:Intent = Intent(applicationContext,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}