package com.example.lab1
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.time.LocalDate

const val RESULT_KEY = "result"
const val HISTORY_KEY = "records"
const val RES_COLOR_KEY = "res_color"

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: FirstPageViewModel
    private lateinit var historyManager: HistoryManager
    private lateinit var catConverter: CategoryConverter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(FirstPageViewModel::class.java)
        historyManager = HistoryManager(this)
        catConverter = CategoryConverter(this)

        val goButton : Button = findViewById<Button>(R.id.button)
        val resultTV = findViewById<TextView>(R.id.result)
        val weightET = findViewById<EditText>(R.id.weightET)

        weightET.filters = arrayOf(DecimalDigitsInputFilter(3,1))
        setUnits()

        goButton.setOnClickListener{
            resultTV.text=""
            if(isInputValid()) {
                updateViewModel()
                viewModel.calculate()
                writeResult()
                updateHistory()
            }
        }

        resultTV.setOnClickListener {
            if(resultTV.text.isNotEmpty()) {
                val intent = Intent(this, ResultPageActivity::class.java)
                intent.putExtra(RESULT_KEY, viewModel.getResult())
                startActivity(intent)
            }
        }
    }

    fun updateHistory(){
        val record = BMIRecord(viewModel.getWeight(), viewModel.getHeight(), viewModel.getResult(),
            catConverter.getCategoryColor(viewModel.getResult()),LocalDate.now().toString(),viewModel.isMetric())
        historyManager.saveRecord(record)
    }

    fun writeResult() {
        val resultTV = findViewById<TextView>(R.id.result)
        val result = viewModel.getResult()
        resultTV.text = getString(R.string.result_template, result)
        resultTV.setTextColor(catConverter.getCategoryColor(result))
    }

    fun updateViewModel(){
        val height = findViewById<EditText>(R.id.heightET).text.toString().toDouble()
        val weight = findViewById<EditText>(R.id.weightET).text.toString().toDouble()
        viewModel.setHeight(height)
        viewModel.setWeight(weight)
    }

    fun isInputValid() : Boolean {
        val heightET = findViewById<EditText>(R.id.heightET)
        val weightET = findViewById<EditText>(R.id.weightET)
        return heightET.text.isNotEmpty() && weightET.text.isNotEmpty() && heightET.text.toString().toDouble()!=0.0
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    fun setUnits(){
        val heightTV = findViewById<TextView>(R.id.heightTV)
        val weightTV = findViewById<TextView>(R.id.weightTV)
        if(viewModel.isMetric()){
            heightTV.text=getString(R.string.height_prompt,getString(R.string.cm))
            weightTV.text=getString(R.string.weight_prompt,getString(R.string.kg))
        }
        else{
            heightTV.text=getString(R.string.height_prompt,getString(R.string.inch))
            weightTV.text=getString(R.string.weight_prompt,getString(R.string.lbs))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.unit_change){
            viewModel.changeUnits()
            viewModel.setCalc()
            setUnits()
            findViewById<TextView>(R.id.heightET).text=""
            findViewById<TextView>(R.id.weightET).text=""
            findViewById<TextView>(R.id.result).text=""
        }
        if(item.itemId==R.id.aboutMe){
            val intent = Intent(this, AuthorPageActivity::class.java)
            startActivity(intent)
        }
        if(item.itemId==R.id.history){
            val intent = Intent(this, HistoryPageActivity::class.java)
            val history = historyManager.getHistory()
            intent.putExtra(HISTORY_KEY, history)
            startActivity(intent)

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val res = findViewById<TextView>(R.id.result).text.toString()
        val res_color = findViewById<TextView>(R.id.result).currentTextColor
        outState.putString(RESULT_KEY,res)
        outState.putInt(RES_COLOR_KEY,res_color)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val resTV = findViewById<TextView>(R.id.result)
        resTV.setText(savedInstanceState.getString(RESULT_KEY))
        resTV.setTextColor(savedInstanceState.getInt(RES_COLOR_KEY))
    }


}
