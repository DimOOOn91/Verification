package com.example.test3;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class ExpandableListViewTest extends AppCompatActivity {

    private String[] mGroupsArray = new String[]{"Winter", "Spring", "Summer", "Autumn"};

    private String[] mWinterMonthArray = new String[]{"December", "January", "February"};
    private String[] mSpringMonthArray = new String[]{"March", "April", "May"};
    private String[] mSummerMonthArray = new String[]{"June", "July", "August"};
    private String[] mAutumnMonthArray = new String[]{"September", "October", "November"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view_test);

        // collection for group
        Map<String, String> map;

        // fill the collection of groups from the array with the groups names
        ArrayList<Map<String, String>> groupDataList = new ArrayList<>();

        //fill the list of attributes for each group
        for (String group : mGroupsArray) {
            map = new HashMap<>();
            map.put("groupName", group);
            groupDataList.add(map);
        }

        //array of groups attributes to read
        String[] groupFrom = new String[]{"groupName"};
        //array of ID of views' for groups' attributes
        int[] groupTo = new int[]{android.R.id.text1};

        //create global collection for collections of elements
        ArrayList<ArrayList<Map<String, String>>> childDataList = new ArrayList<>();

        //so childDataList = ArrayList<chilledDataItemList>

        //create collection of elements for the first group
        ArrayList<Map<String, String>> childDataItemList = new ArrayList<>();
        //fill the list of attributes for each element
        for (String month : mWinterMonthArray) {
            map = new HashMap<>();
            map.put("monthName", month);
            childDataItemList.add(map);
        }
        childDataList.add(childDataItemList);

        childDataItemList = new ArrayList<>();
        for (String month : mSpringMonthArray) {
            map = new HashMap<>();
            map.put("monthName", month);
            childDataItemList.add(map);
        }
        childDataList.add(childDataItemList);

        childDataItemList = new ArrayList<>();
        for (String month : mAutumnMonthArray) {
            map = new HashMap<>();
            map.put("monthName", month);
            childDataItemList.add(map);
        }
        childDataList.add(childDataItemList);

        childDataItemList = new ArrayList<>();
        for (String month : mSummerMonthArray) {
            map = new HashMap<>();
            map.put("monthName", month);
            childDataItemList.add(map);
        }
        childDataList.add(childDataItemList);


        //array of groups attributes to read
        String childFrom[] = new String[]{"monthName"};
        //array of ID of views' for groups' attributes
        int childTo[] = new int[]{android.R.id.text1};

//        Log.d("MyLog", String.valueOf(getResources().getConfiguration().orientation));

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {

            Toast.makeText(this, String.valueOf(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT), Toast.LENGTH_SHORT).show();
            SimpleExpandableListAdapter simpleExpandableListAdapter = new SimpleExpandableListAdapter(
                    this, groupDataList, android.R.layout.simple_expandable_list_item_1,
                    groupFrom, groupTo, childDataList, android.R.layout.simple_expandable_list_item_1,
                    childFrom, childTo);
            ExpandableListView expandableListView1 = (ExpandableListView) findViewById(R.id.expandable_list_view);
            expandableListView1.setAdapter(simpleExpandableListAdapter);

        } else {

            ArrayList<Map<String, String>> groupDataList1 = new ArrayList<>();
            ArrayList<Map<String, String>> groupDataList2 = new ArrayList<>();
            ArrayList<ArrayList<Map<String, String>>> childDataList1 = new ArrayList<>();
            ArrayList<ArrayList<Map<String, String>>> childDataList2 = new ArrayList<>();

            separateList(groupDataList, groupDataList1, groupDataList2);
            separateList(childDataList, childDataList1, childDataList2);

            SimpleExpandableListAdapter simpleExpandableListAdapter1 = new SimpleExpandableListAdapter(
                    this, groupDataList1, android.R.layout.simple_expandable_list_item_1,
                    groupFrom, groupTo, childDataList1, android.R.layout.simple_expandable_list_item_1,
                    childFrom, childTo);
            SimpleExpandableListAdapter simpleExpandableListAdapter2 = new SimpleExpandableListAdapter(
                    this, groupDataList2, android.R.layout.simple_expandable_list_item_1,
                    groupFrom, groupTo, childDataList2, android.R.layout.simple_expandable_list_item_1,
                    childFrom, childTo);

            ExpandableListView expandableListView1 = (ExpandableListView) findViewById(R.id.expandable_list_view);
            expandableListView1.setAdapter(simpleExpandableListAdapter1);

            ExpandableListView expandableListView2 = (ExpandableListView) findViewById(R.id.expandable_list_view2);
            expandableListView2.setAdapter(simpleExpandableListAdapter2);

        }


    }

    private <T> void separateList(ArrayList<T> dataList, ArrayList<T> dataList1, ArrayList<T> dataList2) {
        for(int i = 0; i < dataList.size() / 2; i++) {
            dataList1.add(dataList.get(i));
        }
        for (int j = dataList.size() / 2;j < dataList.size(); j++) {
            dataList2.add(dataList.get(j));
        }
    }

}
