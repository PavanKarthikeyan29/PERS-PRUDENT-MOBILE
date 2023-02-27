package com.coimbatorepublicschool.persprudent

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException

class LoginActivity : AppCompatActivity() {
    lateinit var edUsername:EditText
    lateinit var edPassword:EditText
    lateinit var btLogin:Button
    lateinit var cvBody:CardView
    lateinit var tvError:TextView

    val client = OkHttpClient()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        cvBody  = findViewById(R.id.cv_body)
        edUsername = findViewById(R.id.ed_username)
        edPassword = findViewById(R.id.ed_password)
        btLogin = findViewById(R.id.bt_login)
        tvError = findViewById(R.id.tv_error_1)
        cvBody.setBackgroundResource(R.drawable.stroke)
        btLogin.setOnClickListener {
            login(edUsername.text.toString(),edPassword.text.toString())
        }
    }

    override fun onBackPressed() {
        return
    }
    fun login(username:String, password:String){
        var jsonObject: JSONObject = JSONObject();
        jsonObject.put("username",username)
        jsonObject.put("password",password)
        val request = Request.Builder()
            .url(Constants.URL+"api/user/login")
            .method("POST", RequestBody.create("application/json".toMediaTypeOrNull(),jsonObject.toString()))
            .addHeader("Content-Type","application/json")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("error --> "+e)
            }
            @SuppressLint("CommitPrefEdits")
            override fun onResponse(call: Call, response: Response) {
                if (!response.code.toString().equals("401")) {
                    val responseJson:JSONObject = JSONObject(response.body?.string());
                    if (responseJson.getString("default_password").equals("true")){
                        val resetPasswordIntent:Intent = Intent(applicationContext,ResetPasswordActivity::class.java)
                        val bundle = Bundle()
                        bundle.putString("token", responseJson.getString("token"))
                        resetPasswordIntent.putExtras(bundle)
                        startActivity(resetPasswordIntent)
                    }else{
                        var intent:Intent = Intent(applicationContext,HomeActivity::class.java)
                        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
                        var editor = sharedPreference.edit()
                        editor.putString("token",responseJson.getString("token"))
                        editor.commit()
                        startActivity(intent)
                    }
                } else {
                    val responseJson:JSONObject = JSONObject(response.body?.string());
                    runOnUiThread {
                        tvError.text = ""

                        if (responseJson.getString("status").equals("UNAUTHORIZED")){
                            tvError.text = "Incorrect username or password!"
                        }
                    }

                }
            }})
}}