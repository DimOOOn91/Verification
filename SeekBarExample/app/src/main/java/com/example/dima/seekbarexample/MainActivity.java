package com.example.dima.seekbarexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar sbWeight;
    private Button mButton1;
    private Button mButton2;

    LinearLayout.LayoutParams lParams1;
    LinearLayout.LayoutParams lParams2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbWeight = (SeekBar) findViewById(R.id.sbWeight);
        sbWeight.setOnSeekBarChangeListener(this);

        mButton1 = (Button) findViewById(R.id.btn1);
        mButton2 = (Button) findViewById(R.id.btn2);

        lParams1 = (LinearLayout.LayoutParams) mButton1.getLayoutParams();
        lParams2 = (LinearLayout.LayoutParams) mButton2.getLayoutParams();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        int leftValue = progress;
        int rightValue = seekBar.getMax() - progress;
        lParams1.weight = leftValue;
        lParams2.weight = rightValue;
        mButton1.setText(String.valueOf(leftValue));
        mButton2.setText(String.valueOf(rightValue));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
