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
        super.onPostCreate(savedInstanceState);
        makeLog("onPostCreate");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        makeLog("onPostResume");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        makeLog("onBackPressed");
    }

    @Override
    protected void onStart() {
        super.onStart();
        makeLog("onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        makeLog("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        makeLog("onDestroy");
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        makeLog("onContentChanged");
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        super.onPanelClosed(featureId, menu);
        makeLog("onPanelClosed");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        makeLog("onActivityResult");
    }

    @Override
    protected void onPause() {
        super.onPause();
        makeLog("onPause");
    }

    @Override
    public void onStateNotSaved() {
        super.onStateNotSaved();
        makeLog("onStateNotSaved");
    }

    @Override
    protected void onResume() {
        super.onResume();
        makeLog("onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        makeLog("onRestart");
    }

    private void makeLog(String s) {
        Log.d(MY_LOG, s);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(Main2Activity.this, MainActivity.class));
    }
}
