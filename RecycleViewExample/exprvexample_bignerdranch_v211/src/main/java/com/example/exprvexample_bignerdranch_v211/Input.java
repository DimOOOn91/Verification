package com.example.exprvexample_bignerdranch_v211;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Input implements ParentListItem {

    private String mName;
    private List<Date> mChildrenList;

    public Input(String name) {
        mName = name;
        mChildrenList = new ArrayList<>();
    }

    @Override
    public List<Date> getChildItemList() {
        return mChildrenList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public void addChildren(Date inputChild) {
        mChildrenList.add(inputChild);
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Date> getChildrenList() {
        return mChildrenList;
    }

    public void setChildrenList(List<Date> childrenList) {
        mChildrenList = childrenList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Input input = (Input) o;

        if (mName != null ? !mName.equals(input.mName) : input.mName != null) return false;
        return mChildrenList != null ? mChildrenList.equals(input.mChildrenList) : input.mChildrenList == null;

    }

    @Override
    public int hashCode() {
        int result = mName != null ? mName.hashCode() : 0;
        result = 31 * result + (mChildrenList != null ? mChildrenList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Input{" +
                "mName='" + mName + '\'' +
                ", mChildrenList=" + mChildrenList +
                '}';
    }
}
