package com.example.lab1

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HistoryManager (private val context : Context){

    private val sharedPreferences = context.getSharedPreferences(HISTORY_KEY, Context.MODE_PRIVATE)
    private val gson = Gson()
    private val maxSize = 10

    fun saveRecord(record : BMIRecord){
        val records = getHistory()
        records.add(0, record)
        if(records.size>maxSize)
            records.removeAt(maxSize)
        saveHistory(records)
    }

    fun saveHistory(history : ArrayList<BMIRecord>){
        val editor = sharedPreferences.edit()
        val json = gson.toJson(history)
        editor.putString(HISTORY_KEY, json)
        editor.apply()
    }

    fun getHistory(): ArrayList<BMIRecord> {
        val json = sharedPreferences.getString(HISTORY_KEY, null)
        val type = object : TypeToken<ArrayList<BMIRecord>>() {}.type
        return gson.fromJson(json, type) ?: return ArrayList()
    }



}