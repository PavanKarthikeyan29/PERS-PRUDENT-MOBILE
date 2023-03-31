package com.coimbatorepublicschool.persprudent

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children


class DeviceInfoActivity : AppCompatActivity() {
    lateinit var view: View
    lateinit var layout:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_info)
        layout = findViewById(R.id.container1)
        addCard("Device Version", "1.0.0")
        addCard("Device id", "72973221")
        addCard("Battery Status", "Good")
        addCard("Sim number", "9823132456")
        addCard("Network Status", "Enabled")
        addCard("Battery Charge", "85%")
        for ( i in layout.children){
            println(i)
        }
//        Handler().postDelayed({if (view)},1000)
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