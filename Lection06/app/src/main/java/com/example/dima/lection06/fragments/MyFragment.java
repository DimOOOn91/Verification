package com.example.dima.lection06.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MyFragment extends Fragment implements View.OnClickListener {

//    @BindView(R.id.et_shreText)
//    EditText title;
//    @BindView(R.id.alarm_message)
//    EditText alarmMessage;
//    @BindView(R.id.alarm_hours)
//    EditText alarmHour;
//    @BindView(R.id.alarm_minutes)
//    EditText alarmMinute;
//    @BindView(R.id.timer_length)
//    EditText timerLength;
//    @BindView(R.id.event_year)
//    EditText eventYear;
//    @BindView(R.id.event_month)
//    EditText eventMonth;
//    @BindView(R.id.event_day)
//    EditText eventDay;
//    @BindView(R.id.event_hours)
//    EditText eventHour;
//    @BindView(R.id.event_minutes)
//    EditText eventMinute;
//    @BindView(R.id.event_title)
//    EditText eventTitle;
//    @BindView(R.id.btn_share)
//    Button btnShare;
//    @BindView(R.id.btn_setAlarm)
//    Button btnSetAlarm;
//    @BindView(R.id.btn_setTimer)
//    Button btnSetTimer;
//    @BindView(R.id.btn_setEvent)
//    Button btnSetEvent;

    public MyFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_main, container, false);
//        ButterKnife.bind(this, view);
//
//        btnShare.setOnClickListener(this);
//        btnSetAlarm.setOnClickListener(this);
//        btnSetTimer.setOnClickListener(this);
//        btnSetEvent.setOnClickListener(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.btn_share:
//                shareMessage();
//                break;
//            case R.id.btn_setAlarm:
//                setAlarm();
//                break;
//            case R.id.btn_setTimer:
//                setTimer();
//                break;
//            case R.id.btn_setEvent:
//                setEvent();
//                break;
            default:
                break;
        }
    }

//    private void shareMessage() {
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        String message = title.getText().toString();
//        intent.putExtra(Intent.EXTRA_TEXT, message);
//        intent.setType("text/plain");
//        Intent chooser = Intent.createChooser(intent, "Share by:");
//        startActivity(chooser);
//    }

//    private void setAlarm() {
//        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
//        intent.putExtra(AlarmClock.EXTRA_MESSAGE, alarmMessage.getText().toString());
//        intent.putExtra(AlarmClock.EXTRA_HOUR, getInteger(alarmHour));
//        intent.putExtra(AlarmClock.EXTRA_MINUTES, getInteger(alarmMinute));
//            startActivity(intent);
//    }
//
//    private int getInteger(EditText editText) {
//        return Integer.parseInt(editText.getText().toString());
//    }
//
//    private void setTimer() {
//        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);
//        intent.putExtra(AlarmClock.EXTRA_LENGTH, getInteger(timerLength));
//        intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
//            startActivity(intent);
//    }
//
//    private void setEvent() {
//
//        Calendar begin = Calendar.getInstance();
//        int year = getInteger(eventYear);
//        int month = getInteger(eventMonth);
//        int day = getInteger(eventDay);
//        int hour = getInteger(eventHour);
//        int minute = getInteger(eventMinute);
//        begin.set(year, month, day, hour, minute);
//        Calendar end = Calendar.getInstance();
//        end.set(year, month, day, hour, minute + 10);
//
//        Intent intent = new Intent(Intent.ACTION_INSERT);
//        intent.setData(CalendarContract.Events.CONTENT_URI);
//        String title = eventTitle.getText().toString();
//        intent.putExtra(CalendarContract.Events.TITLE, title)
//                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin.getTimeInMillis())
//                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end.getTimeInMillis());
//            startActivity(intent);
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
