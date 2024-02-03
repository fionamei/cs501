package com.example.hw2_simplecalc

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val NOT_SELECTED = 0
    val ADD = 1
    val SUBTRACT = 2
    val DIVIDE = 3
    val MULTIPLY = 4
    val MOD = 5

    private lateinit var firstNumET: EditText
    private lateinit var operatorSpinner: Spinner
    private lateinit var secondNumET: EditText
    private lateinit var calculateBtn: Button
    private lateinit var answerTV: TextView
    private var operator = NOT_SELECTED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeObjects()
        setUpSpinner()
        setUpClickListener()
    }

    private fun initializeObjects() {
        firstNumET = findViewById(R.id.firstNumEditText)
        operatorSpinner = findViewById(R.id.operatorSpinner)
        secondNumET = findViewById(R.id.secondNumEditText)
        calculateBtn = findViewById(R.id.calculateBtn)
        answerTV = findViewById(R.id.answerTV)
    }

    private fun setUpSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.operators_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            operatorSpinner.adapter = adapter
        }

        operatorSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                operator = when (p2) {
                    1 -> ADD
                    2 -> SUBTRACT
                    3 -> DIVIDE
                    4 -> MULTIPLY
                    5 -> MOD
                    else -> NOT_SELECTED
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun setUpClickListener() {
        calculateBtn.setOnClickListener {
//            if (firstNumET.text.isBlank()) {
//                Log.i("testing", "text is blank")
//            } else {
//                Log.i("testing", "text is not !! blank")
//            }

            answerTV.text = ""

            if (firstNumET.text.isBlank() || secondNumET.text.isBlank() || operator == NOT_SELECTED) {
                Log.i("testing", "hit!!")
                Toast.makeText(this, "Please fill in the entire equation!", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    val secondNum = secondNumET.text.toString().toInt()

                    if (operator == DIVIDE && secondNum == 0) {
                        Toast.makeText(this, "Cannot divide by 0", Toast.LENGTH_SHORT).show()

                    } else if (operator == MOD && secondNum == 0) {
                        Toast.makeText(this, "Cannot mod with 0", Toast.LENGTH_SHORT).show()
                    } else {
                        answerTV.text = calculate(firstNumET.text.toString().toInt(), secondNum, operator).toString()
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun calculate(op1: Int, op2: Int, op: Int) =
        when (op) {
            ADD -> op1 + op2
            SUBTRACT -> op1 - op2
            DIVIDE -> op1 / op2
            MULTIPLY -> op1 * op2
            MOD -> op1 % op2
            else -> 0
        }
}
