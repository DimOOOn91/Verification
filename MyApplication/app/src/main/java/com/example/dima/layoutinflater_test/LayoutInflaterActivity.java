package com.example.dima.layoutinflater_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LayoutInflaterActivity extends AppCompatActivity {

    private final String LOG_TAG = "myLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_inflater);

        LayoutInflater ltInflater = getLayoutInflater();

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        View view1 = ltInflater.inflate(R.layout.text, linLayout, false);
        linLayout.addView(view1);
        ViewGroup.LayoutParams lp1 = view1.getLayoutParams();

        Log.d(LOG_TAG, "Class of view1: " + view1.getClass().toString());
        Log.d(LOG_TAG, "Class of LayoutParams of view1:  " + lp1.getClass().toString());
        Log.d(LOG_TAG, "Text of view1:  " + ((TextView) view1).getText());

        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.relLayout);
        View view2 = ltInflater.inflate(R.layout.text, relLayout, false);
        relLayout.addView(view2);
        ViewGroup.LayoutParams lp2 = view2.getLayoutParams();

        Log.d(LOG_TAG, "Class of view2:  " + view2.getClass().toString());
        Log.d(LOG_TAG, "Class of layoutParams of view2:  " + lp2.getClass().toString());
        Log.d(LOG_TAG, "Text of view2:  " + ((TextView) view2).getText());

    }
}
