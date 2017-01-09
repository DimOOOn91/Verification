package com.example.task2;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = (LinearLayout) findViewById(R.id.activity_main);
        mLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.yellow));

        Button buttonRed = (Button) findViewById(R.id.red);
        buttonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.red));
            }
        });

        Button buttonBlue = (Button) findViewById(R.id.blue);
        buttonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.blue));
            }
        });

        Button buttonGreen = (Button) findViewById(R.id.green);
        buttonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.green));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}
