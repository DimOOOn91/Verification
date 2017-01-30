package com.example.androidhelper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

    public static final String ONE_TIME = "oneTime";

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();
        String getExtraString = extras.getString("extra");

        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Your tag");
        wakeLock.acquire();

        StringBuilder messageString = new StringBuilder();

        if (extras != null && extras.getBoolean(ONE_TIME, Boolean.FALSE)) {
            messageString.append("One time alarm clock: ");
        }

        Format formatter = new SimpleDateFormat("hh:mm:ss a");
        messageString.append(formatter.format(new Date()));

        Toast.makeText(context, messageString, Toast.LENGTH_LONG).show();

        //start the ringtone service
        Intent serviceIntent = new Intent(context, RingtonePlayingService.class);

        // pass the extra string from MainActivity to RingtonePlayingService
        serviceIntent.putExtra("extra", getExtraString);

        context.startService(serviceIntent);



        wakeLock.release();
    }

    public void setAlarm(Context context) {
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        intent.putExtra(ONE_TIME, Boolean.FALSE);
        intent.putExtra("extra", "alarm on");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 2, pendingIntent);
    }

    public void cancelAlarm(Context context) {
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        intent.putExtra("extra", "alarm off");
        context.sendBroadcast(intent);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        Log.d("wtf", "context.sendBroadcast(intent): alarm off");

        //stop the ringtone
    }

    public void setOneTimeTimer(Context context) {
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        intent.putExtra(ONE_TIME, Boolean.TRUE);
        intent.putExtra("extra", "alarm on");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent);
    }
}
