package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_page)
        setupRV()
    }

    fun setupRV() {
        val historyRV = findViewById<RecyclerView>(R.id.historyRV)
        val records : List<BMIRecord> = intent.getSerializableExtra(HISTORY_KEY) as ArrayList<BMIRecord>
        val adapter = HistoryAdapter(records)
        val layoutManager = LinearLayoutManager(this)
        historyRV.adapter = adapter
        historyRV.layoutManager = layoutManager
        historyRV.addItemDecoration(DividerItemDecoration(baseContext, layoutManager.orientation))
    }
}