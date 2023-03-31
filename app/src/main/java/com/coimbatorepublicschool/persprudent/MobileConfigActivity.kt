package com.coimbatorepublicschool.persprudent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MobileConfigActivity : AppCompatActivity() {
    var list1: List<String> = ArrayList()
    lateinit var layout: LinearLayout
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var LimitWarning:TextView
    var client = okhttp3.OkHttpClient()
    lateinit var token:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_config)
        layout = findViewById<LinearLayout>(R.id.container)
        floatingActionButton = findViewById(R.id.floatingActionButton)
        LimitWarning = findViewById(R.id.tv_error_1)
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val lToken = sharedPreference.getString("token", "defaultName")
        token = lToken.toString()
        setAllNumbers()
        floatingActionButton.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Add Progress")
            val input = EditText(this)
            input.setHint("Enter Mobile number")
            input.inputType = InputType.TYPE_CLASS_NUMBER
            input.filters = arrayOf<InputFilter>(LengthFilter(10))
            input.maxLines = 1
            builder.setView(input)
            builder.setPositiveButton("Save", DialogInterface.OnClickListener { dialog, which ->
                var m_Text = input.text.toString()
                addCard(m_Text)
                addNumber(m_Text)
            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

            builder.show()
        }
    }
    fun deleteNumber(mobile:String){
        var json:JSONObject = JSONObject()
        json.put("mobile",mobile)
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = json.toString().toRequestBody(mediaType)
        val request = Request.Builder()
            .url(Constants.URL + "api/user/delete/call")
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
                    println(response.code)
                    println(response.body?.string())
                }
            }
        )
    }
    fun addNumber(mobile:String){
        var json:JSONObject = JSONObject()
        json.put("mobile",mobile)
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = json.toString().toRequestBody(mediaType)
        val request = Request.Builder()
            .url(Constants.URL + "api/user/add/call")
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
                    println(response.code)
                    println(response.body?.string())
                }
            }
        )
    }

    fun setAllNumbers(){
        val body = RequestBody.create(null, byteArrayOf())

        val request = Request.Builder()
            .url(Constants.URL + "api/user/get/call")
            .addHeader("token",token)
            .build()

        val call = client.newCall(request)
        call.enqueue(
            object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }
                override fun onResponse(call: Call, response: Response) {
                    if (response.code.equals(200)){
                        val responseJson: JSONArray = JSONArray(response.body?.string());
                        val listdata = ArrayList<String>()
                        if (responseJson != null) {
                            for (i in 0 until responseJson.length()) {
                                listdata.add(responseJson.getString(i))
                            }
                        }
                        runOnUiThread {
                            for (i in listdata){
                                println(i)
                                addCard(i)
                            }
                        }

                    }else{
                        println("error")
                    }
                }
            }
        )
    }

    @SuppressLint("MissingInflatedId")
    private fun addCard(name: String) {
        val view: View = layoutInflater.inflate(R.layout.task_layout, null)
        val nameView = view.findViewById<TextView>(R.id.tv_number)
        val delete = view.findViewById<ImageView>(R.id.delete)

        delete.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Delete Progress")
            builder.setPositiveButton("Delete", DialogInterface.OnClickListener { dialog, which ->
                layout.removeView(view)
                deleteNumber(nameView.text.toString())
                checkCountDisable()
            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

            builder.show()
        }

        nameView.text = name
        list1.toMutableList().add(name)
        layout.addView(view)
        checkCountDisable()
    }
    fun checkCountDisable() {
        println("Count ---> " + layout.childCount)
        if (layout.childCount >= 3) {
            LimitWarning.setText("Limit Reached. Mobile number can be added upto 3")
            LimitWarning.setVisibility(View.VISIBLE)
            floatingActionButton.setEnabled(false)
        } else {
            LimitWarning.setText("")
            LimitWarning.setVisibility(View.INVISIBLE)
            floatingActionButton.setEnabled(true)
        }
    }
}