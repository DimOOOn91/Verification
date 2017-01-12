package com.example.dima.tipcalculation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener{

    public static final String PERCENT = "Percent";
    public static final String SUM = "Sum";

    private int percent;

    private EditText mOriginalSum;
    private EditText mTipPercent;
    private TextView mTotalSum;
    private TextView mTipSum;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTipSum = (TextView) findViewById(R.id.tip_sum);
        mTotalSum = (TextView) findViewById(R.id.total_sum);

        mOriginalSum = (EditText) findViewById(R.id.sum);
        mOriginalSum.addTextChangedListener(new CustomTextWatcher(mOriginalSum));

        mTipPercent = (EditText) findViewById(R.id.percent);
        mTipPercent.addTextChangedListener(new CustomTextWatcher(mTipPercent));


        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        mSeekBar.setOnSeekBarChangeListener(this);

        Button button = (Button) findViewById(R.id.clean);
        button.setOnClickListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        mTipPercent.setText(String.valueOf(i + 1));
        calculateTotalSum();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clean:
                mOriginalSum.setText("");
                mTipPercent.setText("10");
                updateSeekBar();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SUM, mOriginalSum.getText().toString());
        outState.putString(PERCENT, mTipPercent.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mOriginalSum.setText(savedInstanceState.getString(SUM, ""));
        mTipPercent.setText(savedInstanceState.getString(PERCENT, "10"));
    }

    private void updateSeekBar() {
        int progress = percent - 1;
        if (progress < 0) {
            progress = 0;
        }
        mSeekBar.setProgress(progress);
    }

    public void calculateTotalSum() {
        double originalSum = getDouble(mOriginalSum);
        double tipCoef = getDouble(mTipPercent) / 100;
        double tipSum = originalSum * tipCoef;
        mTipSum.setText(String.format("%s UAH", tipSum));
        mTotalSum.setText(String.format("%s UAH", originalSum + tipSum));
    }

    private static double getDouble(EditText editText) {
        String input = editText.getText().toString();
        if (TextUtils.isEmpty(input)) {
            return 0;
        }
        return Double.parseDouble(input);
    }

    private static int getInteger(EditText editText) {
        String input = editText.getText().toString();
        if (TextUtils.isEmpty(input)) {
            return 0;
        }
        return Integer.parseInt(input);
    }

    //Complex TextWatcher for all EditTexts
    public class CustomTextWatcher implements TextWatcher{

        private EditText mEditText;

        CustomTextWatcher(EditText editText) {
            mEditText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            switch (mEditText.getId()) {
                case R.id.percent:
                    percent = getInteger(mTipPercent);
                    updateSeekBar();
//                    mEditText.setSelection(mEditText.getSelectionEnd());
                    break;
                case R.id.sum:
                    calculateTotalSum();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }
}
