package com.example.dima.preferencesexample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String SAVED_TEXT = "saved text";
    private EditText mEditText;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.eText);
        mSharedPreferences = getSharedPreferences("my_shared_preferences", MODE_PRIVATE);

        Button buttonSave = (Button) findViewById(R.id.btnSave);
        buttonSave.setOnClickListener(this);

        Button buttonLoad = (Button) findViewById(R.id.btnLoad);
        buttonLoad.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                saveText();
                break;
            case R.id.btnLoad:
                loadText();
                break;
            default:
                break;
        }
    }

    private void saveText() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(SAVED_TEXT, mEditText.getText().toString());
        editor.apply();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    private void loadText() {
        String savedText = mSharedPreferences.getString(SAVED_TEXT, "");
        mEditText.setText(savedText);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }
}
