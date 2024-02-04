package com.example.hw2_gui_calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var zero: TextView
    private lateinit var one: TextView
    private lateinit var two: TextView
    private lateinit var three: TextView
    private lateinit var four: TextView
    private lateinit var five: TextView
    private lateinit var six: TextView
    private lateinit var seven: TextView
    private lateinit var eight: TextView
    private lateinit var nine: TextView
    private lateinit var multiply: TextView
    private lateinit var add: TextView
    private lateinit var subtract: TextView
    private lateinit var sqrt: TextView
    private lateinit var decimal: TextView
    private lateinit var equal: TextView
    private lateinit var resultET: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
    }

    private fun initializeViews() {
        zero = findViewById(R.id.zeroTv)
        one = findViewById(R.id.oneTV)
        two = findViewById(R.id.twoTV)
        three = findViewById(R.id.threeTV)
        four = findViewById(R.id.fourTV)
        five = findViewById(R.id.fiveTV)
        six = findViewById(R.id.sixTV)
        seven = findViewById(R.id.sevenTV)
        eight = findViewById(R.id.eightTV)
        nine = findViewById(R.id.nineTV)
        multiply = findViewById(R.id.multiplyTV)
        add = findViewById(R.id.addTV)
        subtract = findViewById(R.id.subtractTV)
        sqrt = findViewById(R.id.sqrtTV)
        decimal = findViewById(R.id.decimalTv)
        equal = findViewById(R.id.equalTV)
        resultET = findViewById(R.id.resultET)
    }
}