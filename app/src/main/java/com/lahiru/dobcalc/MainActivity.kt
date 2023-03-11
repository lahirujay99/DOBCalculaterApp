package com.lahiru.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
       private var selectedDate : TextView? = null
       private var calculatedResultView : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnPicker: Button = findViewById(R.id.btnDatePicker)
        selectedDate = findViewById(R.id.tvDateShower)
        calculatedResultView = findViewById(R.id.calculatedResult)

        btnPicker.setOnClickListener {
            clickDatePicker()

        }
    }

    private fun clickDatePicker() {
        val calander = Calendar.getInstance()
        val year = calander.get(Calendar.YEAR)
        val month = calander.get(Calendar.MONTH)
        val day = calander.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, dayOfMonth ->
                Toast.makeText(this, "Year was $selectedYear, Month was ${selectedMonth+1}, Day was $dayOfMonth", Toast.LENGTH_LONG).show()
                val pickedDate = "$dayOfMonth/${selectedMonth+1}/$selectedYear"
                selectedDate?.text = pickedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(pickedDate)

                val selectedDateInMinute = theDate.time/60000
                val currantDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currantDateInMinutes = currantDate.time/60000
                val difference = currantDateInMinutes - selectedDateInMinute
                calculatedResultView?.text= difference.toString();
            },
            year, month, day
        ).show()

    }
}