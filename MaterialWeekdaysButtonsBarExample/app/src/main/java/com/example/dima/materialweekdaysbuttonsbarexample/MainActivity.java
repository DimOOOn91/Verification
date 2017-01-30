package com.example.dima.materialweekdaysbuttonsbarexample;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.touchboarder.weekdaysbuttons.WeekdaysDataItem;
import com.touchboarder.weekdaysbuttons.WeekdaysDataSource;
import com.touchboarder.weekdaysbuttons.WeekdaysDrawableProvider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements WeekdaysDataSource.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeekdaysDataSource wds = new WeekdaysDataSource(this, R.id.weekdays_stub)
                .setLocale(Locale.ENGLISH)
                .setDrawableType(WeekdaysDrawableProvider.MW_ROUND)
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setSelectedDays(Calendar.MONDAY, Calendar.WEDNESDAY)
                .setSelectedColorRes(android.R.color.holo_blue_dark)
                .setUnselectedColor(Color.TRANSPARENT)
                .setTextColorUnselectedRes(android.R.color.secondary_text_dark)
                .setFontTypeFace(Typeface.MONOSPACE)
                .setFontBaseSize(15)
                .setNumberOfLetters(2)
                .start(this);
    }

    @Override
    public void onWeekdaysItemClicked(int i, WeekdaysDataItem weekdaysDataItem) {
        // Do something if today is selected?
        Calendar calendar = Calendar.getInstance();
        if(weekdaysDataItem.isSelected())
            Toast.makeText(MainActivity.this,"Carpe diem",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWeekdaysSelected(int i, ArrayList<WeekdaysDataItem> arrayList) {

    }
}
