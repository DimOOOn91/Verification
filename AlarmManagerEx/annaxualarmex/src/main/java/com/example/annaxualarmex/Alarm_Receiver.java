package com.example.annaxualarmex;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class Alarm_Receiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("we are in the receiver.", "Yay!");

        // fetch extra strings from the intent
        String get_your_intent = intent.getExtras().getString("extra");

        Log.e("What is the key? ", get_your_intent);

        // create an intent to the Ringtone Service
        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        // pass the extra string from Main Activity to the Ringtone Playing Service
        service_intent.putExtra("extra", get_your_intent);

        // start the ringtone service
        context.startService(service_intent);

    }
}
