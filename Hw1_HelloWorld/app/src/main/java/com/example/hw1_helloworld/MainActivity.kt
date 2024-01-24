package com.example.hw1_helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var sayHelloBtn: Button
    lateinit var helloText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sayHelloBtn = findViewById(R.id.sayHelloBtn)
        helloText = findViewById(R.id.helloText)

        sayHello()
    }

    fun sayHello() {
        sayHelloBtn.setOnClickListener{
            helloText.setText(R.string.hello_world)
        }
    }
}