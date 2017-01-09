package com.example.exprvexample;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.ArrayList;
import java.util.List;


public class Crime implements ParentObject {

    private String mTitle;
    private List<Object> mChildrenList;

    public Crime(String title) {
        mTitle = title;
        mChildrenList = new ArrayList<>();
    }

    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList.addAll(list);
    }

    public void addChild(Object o) {
        mChildrenList.add(o);
    }

    public String getTitle() {
        return mTitle;
    }
}
