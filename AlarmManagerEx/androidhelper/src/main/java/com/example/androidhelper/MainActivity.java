package com.example.androidhelper;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AlarmManagerBroadcastReceiver alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarm = new AlarmManagerBroadcastReceiver();

        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_oneTime).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Context context = this.getApplicationContext();
        switch (view.getId()) {
            case R.id.btn_start:
                if (alarm != null) {
                    alarm.setAlarm(context);
                } else {
                    makeToast("Alarm is null");
                }
                break;
            case R.id.btn_cancel:
                if (alarm != null) {
                    alarm.cancelAlarm(context);
                } else {
                    makeToast("Alarm is null");
                }
                break;
            case R.id.btn_oneTime:
                if (alarm != null) {
                    alarm.setOneTimeTimer(context);
                } else {
                    makeToast("Alarm is null");
                }
                break;
            default:
                break;
        }
    }

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
