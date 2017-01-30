package com.example.dima.lifecycleexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    public static final String MY_LOG = "Main2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        makeLog("onCreate");

        Button button = (Button) findViewById(R.id.toMain1);
        button.setOnClickListener(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        makeLog("onPostCreate");
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onPostResume() {
        makeLog("onPostResume");
        super.onPostResume();
    }

    @Override
    public void onBackPressed() {
        makeLog("onBackPressed");
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        makeLog("onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        makeLog("onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        makeLog("onDestroy");
        super.onDestroy();
    }

    @Override
    public void onContentChanged() {
        makeLog("onContentChanged");
        super.onContentChanged();
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        makeLog("onPanelClosed");
        super.onPanelClosed(featureId, menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        makeLog("onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        makeLog("onPause");
        super.onPause();
    }

    @Override
    public void onStateNotSaved() {
        makeLog("onStateNotSaved");
        super.onStateNotSaved();
    }

    @Override
    protected void onResume() {
        makeLog("onResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        makeLog("onRestart");
        super.onRestart();
    }

    private void makeLog(String s) {
        Log.d(MY_LOG, s);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(Main2Activity.this, MainActivity.class));
    }
}
