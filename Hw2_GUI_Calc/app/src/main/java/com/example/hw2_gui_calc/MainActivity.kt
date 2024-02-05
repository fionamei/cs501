package com.example.hw2_gui_calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    val ADD = 1
    val SUBTRACT = 2
    val MULTIPLY = 3
    val DIVIDE = 4
    val SQRT = 5

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
    private lateinit var divide: TextView
    private lateinit var add: TextView
    private lateinit var subtract: TextView
    private lateinit var sqrt: TextView
    private lateinit var decimal: TextView
    private lateinit var equal: TextView
    private lateinit var resultET: EditText
    private lateinit var numbers: Array<TextView>
    private lateinit var operators: Array<TextView>

    private var num1: Double ?= null
    private var opView: TextView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setListeners()
    }

    fun initializeViews() {
        /** I ASKED CHATGPT HOW TO DO THIS BECAUSE I DIDNT WANT TO
        MAKE A CLICK LISTENER FOR EACH VIEW so i got the array of views
        and the for loop click listener from chatgpt **/

        zero = findViewById(R.id.zeroTV)
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
        divide = findViewById(R.id.divideTV)
        add = findViewById(R.id.addTV)
        subtract = findViewById(R.id.subtractTV)
        sqrt = findViewById(R.id.sqrtTV)
        decimal = findViewById(R.id.decimalTV)
        equal = findViewById(R.id.equalTV)
        resultET = findViewById(R.id.resultET)

        numbers = arrayOf(
            zero, one, two, three, four, five, six, seven, eight, nine, decimal
        )

        operators = arrayOf(
            multiply, divide, add, subtract, sqrt, equal
        )
    }

    private fun setListeners() {
        for (num in numbers) {
            num.setOnClickListener {
                handleNumberClick(num.text.toString())
            }
        }

        for (op in operators) {
            op.setOnClickListener{
                handleOperatorClick(it)
            }
        }
    }

    private fun handleNumberClick(input: String) {
        val currentText = resultET.text
        resultET.text = currentText.append(input)
    }

    private fun handleOperatorClick(view: View) {
        val currentText = resultET.text
        // i got this from chatgpt too
        view.setBackgroundColor(ContextCompat.getColor(this, R.color.btn_clicked_background))
    }

    // TODO: clear button and clear button clears the operators and numbers and edit text
}