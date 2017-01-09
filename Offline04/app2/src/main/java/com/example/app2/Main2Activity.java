package com.example.app2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button = (Button) findViewById(R.id.select);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.TEST_NAME, "Hi");
                intent.putExtra(MainActivity.TEST_PHONE, "123-12");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
