package com.example.lab1

import java.io.Serializable

data class BMIRecord(
    val weight: Double,
    val height: Double,
    val result: Double,
    val color: Int,
    val date: String,
    val isMetric : Boolean) : Serializable