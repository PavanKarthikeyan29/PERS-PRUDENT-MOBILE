package com.coimbatorepublicschool.persprudent

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.cardview.widget.CardView

class HomeActivity : AppCompatActivity() {
    lateinit var cvLogout:CardView
    lateinit var cvSms:CardView
    lateinit var cvCall:CardView
    lateinit var cvProfile:CardView
    lateinit var cvDevice:CardView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        cvLogout = findViewById(R.id.cv_logout)
        cvSms = findViewById(R.id.cv_sms)
        cvCall = findViewById(R.id.cv_mobile)
        cvProfile = findViewById(R.id.cv_profile)
        cvDevice = findViewById(R.id.cv_device_info)


        cvLogout.setOnClickListener {
            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.remove("token")
            editor.commit()
            var intent:Intent = Intent(applicationContext,LoginActivity::class.java)
            startActivity(intent)
        }

        cvSms.setOnClickListener{
            startActivity(Intent(applicationContext,SMSConfigActivity::class.java))
        }

        cvCall.setOnClickListener{
            startActivity(Intent(applicationContext,MobileConfigActivity::class.java))
        }


        cvProfile.setOnClickListener{
            startActivity(Intent(applicationContext,ProfileInfoActivity::class.java))

        }

        cvDevice.setOnClickListener{
            startActivity(Intent(applicationContext,DeviceInfoActivity::class.java))

        }


    }
}