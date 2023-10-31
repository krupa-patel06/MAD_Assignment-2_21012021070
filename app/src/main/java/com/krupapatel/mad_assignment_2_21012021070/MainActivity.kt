package com.krupapatel.mad_assignment_2_21012021070

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn00 = findViewById<Button>(R.id.btn00)
        val btn0 = findViewById<Button>(R.id.btn00)
        val btn1 = findViewById<Button>(R.id.btn00)
        val btn2 = findViewById<Button>(R.id.btn00)
        val btn3 = findViewById<Button>(R.id.btn00)
        val btn4 = findViewById<Button>(R.id.btn00)
        val btn5 = findViewById<Button>(R.id.btn00)
        val btn6 = findViewById<Button>(R.id.btn00)
        val btn7 = findViewById<Button>(R.id.btn00)
        val btn8 = findViewById<Button>(R.id.btn00)
        val btn9 = findViewById<Button>(R.id.btn00)
        val btnDot = findViewById<Button>(R.id.btn00)
        val btnPlus = findViewById<Button>(R.id.btn00)
        val btnMinus = findViewById<Button>(R.id.btn00)
        val btnMultiply = findViewById<Button>(R.id.btn00)
        val btnDivide = findViewById<Button>(R.id.btn00)
        val btnLeftB = findViewById<Button>(R.id.btn00)
        val btnRightB = findViewById<Button>(R.id.btn00)
        val btnClear = findViewById<Button>(R.id.btn00)
        val btnEqual = findViewById<Button>(R.id.btn00)


        //Number listeners
        btn00.setOnClickListener { appendOnClick(true, "00") }
        btn0.setOnClickListener { appendOnClick(true, "0") }
        btn1.setOnClickListener { appendOnClick(true, "1") }
        btn2.setOnClickListener { appendOnClick(true, "2") }
        btn3.setOnClickListener { appendOnClick(true, "3") }
        btn4.setOnClickListener { appendOnClick(true, "4") }
        btn5.setOnClickListener { appendOnClick(true, "5") }
        btn6.setOnClickListener { appendOnClick(true, "6") }
        btn7.setOnClickListener { appendOnClick(true, "7") }
        btn8.setOnClickListener { appendOnClick(true, "8") }
        btn9.setOnClickListener { appendOnClick(true, "9") }
        btnDot.setOnClickListener { appendOnClick(true, ".") }


        //Operator Listeners
        btnPlus.setOnClickListener { appendOnClick(false, "+") }
        btnMinus.setOnClickListener { appendOnClick(false, "-") }
        btnMultiply.setOnClickListener { appendOnClick(false, "*") }
        btnDivide.setOnClickListener { appendOnClick(false, "/") }
        btnLeftB.setOnClickListener { appendOnClick(false, "(") }
        btnRightB.setOnClickListener { appendOnClick(false, ")") }


        btnClear.setOnClickListener {
            clear()
        }

        btnEqual.setOnClickListener {
            calculate()
        }


    }

    //now create methods
    val tvInput = findViewById<TextView>(R.id.tvInput)
    val tvOutput = findViewById<TextView>(R.id.tvOutput)

    private fun appendOnClick(clear: Boolean, string: String) {

        if (clear) {
            tvOutput.text = ""
            tvInput.append(string)
        } else {
            tvInput.append(tvOutput.text)
            tvInput.append(string)
            tvOutput.text = ""
        }
    }

    private fun clear() {
        tvInput.text = ""
        tvOutput.text = ""

    }

    private fun calculate() {
        try {
            val expression = tvInput.text.toString()
            val result = evaluateExpression(expression)
            tvOutput.text = result.toString()
        } catch (e: Exception) {
            // Handle any errors or exceptions, such as displaying an error message.
            tvOutput.text = "Error: ${e.message}"
        }
    }

    fun evaluateExpression(expression: String): Double {
        // Split the expression by operators (+, -, *, /) and remove empty elements
        val elements = expression.split("[+\\-*/]".toRegex()).filter { it.isNotEmpty() }
        // Split the expression by numbers and keep the operators
        val operators = expression.split("\\d+".toRegex()).filter { it.isNotEmpty() }

        // Ensure the number of operators is one less than the number of elements
        require(operators.size == elements.size - 1) { "Invalid expression" }

        var result = elements[0].toDouble()
        for (i in operators.indices) {
            when (operators[i]) {
                "+" -> result += elements[i + 1].toDouble()
                "-" -> result -= elements[i + 1].toDouble()
                "*" -> result *= elements[i + 1].toDouble()
                "/" -> {
                    val divisor = elements[i + 1].toDouble()
                    require(divisor != 0.0) { "Division by zero" }
                    result /= divisor
                }
            }
        }

        return result
    }
}



