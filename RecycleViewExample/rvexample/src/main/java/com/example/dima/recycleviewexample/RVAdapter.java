package com.example.dima.recycleviewexample;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    private List<Person> mPersons;

    public RVAdapter(List<Person> persons) {
        mPersons = persons;
    }

    @Override
    public int getItemCount() {
        return mPersons.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        PersonViewHolder personViewHolder = new PersonViewHolder(view);
        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.mName.setText(mPersons.get(position).getName());
        holder.mPhone.setText(mPersons.get(position).getPhone());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView mCardView;
        TextView mName;
        TextView mPhone;

        public PersonViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.item_layout);
            mName = (TextView) mCardView.findViewById(R.id.main_text);
            mPhone = (TextView) mCardView.findViewById(R.id.main_text2);
        }
    }
}
