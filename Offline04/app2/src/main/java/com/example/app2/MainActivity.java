package com.example.app2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int TEST_REQUEST = 111;
    public static final String TEST_NAME = "Name";
    public static final String TEST_PHONE = "Phone";

    private TextView mTextViewName;
    private TextView mTextViewPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewName = (TextView) findViewById(R.id.name);
        mTextViewPhone = (TextView) findViewById(R.id.phone);
        Button button = (Button) findViewById(R.id.button_chose_phone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_OK) {
            switch (resultCode) {
                case TEST_REQUEST:
                    mTextViewName.setText(data.getExtras().getByte(TEST_NAME));
            }
        }
    }

}
