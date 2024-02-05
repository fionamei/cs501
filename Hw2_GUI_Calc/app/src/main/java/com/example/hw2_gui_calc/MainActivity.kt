package com.example.hw2_gui_calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    private lateinit var clearTV: TextView

    private var num1: Double? = null
    private var operatorView: View? = null

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
        clearTV = findViewById(R.id.clearTV)

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
            op.setOnClickListener {
                handleOperatorClick(it)
            }
        }

        clearTV.setOnClickListener {
            resultET.setText("0")
            num1 = null
            operatorView?.setBackgroundColor(ContextCompat.getColor(this, R.color.btn_background))
            operatorView = null
        }
    }

    private fun handleNumberClick(input: String) {
        resultET.setText(input)
        // i got this context compact thing from chatgpt too
        operatorView?.setBackgroundColor(ContextCompat.getColor(this, R.color.btn_background))
    }

    private fun handleOperatorClick(view: View) {
        val currentText = resultET.text
        operatorView?.let {
            resultET.setText(calculate())
        }
        operatorView = view
        view.setBackgroundColor(ContextCompat.getColor(this, R.color.btn_clicked_background))
    }

    private fun calculate(): String {
        Log.i("testing", "hit!")
        return "9999"
    }
}