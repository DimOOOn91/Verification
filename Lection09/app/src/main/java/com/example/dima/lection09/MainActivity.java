package com.example.dima.lection09;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 5; i++) {
            mList.add("text" + i);
        }

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, mList));

//        listView.setOnCreateContextMenuListener(this);
        registerForContextMenu(listView);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                Toast.makeText(this, "111", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Toast.makeText(this, "222", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu3:
                Toast.makeText(this, "333", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("LaLaLa");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public void registerForContextMenu(View view) {
        super.registerForContextMenu(view);
    }
}
