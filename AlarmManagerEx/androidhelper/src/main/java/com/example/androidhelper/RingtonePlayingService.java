package com.example.androidhelper;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class RingtonePlayingService extends Service {

    MediaPlayer mMediaPlayer;
    //    Ringtone mRingtone;
    int myStartId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // fetch the extra string values
        String state = intent.getExtras().getString("extra");
        Log.d("wtf", "Service.onStartCommand: " + state);

        // convert the extra string to startId 1 or 0
        assert state != null;
        switch (state) {
            case "alarm on":
                myStartId = 1;
                break;
            case "alarm off":
                myStartId = 0;
                break;
            default:
                myStartId = 0;
                break;
        }

//        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        mRingtone = RingtoneManager.getRingtone(this, Uri.parse("android.resource://com.example.androidhelper/raw/music"));

        if (!isRunning && startId == 1) {
            Log.d("wtf", "1 isRunning = " + isRunning + " startId = " + startId);
            mMediaPlayer = MediaPlayer.create(this, R.raw.music);
            mMediaPlayer.start();
//            mRingtone.play();
            this.isRunning = true;
            this.myStartId = 0;
        } else if (isRunning && startId == 0) {
            Log.d("wtf", "2 isRunning = " + isRunning + " startId = " + startId);
            mMediaPlayer.stop();
            mMediaPlayer.reset();
//            mRingtone.stop();
            this.isRunning = false;
            this.myStartId = 0;
        } else if (!isRunning && startId == 0) {
            Log.d("wtf", "3 isRunning = " + isRunning + " startId = " + startId);

            this.isRunning = false;
            this.myStartId = 0;
        } else if (isRunning && startId == 1) {
            Log.d("wtf", "4 isRunning = " + isRunning + " startId = " + startId);
            this.isRunning = true;
            this.myStartId = 0;
        } else {
            Log.d("wtf", "isRunning = else startId = else");
            mMediaPlayer.stop();
            mMediaPlayer.reset();

            this.isRunning = false;
            this.myStartId = 0;
        }


        // create an instance of the media player

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "OnDestroy called", Toast.LENGTH_SHORT).show();

        this.isRunning = false;
        super.onDestroy();
    }
}
