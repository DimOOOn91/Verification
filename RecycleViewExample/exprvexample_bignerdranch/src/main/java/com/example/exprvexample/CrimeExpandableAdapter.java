package com.example.exprvexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

public class CrimeExpandableAdapter extends ExpandableRecyclerAdapter<CrimeParentViewHolder, CrimeChildViewHolder> {

    private LayoutInflater mInflater;

    public CrimeExpandableAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CrimeParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.list_item_crime_parent,viewGroup, false);
        return new CrimeParentViewHolder(view);
    }

    @Override
    public CrimeChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.list_item_crime_child, viewGroup, false);
        return new CrimeChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(CrimeParentViewHolder crimeParentViewHolder, int i, Object o) {
        Crime crime = (Crime) o;
        crimeParentViewHolder.mCrimeTitleTextView.setText(crime.getTitle());
    }

    @Override
    public void onBindChildViewHolder(CrimeChildViewHolder crimeChildViewHolder, int i, Object o) {
        CrimeChild crimeChild = (CrimeChild) o;
        crimeChildViewHolder.mCrimeDateText.setText(String.format("%1$tD %1$tl:%1$tM (%1$tp)", crimeChild.getDate()));
        crimeChildViewHolder.mCrimeSolvedCheckBox.setChecked(crimeChild.isSolved());
    }
}
