package com.example.exprvexample_bignerdranch_v211;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import java.util.Date;
import java.util.List;


public class InputExpandableAdapter
        extends ExpandableRecyclerAdapter<InputExpandableAdapter.InputViewHolder,
        InputExpandableAdapter.InputChildViewHolder> {

    private LayoutInflater mInflater;

    public InputExpandableAdapter(Context context, @NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public InputViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View inputView = mInflater.inflate(android.R.layout.simple_expandable_list_item_1, parentViewGroup, false);
        return new InputViewHolder(inputView);
    }

    @Override
    public InputChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View childView = mInflater.inflate(android.R.layout.simple_list_item_1, childViewGroup, false);
        return new InputChildViewHolder(childView);
    }

    @Override
    public void onBindParentViewHolder(InputViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        Input item = (Input) parentListItem;
        parentViewHolder.bind(item);
    }

    @Override
    public void onBindChildViewHolder(InputChildViewHolder childViewHolder, int position, Object childListItem) {
        Date inputChild = (Date) childListItem;
        childViewHolder.bind(inputChild);
    }

    public class InputViewHolder extends ParentViewHolder {

        private TextView mTitle;

        public InputViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(android.R.id.text1);
        }

        public void bind(Input input) {
            mTitle.setText(input.getName());
            mTitle.setTypeface(null, Typeface.BOLD);
        }
    }

    public class InputChildViewHolder extends ChildViewHolder {

        private TextView mText;

        public InputChildViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(android.R.id.text1);
        }

        public void bind(Date inputChild) {

            mText.setText(String.format("%1$tD %1$tl:%1$tM:%1$tS (%1$tp)", inputChild));
        }
    }
}
