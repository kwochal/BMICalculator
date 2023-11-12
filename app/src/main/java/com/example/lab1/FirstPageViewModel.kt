package com.example.lab1

import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.util.Locale
import kotlin.math.roundToLong

class FirstPageViewModel : ViewModel(){

    private var isMetric = true
    private var calc : BMICalculation = MetricCalculator()
    private var height : Double = 0.0
    private var weight : Double = 0.0
    private var result : Double = 0.0

    fun setHeight(height : Double) {
        this.height=height
    }
    fun setWeight(weight : Double){
        this.weight=weight
    }
    fun getHeight() : Double {
        return height
    }
    fun getWeight() : Double {
        return weight
    }
    fun isMetric() : Boolean {
        return isMetric
    }

    fun changeUnits() {
        isMetric=!isMetric
    }

    fun setCalc(){
        calc = if(isMetric)
            MetricCalculator()
        else
            ImperialCalculator()
    }

    fun calculate() {
        result = calc.calculate(weight,height)
    }

    fun getResult() : Double{
        return result
    }


}