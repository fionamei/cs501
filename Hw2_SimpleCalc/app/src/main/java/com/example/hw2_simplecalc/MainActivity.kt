package com.example.hw2_simplecalc

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val ADD = 1
    val SUBTRACT = 2
    val DIVIDE = 3
    val MULTIPLY = 4

    private lateinit var firstNumET: EditText
    private lateinit var operatorSpinner: Spinner
    private lateinit var secondNumET: EditText
    private lateinit var calculateBtn: Button
    private lateinit var answerTV: TextView
    private var operator: Int? = null

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
                    else -> null
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun setUpClickListener() {
        calculateBtn.setOnClickListener {
            firstNumET.text?.toString()?.toInt()?.let { firstNum ->
                secondNumET.text?.toString()?.toInt()?.let { secondNum ->
                    operator?.let { operator ->
                        answerTV.text = if (secondNum == 0 && operator == DIVIDE) getString(R.string.calculate) else calculate(firstNum, secondNum, operator).toString()
                    }
                }

            }
            // had this originally but asked chatgpt how i could make it look nicer since
            // i knew kotlin has fancy question marks to do null checks
            // if (firstNumET.text != null && secondNumET.text != null && operator != null)
        }
    }

    private fun calculate(op1: Int, op2: Int, op: Int) =
        when (op) {
            ADD -> op1 + op2
            SUBTRACT -> op1 - op2
            DIVIDE -> op1 / op2
            MULTIPLY -> op1 * op2
            else -> 0
        }
}
