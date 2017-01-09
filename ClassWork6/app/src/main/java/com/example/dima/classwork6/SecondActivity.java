package com.example.dima.classwork6;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button share = (Button) findViewById(R.id.share);

        Button checkWifi = (Button) findViewById(R.id.check_wifi);
        checkWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkWifiSettings();
            }
        });
        Button openBrowser = (Button) findViewById(R.id.open_browser);

    }

    public boolean checkWifiSettings() {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        return intent.resolveActivity(getPackageManager()) != null;
    }

    public void openWebPage(String url) {
        Uri webpage =
    }
}
