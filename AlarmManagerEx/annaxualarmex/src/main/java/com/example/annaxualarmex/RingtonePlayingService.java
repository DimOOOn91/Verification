package com.example.annaxualarmex;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class RingtonePlayingService extends Service {

    private static MediaPlayer media_song;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e("In the service", "onStartCommand");

        // fetch the extra string values
        String state = intent.getExtras().getString("extra");

        Log.e("Ringtone extra is ", state);

        // this converts the extra strings from the intent
        // to start IDs, values 0 or 1
        assert state != null;
        switch (state) {
            case "alarm_on":
                this.startId = 1;
                Log.e("Start ID is ", state);
                break;
            case "alarm_off":
                this.startId = 0;
                Log.e("Start ID is ", state);
                break;
            default:
                this.startId = 0;
                Log.e("Start ID is ", state);
                break;
        }


//        media_song.setAudioStreamType(AudioManager.STREAM_ALARM);

        // if else statement
        // if there no music playing, and the user pressed "alarm_on"
        // music should start playing
        if (!isRunning && this.startId == 1) {
            Log.e("There is no music", " and you want to start");


            // create an instance of media player
            getMediaPlayerInstance();
            // start the ringtone
            media_song.start();

            this.isRunning = true;

            // if there is music playing, an the user pressed "alarm_off"
            // music should stop playing
        } else if (isRunning && this.startId == 0) {
            Log.e("There is music", " and you want to end");

            // stop the ringtone
            media_song.stop();
            media_song.reset();

            this.isRunning = false;

            // these are if the user presses the random buttons
            // just to bug-proof the app
            // if there is no music, and the user presser "alarm_off"
            // do nothing
        } else if (!isRunning && this.startId == 0) {
            Log.e("There is no music", " and you want to end");

            // if there is music playing,  and the user presser "alarm_on"
            // do nothing
        } else if (isRunning && this.startId == 1) {
            Log.e("There is music", " and you want to start");

            // can't think of anything else, just to catch the odd event
        } else {
            Log.e("else", " somehow you rich this");
            Log.e("else", "isRunning = " + isRunning + "; startId = " + this.startId);

            media_song.stop();
            media_song.reset();

            this.isRunning = false;
            this.startId = 0;
        }


        return START_NOT_STICKY;
    }

    private MediaPlayer getMediaPlayerInstance() {
        if (media_song == null) {
            media_song = MediaPlayer.create(this, R.raw.music);
        }
        return media_song;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        this.isRunning = false;
    }
}
