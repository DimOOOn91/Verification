package com.example.dima.lifecycleexample;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String MY_LOG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeLog("onCreate");
        Button button = (Button) findViewById(R.id.toMain2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
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

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
    }

    private void makeLog(String s) {
        Log.d(MY_LOG, s);
    }
}
