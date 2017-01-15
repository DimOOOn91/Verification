package com.example.dima.lection06;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_IMAGE_CAPTURE = 111;
    private static final String IMAGE_DIRECTORY = "TestPhoto";
    private Uri photoUri;
    private LocationListener mLocationListener;


    @BindView(R.id.et_shreText)
    EditText title;
    @BindView(R.id.alarm_message)
    EditText alarmMessage;
    @BindView(R.id.alarm_hours)
    EditText alarmHour;
    @BindView(R.id.alarm_minutes)
    EditText alarmMinute;
    @BindView(R.id.timer_length)
    EditText timerLength;
    @BindView(R.id.event_year)
    EditText eventYear;
    @BindView(R.id.event_month)
    EditText eventMonth;
    @BindView(R.id.event_day)
    EditText eventDay;
    @BindView(R.id.event_hours)
    EditText eventHour;
    @BindView(R.id.event_minutes)
    EditText eventMinute;
    @BindView(R.id.event_title)
    EditText eventTitle;
    @BindView(R.id.img_photo)
    ImageView imgPhoto;
    @BindView(R.id.btn_share)
    Button btnShare;
    @BindView(R.id.btn_setAlarm)
    Button btnSetAlarm;
    @BindView(R.id.btn_setTimer)
    Button btnSetTimer;
    @BindView(R.id.btn_setEvent)
    Button btnSetEvent;
    @BindView(R.id.btn_makePhoto)
    Button btnMakePhoto;
    @BindView(R.id.btn_wifiSettings)
    Button btnWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        btnShare.setOnClickListener(this);
        btnSetAlarm.setOnClickListener(this);
        btnSetTimer.setOnClickListener(this);
        btnSetEvent.setOnClickListener(this);
        btnMakePhoto.setOnClickListener(this);
        btnWifi.setOnClickListener(this);

        onWifiListener();

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        MyFragment fragment = new MyFragment();
//        fragmentTransaction.add(R.id.activity_main, fragment, "tag");
//        fragmentTransaction.commit();
//        getSupportFragmentManager().beginTransaction().add(R.id.activity_main, new MyFragment(), "tag").commit();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_share:
                shareMessage();
                break;
            case R.id.btn_setAlarm:
                setAlarm();
                break;
            case R.id.btn_setTimer:
                setTimer();
                break;
            case R.id.btn_setEvent:
                setEvent();
                break;
            case R.id.btn_makePhoto:
//                dispatchTakePictureIntent();
                makePhoto();
                break;
            case R.id.btn_wifiSettings:
                openWifiSettings();
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_CAPTURE:
                    previewCaptureImage();
                    break;
                default:
                    super.onActivityResult(requestCode, resultCode, data);
            }
        } else {
            Toast.makeText(this, "CANCEL", Toast.LENGTH_SHORT).show();
        }
    }

    private void shareMessage() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        String message = title.getText().toString();
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("text/plain");
        Intent chooser = Intent.createChooser(intent, "Share by:");
        startActivity(chooser);
    }

    private void setAlarm() {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, alarmMessage.getText().toString());
        intent.putExtra(AlarmClock.EXTRA_HOUR, getInteger(alarmHour));
        intent.putExtra(AlarmClock.EXTRA_MINUTES, getInteger(alarmMinute));
        startActivity(intent);
    }

    private int getInteger(EditText editText) {
        return Integer.parseInt(editText.getText().toString());
    }

    private void setTimer() {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);
        intent.putExtra(AlarmClock.EXTRA_LENGTH, getInteger(timerLength));
        intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        startActivity(intent);
    }

    private void setEvent() {

        Calendar begin = Calendar.getInstance();
        int year = getInteger(eventYear);
        int month = getInteger(eventMonth);
        int day = getInteger(eventDay);
        int hour = getInteger(eventHour);
        int minute = getInteger(eventMinute);
        begin.set(year, month, day, hour, minute);
        Calendar end = Calendar.getInstance();
        end.set(year, month, day, hour, minute + 10);

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        String title = eventTitle.getText().toString();
        intent.putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end.getTimeInMillis());
        startActivity(intent);
    }

    private void makePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoUri = getOutputPhotoUri();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public Uri getOutputPhotoUri() {
        return Uri.fromFile(getOutputPhoto());
    }

    public File getOutputPhoto() {
        File mediaStorageDir = new File(
                Environment.
                        getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY);
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                makeLog("Oooops! Field created");
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator
                + "IMG_" + timeStamp + ".jpg");
    }

    private void previewCaptureImage() {
        try {
            Bitmap bitmapOriginal = BitmapFactory.decodeFile(photoUri.getPath());
            BitmapFactory.Options options = new BitmapFactory.Options();
            imgPhoto.setVisibility(View.VISIBLE);
            imgPhoto.setImageBitmap(bitmapOriginal);
            options.inSampleSize = 4;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void openWifiSettings() {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void onWifiListener() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI &&
                    activeNetwork.isConnected()) {
                btnWifi.setBackgroundResource(android.R.color.holo_green_dark);
            } else {
                btnWifi.setBackgroundResource(android.R.drawable.btn_default);
            }
        }
    }

    private void makeLog(String s) {
        Log.d("123", s);
    }
}
