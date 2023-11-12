package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultPageActivity : AppCompatActivity() {

    private val catConverter : CategoryConverter = CategoryConverter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)
        val result = intent.getDoubleExtra(RESULT_KEY, 0.0)
        val color = catConverter.getCategoryColor(result)

        writeResult(result, color)
        describeResult(result)
    }

    fun writeResult(result : Double, color : Int) {
        val resultTV = findViewById<TextView>(R.id.resultTV)
        resultTV.text = getString(R.string.result_template, result)
        resultTV.setTextColor(color)
    }

    fun describeResult(result : Double) {
        val catTV = findViewById<TextView>(R.id.categoryTV)
        catTV.text = catConverter.getCategoryName(result)
        val descTV = findViewById<TextView>(R.id.descTV)
        descTV.text = catConverter.getCategoryDescription(result)
    }

}