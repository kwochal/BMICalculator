package com.example.lab1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable


class HistoryAdapter(private val records : List<BMIRecord>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val heightTV : TextView
        val weightTV : TextView
        val dateTV : TextView
        val resTV : TextView

        init {
            heightTV = view.findViewById(R.id.heightHistTV)
            weightTV = view.findViewById(R.id.weightHistTV)
            dateTV = view.findViewById(R.id.dateTV)
            resTV = view.findViewById(R.id.resultHistTV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bmi_record_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return records.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.dateTV.text = records[position].date
        val units = getUnits(records[position],context)
        holder.heightTV.text = context.getString(R.string.height,records[position].height, units.first)
        holder.weightTV.text = context.getString(R.string.weight,records[position].weight,units.second)
        holder.resTV.text = String.format("%.2f",  records[position].result)
        holder.resTV.setTextColor(records[position].color)
    }

    fun getUnits(record : BMIRecord, context : Context) : Pair<String,String> {
        if(record.isMetric)
            return Pair(context.getString(R.string.cm),context.getString(R.string.kg))
        else
            return Pair(context.getString(R.string.inch),context.getString(R.string.lbs))
    }

}