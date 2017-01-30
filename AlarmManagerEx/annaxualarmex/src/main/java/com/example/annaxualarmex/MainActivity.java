package com.example.annaxualarmex;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // to make our alarm manager
    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    PendingIntent pending_intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize our alarm manager
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // initialize our timepicker
        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);

        // initialize our text update box
        update_text = (TextView) findViewById(R.id.update_text);

        // create instance of a calendar
        final Calendar calendar = Calendar.getInstance();

        // create an intent to the Alarm Receiver class
        final Intent my_intent = new Intent(this, Alarm_Receiver.class);


        //initialize start buttons
        Button alarm_on = (Button) findViewById(R.id.alarm_on);
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int hour = 0;
                int minute = 0;

                // picking hour and minute from the time picker
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    hour = alarm_timepicker.getHour();
                    minute = alarm_timepicker.getMinute();
                } else {
                    hour = alarm_timepicker.getCurrentHour();
                    minute = alarm_timepicker.getCurrentMinute();
                }

                // setting calendar instance with the hour and minute
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);

                // get the string from out hours and minutes
                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                if (minute < 10) {
                    minute_string = "0" + minute_string;
                }

                // method that changes the update text Textbox
                set_alarm_text("Alarm to: " + hour_string + ":" + minute_string);

                // put in extra string into my_intent
                // tells the click that you pressed tha "alarm_on" button
                my_intent.putExtra("extra", "alarm_on");

                // create a pending intent that delays the intent
                // until the specified calendar time
                pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0,
                        my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                // set the alarm manager
                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        pending_intent);


            }
        });


        //initialize the stop buttons
        Button alarm_off = (Button) findViewById(R.id.alarm_off);
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // method that changes the update text Textbox
                set_alarm_text("Alarm off!");

                // cancel the alarm
                alarm_manager.cancel(pending_intent);

                // put in extra string into my_intent
                // tells the click that you pressed tha "alarm_off" button
                my_intent.putExtra("extra", "alarm_off");

                // stop the ringtone
                sendBroadcast(my_intent);

            }
        });
    }

    private void set_alarm_text(String output) {
        update_text.setText(output);
    }

    @Override
    public void onClick(View view) {

    }
}
