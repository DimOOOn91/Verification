package com.example.task3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private TextView mTextView;

    private SharedPreferences mSharedPreferences;

    private String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);

        mTextView = (TextView) findViewById(R.id.text_view);
        mEditText = (EditText) findViewById(R.id.edit_text);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString();
                mSharedPreferences.edit().putString(SAVED_TEXT, input).apply();
            }
        });



        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditText.setText("");
                String text = mSharedPreferences.getString(SAVED_TEXT, "");
                mTextView.setText(text);
            }
        });
    }
}
