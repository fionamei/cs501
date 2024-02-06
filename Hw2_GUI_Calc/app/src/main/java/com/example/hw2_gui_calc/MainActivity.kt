package com.example.hw2_gui_calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    val ADD = 1
    val SUBTRACT = 2
    val MULTIPLY = 3
    val DIVIDE = 4
    val SQRT = 5
    val EQUAL = 6

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
    private var clickedOperator = false

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
            clickedOperator = false
        }
    }

    private fun handleNumberClick(input: String) {
        if (!clickedOperator && resultET.text.toString() != "0") {
            resultET.append(input)
        } else {
            resultET.setText(input)
            clickedOperator = false
        }
        // i got this context compact thing from chatgpt too!!!
        operatorView?.setBackgroundColor(ContextCompat.getColor(this, R.color.btn_background))
    }

    private fun handleOperatorClick(view: View) {
        var operator = 0
        val localOperator = operatorView
        if (localOperator is TextView) {
            operator = when (localOperator.text.toString()) {
                "+" -> ADD
                "-" -> SUBTRACT
                "/" -> DIVIDE
                "*" -> MULTIPLY
                "√" -> SQRT
                "=" -> EQUAL
                else -> 0
            }
        }
        if (clickedOperator && operator != SQRT) {
            Snackbar.make(view, getString(R.string.operator_multi_click), Snackbar.LENGTH_SHORT)
                .show()
        } else {

            val localNum1 = num1
            if (localNum1 == null || resultET.text.isBlank() || operator == 0) {
                Log.i("testing", "not valid something is wrong" + localNum1 + operator)
            } else {
                try {
                    val num2 = resultET.text.toString().toDouble()
                    if (operator == DIVIDE && num2 == 0.0) {
                        Snackbar.make(
                            view,
                            getString(R.string.divide_by_zero),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    } else if (operator == SQRT && num2 < 0) {
                        Snackbar.make(
                            view,
                            getString(R.string.sqrt_negative),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    } else {
                        resultET.setText(calculate(localNum1.toDouble(), num2, operator).toString())
                    }
                } catch (e: NumberFormatException) {
                    Snackbar.make(view, getString(R.string.invalid_format), Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
            if (view is TextView && view.text.toString() == "=") {
                operatorView?.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.btn_background
                    )
                )
                operatorView = null
                clickedOperator = true
            } else {
                operatorView = view
                view.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.btn_clicked_background
                    )
                )
                clickedOperator = true
            }
            num1 = resultET.text.toString().toDouble()

        }
    }

    private fun calculate(op1: Double, op2: Double, op: Int) =
        when (op) {
            ADD -> op1 + op2
            SUBTRACT -> op1 - op2
            DIVIDE -> op1 / op2
            MULTIPLY -> op1 * op2
            SQRT -> Math.sqrt(op2)
            else -> 0
        }
}