package com.coimbatorepublicschool.persprudent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import java.io.IOException
import kotlin.reflect.typeOf


class MainActivity : AppCompatActivity() {
    var client = okhttp3.OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var imLogo: ImageView = findViewById(R.id.im_logo)
        val aniFade: Animation =
            AnimationUtils.loadAnimation(applicationContext, android.R.anim.fade_out)
        val tvQuote: TextView = findViewById(R.id.tv_quote)
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val token = sharedPreference.getString("token", "defaultName")
        Handler().postDelayed(Runnable { tvQuote.setText("〝 ") }, 300)
        Handler().postDelayed(Runnable { tvQuote.append("S") }, 400)
        Handler().postDelayed(Runnable { tvQuote.append("a") }, 500)
        Handler().postDelayed(Runnable { tvQuote.append("v") }, 600)
        Handler().postDelayed(Runnable { tvQuote.append("e") }, 700)
        Handler().postDelayed(Runnable { tvQuote.append("s") }, 800)
        Handler().postDelayed(Runnable { tvQuote.append(" ") }, 900)
        Handler().postDelayed(Runnable { tvQuote.append("t") }, 1000)
        Handler().postDelayed(Runnable { tvQuote.append("h") }, 1100)
        Handler().postDelayed(Runnable { tvQuote.append("e") }, 1200)
        Handler().postDelayed(Runnable { tvQuote.append(" ") }, 1300)
        Handler().postDelayed(Runnable { tvQuote.append("l") }, 1400)
        Handler().postDelayed(Runnable { tvQuote.append("i") }, 1500)
        Handler().postDelayed(Runnable { tvQuote.append("f") }, 1600)
        Handler().postDelayed(Runnable { tvQuote.append("e") }, 1700)
        Handler().postDelayed(Runnable { tvQuote.append("t") }, 1800)
        Handler().postDelayed(Runnable { tvQuote.append("i") }, 1900)
        Handler().postDelayed(Runnable { tvQuote.append("m") }, 2000)
        Handler().postDelayed(Runnable { tvQuote.append("e") }, 2100)
        Handler().postDelayed(Runnable { tvQuote.append("\n") }, 2200)
        Handler().postDelayed(Runnable { tvQuote.append("        ") }, 2300)
        Handler().postDelayed(Runnable { tvQuote.append("a") }, 2400)
        Handler().postDelayed(Runnable { tvQuote.append("t") }, 2500)
        Handler().postDelayed(Runnable { tvQuote.append(" ") }, 2600)
        Handler().postDelayed(Runnable { tvQuote.append("t") }, 2700)
        Handler().postDelayed(Runnable { tvQuote.append("h") }, 2800)
        Handler().postDelayed(Runnable { tvQuote.append("e") }, 2900)
        Handler().postDelayed(Runnable { tvQuote.append(" ") }, 3100)
        Handler().postDelayed(Runnable { tvQuote.append("r") }, 3200)
        Handler().postDelayed(Runnable { tvQuote.append("i") }, 3300)
        Handler().postDelayed(Runnable { tvQuote.append("g") }, 3400)
        Handler().postDelayed(Runnable { tvQuote.append("h") }, 3500)
        Handler().postDelayed(Runnable { tvQuote.append("t") }, 3600)
        Handler().postDelayed(Runnable { tvQuote.append(" ") }, 3700)
        Handler().postDelayed(Runnable { tvQuote.append("t") }, 3800)
        Handler().postDelayed(Runnable { tvQuote.append("i") }, 3900)
        Handler().postDelayed(Runnable { tvQuote.append("m") }, 4000)
        Handler().postDelayed(Runnable { tvQuote.append("e") }, 4100)
        Handler().postDelayed(Runnable { tvQuote.append(" 〞") }, 4100)
        Handler().postDelayed(Runnable {
            verify(token.toString())

        }, 5500)
    }

    fun verify(token: String){
        val body = RequestBody.create(null, byteArrayOf())

        val request = Request.Builder()
            .url(Constants.URL + "api/user/verify")
            .method("POST", body)
            .addHeader("token",token)
            .build()

        val call = client.newCall(request)
        call.enqueue(
            object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    // when failure
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.code.equals(200)){
                        val intent: Intent = Intent(applicationContext, HomeActivity::class.java)
                        startActivity(intent)
                    }else{
                        val intent: Intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        )
    }
}