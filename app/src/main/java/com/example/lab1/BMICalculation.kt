package com.example.lab1

import kotlin.math.pow

interface BMICalculation {
    fun calculate(weight : Double, height : Double) : Double
}

class MetricCalculator : BMICalculation {
    override fun calculate(weight: Double, height: Double) : Double {
        val heightInM = height/100
        val res = (weight/(heightInM.pow(2)))
        return res
    }
}

class ImperialCalculator : BMICalculation {
    override fun calculate(weight: Double, height: Double) : Double {
        val res = (weight/(height.pow(2))*703)
        return res
    }
}