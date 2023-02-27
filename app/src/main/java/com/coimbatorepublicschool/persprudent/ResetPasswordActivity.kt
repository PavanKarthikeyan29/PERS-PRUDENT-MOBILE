package com.coimbatorepublicschool.persprudent

import android.annotation.SuppressLint
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

class ResetPasswordActivity : AppCompatActivity() {
    lateinit var edPassword:EditText
    lateinit var edConfirmPassword:EditText
    lateinit var btReset:Button
    lateinit var cvBody:CardView
    lateinit var token:String
    lateinit var tvError:TextView
    val client = okhttp3.OkHttpClient()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        cvBody = findViewById(R.id.cv_body)
        edPassword = findViewById(R.id.ed_password)
        tvError = findViewById(R.id.tv_error_1)
        edConfirmPassword = findViewById(R.id.ed_confirm_password)
        btReset = findViewById(R.id.bt_reset)
        cvBody.setBackgroundResource(R.drawable.stroke)
        val bundle = intent.extras
        val tokenLocal = bundle!!.getString("token", "Default")
        token = tokenLocal

        btReset.setOnClickListener {
            if (edPassword.text.toString().equals(edConfirmPassword.text.toString())){
                if (edPassword.text.toString().length > 8){
                    resetPassword(edConfirmPassword.text.toString(),token)
                }else{
                    tvError.text = "Minimum 10 characters needed! "
                }
            }else{
                tvError.text = "Passwords are not matching!"
            }
        }

    }
    fun resetPassword(password:String,token:String){
        var jsonObject: JSONObject = JSONObject();
        jsonObject.put("password",password)
        val request = Request.Builder()
            .url(Constants.URL+"api/user/reset/password")
            .method("POST", RequestBody.create("application/json".toMediaTypeOrNull(),jsonObject.toString()))
            .addHeader("Content-Type","application/json")
            .addHeader("token",token)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("error --> "+e)
            }
            @SuppressLint("CommitPrefEdits")
            override fun onResponse(call: Call, response: Response) {
                println(response.body?.string())
                println(token)
                if (!response.code.toString().equals("401")) {
                    var intent:Intent = Intent(applicationContext,ResetPasswordSuccessActivity::class.java)
                    startActivity(intent)
                }
            }})
    }
}