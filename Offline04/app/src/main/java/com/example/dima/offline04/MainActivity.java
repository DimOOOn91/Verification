package com.example.dima.offline04;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.content.Intent.ACTION_DIAL;
import static android.content.Intent.ACTION_SEND;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextPhone;
    private EditText mEditTextEmail;
    private EditText mEditTextUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextPhone = (EditText) findViewById(R.id.edit_text_phone);
        Button buttonCall = (Button) findViewById(R.id.button_call);
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = mEditTextPhone.getText().toString();
                Intent intent = new Intent(ACTION_DIAL, Uri.parse("tel:" + number));
                startActivity(intent);

            }
        });

        mEditTextEmail = (EditText) findViewById(R.id.edit_text_email);
        Button buttonEmail = (Button) findViewById(R.id.button_email);
        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEditTextEmail.getText().toString();
                Intent intent = new Intent(ACTION_SEND, Uri.parse("mailto:" + email));
                startActivity(intent);

            }
        });
        mEditTextUrl = (EditText) findViewById(R.id.edit_text_browser);

    }
}
