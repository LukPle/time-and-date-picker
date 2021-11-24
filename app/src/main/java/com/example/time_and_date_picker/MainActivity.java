package com.example.time_and_date_picker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * This Activity should resemble a Reminder App.
 * It is possible to choose a specific time and date for the reminder itself.
 * This is done trough Picker.
 * Setting the reminder shows a Toast message to the user.
 *
 * Layout File: activity_main.xml
 *
 * @author Lukas Plenk
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText time, date;
    private Button button;

    Calendar calendar;
    int year;
    int month;
    int day;
    int hour;
    int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.edit_time);
        time.setOnClickListener(this);
        time.setFocusable(false);

        date = findViewById(R.id.edit_date);
        date.setOnClickListener(this);
        date.setFocusable(false);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    /**
     * Method for handling the interaction with different views.
     * The EditText fields and the Button have different actions that get executed by interaction from the user.
     * @param view is the UI component that was clicked on.
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.edit_time:
                setTime();
                break;

            case R.id.edit_date:
                setDate();
                break;

            case R.id.button:
                setReminder();
                break;
        }
    }

    /**
     * This method takes care of the TimePickerDialog.
     * The current time is set as the initial selection.
     * The user can choose between any time from the dialog itself.
     * The selected time gets transferred into a certain format.
     */
    private void setTime() {

        // Getting the current hour and minute
        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);

                String timeInTxt = hour + ":" + minute;
                time.setText(timeInTxt);
            }
        }, hour, minute, DateFormat.is24HourFormat(MainActivity.this));
        timePickerDialog.show();
    }

    /**
     * This method takes care of the DatePickerDialog.
     * The current date is set as the initial selection.
     * The user can choose between any date from the dialog itself.
     * The selected date gets transferred into a certain format.
     */
    private void setDate() {

        // Getting the current year, month and day
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);

                String dateInTxt = day + "." + month + "." + year;
                date.setText(dateInTxt);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    /**
     * Method that checks the input on the EditText fields and shows a Toast message.
     */
    private void setReminder() {

        if (time.getText().toString().trim().isEmpty() || date.getText().toString().trim().isEmpty()) {

            Toast.makeText(MainActivity.this, "Please insert all information", Toast.LENGTH_LONG).show();
        }
        else {

            String reminderInTxt = time.getText().toString() + " on the " + date.getText().toString();
            Toast.makeText(MainActivity.this, "Reminder set for " +reminderInTxt, Toast.LENGTH_LONG).show();
        }
    }
}