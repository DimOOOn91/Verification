package com.example.dima.offline2;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayout = new LinearLayout(MainActivity.this);
        mLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        mLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(mLayout);




        final EditText editText = new EditText(MainActivity.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayout.addView(editText, params);

        Button button = new Button(MainActivity.this);
        button.setText(R.string.save);
        mLayout.addView(button, params);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editText.getText().toString();
                if (input.equals("")) {
                    return;
                }
                TextView textView = new TextView(MainActivity.this);
                textView.setText(input);
                mLayout.addView(textView,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
            }
        });
    }
}
