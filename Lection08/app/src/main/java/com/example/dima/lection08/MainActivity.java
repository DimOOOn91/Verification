package com.example.dima.lection08;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.edit_text);

        Picasso.with(this).load("http://www.imgion.com/images/02/Have-a-good-day-rooster.jpg").into(imageView);
    }
}
