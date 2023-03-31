package com.coimbatorepublicschool.persprudent

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class ProfileInfoActivity : AppCompatActivity() {
    lateinit var token:String
    lateinit var view: View
    lateinit var layout: LinearLayout
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_info)
        layout = findViewById(R.id.container2)

        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val lToken = sharedPreference.getString("token", "defaultName")
        token = lToken.toString()
        setData()
    }
    fun setData(){
        val request = Request.Builder()
            .url(Constants.URL + "api/user/get/token")
            .addHeader("token",token)
            .build()

        val call = client.newCall(request)
        call.enqueue(
            object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }
                override fun onResponse(call: Call, response: Response) {
                    if (response.code.equals(200)){
                        var jsonObject:JSONObject = JSONObject(response.body?.string())
                        runOnUiThread{
                            addCard("First Name",jsonObject.getString("firstName"))
                            addCard("Last Name",jsonObject.getString("lastName"))
                            addCard("Address",jsonObject.getString("address"))
                            addCard("Email",jsonObject.getString("email"))
                            addCard("Mobile number",jsonObject.getString("mobileNumber"))
                            addCard("Username",jsonObject.getString("uuid"))
                        }



                    }else{
                        println("error")
                    }
                }
            }
        )
    }
    private fun addCard(param: String, value: String) {
        view = layoutInflater.inflate(R.layout.device_info_layout, null)
        val paramView: TextView = view.findViewById(R.id.param)
        val valueView: TextView = view.findViewById(R.id.value)
        paramView.text = param
        valueView.text = value
        layout.addView(view)
    }
}