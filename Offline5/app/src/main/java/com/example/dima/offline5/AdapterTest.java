package com.example.dima.offline5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterTest extends RecyclerView.Adapter<AdapterTest.MainHolder> {

    public static final int HEADER = 0;
    public static final int DEFAULT = 1;

    private ArrayList<String> list;
    private LayoutInflater mInflater;
    private OnClickTestAdapter mOnClickTestAdapter;

    public AdapterTest(Context context, ArrayList<String> list) {
        mInflater = LayoutInflater.from(context);
        this.list = list;
    }

    public void setOnClickTestAdapter(OnClickTestAdapter onClickTestAdapter) {
        this.mOnClickTestAdapter = onClickTestAdapter;
    }

    public void updateRemove(int position) {
        notifyItemRemoved(position);
    }

    public void updateList(ArrayList<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER:
                return new HeaderHolder(mInflater.inflate(R.layout.item_header, parent, false));
            case DEFAULT:
                return new DefaultHolder(mInflater.inflate(R.layout.item_view, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(final MainHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case HEADER:
                ((HeaderHolder) holder).mTextView.setText("HEADER");
                break;
            case DEFAULT:
                ((DefaultHolder) holder).mTextView.setText(list.get(position));
                ((DefaultHolder) holder).mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnClickTestAdapter.onClick(holder.getAdapterPosition());
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return HEADER;
            case 4:
                return HEADER;
            default:
                return DEFAULT;
        }
    }

    interface OnClickTestAdapter{
        void onClick(int position);
    }

    static class MainHolder extends RecyclerView.ViewHolder{

        public MainHolder(View itemView) {
            super(itemView);
        }
    }

    static class HeaderHolder extends MainHolder {

        TextView mTextView;
        View mView;

        public HeaderHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mTextView = (TextView) itemView.findViewById(R.id.text);
        }
    }

    static class DefaultHolder extends MainHolder {
        ImageView mImageView;
        TextView mTextView;
        View mView;

        public DefaultHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image);
            mTextView = (TextView) itemView.findViewById(R.id.text);
            mView = itemView;
        }
    }
//
//    static  class Holder extends RecyclerView.ViewHolder{
//
//        ImageView mImageView;
//        TextView mTextView;
//        View mView;
//
//        public Holder(View itemView) {
//            super(itemView);
//            mImageView = (ImageView) itemView.findViewById(R.id.image);
//            mTextView = (TextView) itemView.findViewById(R.id.text);
//            mView = itemView;
//        }
//    }
}
