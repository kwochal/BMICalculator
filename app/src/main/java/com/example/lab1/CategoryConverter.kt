package com.example.lab1

import android.content.Context
import android.graphics.Color

class CategoryConverter (private val context : Context) {

    fun getCategoryName(result : Double) : String{
        return when {
            result<18.5 -> context.getString(R.string.underweight)
            result<25 -> context.getString(R.string.healthy)
            result<30 -> context.getString(R.string.overweight)
            else -> context.getString(R.string.obesity)
        }
    }
    fun getCategoryDescription(result : Double) : String {
        return when {
            result<18.5 -> context.getString(R.string.underweight_desc)
            result<25 -> context.getString(R.string.healthy_desc)
            result<30 -> context.getString(R.string.overweight_desc)
            else -> context.getString(R.string.obesity_desc)
        }
    }
    fun getCategoryColor(result : Double) : Int {
        return when {
            result<18.5 -> context.getColor(R.color.yellow)
            result<25 -> context.getColor(R.color.green)
            result<30 -> context.getColor(R.color.orange)
            else -> context.getColor(R.color.red)
        }
    }

}