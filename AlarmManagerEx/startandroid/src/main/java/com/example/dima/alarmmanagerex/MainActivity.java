package com.example.dima.alarmmanagerex;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "wtf";

    NotificationManager nm;
    AlarmManager am;
    Intent intent1;
    Intent intent2;
    PendingIntent pIntent1;
    PendingIntent pIntent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        am = (AlarmManager) getSystemService(ALARM_SERVICE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                intent1 = createIntent("action 1", "extra 1");
                pIntent1 = createPendingIntent(1, intent1);

                intent2 = createIntent("action 2", "extra 2");
                pIntent2 = createPendingIntent(1, intent2);
//                pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, 0);

                compare();

                sendNotif(1, pIntent1);
//                sendNotif(2, pIntent2);
                break;

            case R.id.btn2:
                sendAlertDialog(123);
                break;
            default:
                break;
        }
    }

    private PendingIntent createPendingIntent(int requestCode, Intent intent) {
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);
        return stackBuilder.getPendingIntent(requestCode, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private Intent createIntent(String action, String extra) {
        Intent intent = new Intent(this, Receiver.class);
        intent.setAction(action);
        intent.putExtra("extra", extra);
        return intent;
    }

    private void compare() {
        Log.d(LOG_TAG, "intent1 = intent2: " + intent1.filterEquals(intent2));
        Log.d(LOG_TAG, "pIntent1 = pIntent2: " + pIntent1.equals(pIntent2));
    }

    private void sendNotif(int id, PendingIntent pIntent) {
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Notif " + id)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Title " + id)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(pIntent)
                .setContentText("Context " + id)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        nm.notify(id, notification);
    }

    private void sendAlertDialog(int id) {
        new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("Notif - " + id)
                .setIcon(R.mipmap.ic_launcher)
                .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .show();
    }
}
