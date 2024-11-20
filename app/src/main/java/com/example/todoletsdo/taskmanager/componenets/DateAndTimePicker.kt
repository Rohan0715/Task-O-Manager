package com.example.todoletsdo.taskmanager.componenets
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.TimePicker
import java.text.SimpleDateFormat
import java.util.*
object DateTimePicker {
    private var calendar = Calendar.getInstance()
    fun showDateTimePicker(context: Context, onDateTimeSet: (String) -> Unit) {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = monthOfYear
                calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                showTimePicker(context, onDateTimeSet)
            }
        DatePickerDialog(
            context, dateSetListener, calendar[Calendar.YEAR],
            calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]
        ).show()
    }
    private fun showTimePicker(context: Context, onDateTimeSet: (String) -> Unit){
        val timeSetListener =
            TimePickerDialog.OnTimeSetListener { _: TimePicker?, hourOfDay: Int, minute: Int ->
                calendar[Calendar.HOUR_OF_DAY] = hourOfDay
                calendar[Calendar.MINUTE] = minute

                val dateFormat = SimpleDateFormat("EEE, MMM d, yyyy 'at' h:mm a", Locale.getDefault())
                val selectedDateTime = dateFormat.format(calendar.time)
                onDateTimeSet(selectedDateTime)
            }
        TimePickerDialog(
            context, timeSetListener, calendar[Calendar.HOUR_OF_DAY],
            calendar[Calendar.MINUTE], false
        ).show()
    }
}
