package com.example.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashSet;

/**
 * Created by Administrator on 2016/7/18.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private static final long LIGHT_DURATION = 1500;
    //数据集
    private String[] mDataSet;

    private HashSet<String> holderObjectSet = new HashSet<>();

    public MyAdapter(String[] mDataSet) {
        super();
        this.mDataSet = mDataSet;
    }

    private void hideLight(View item) {
//        item.setAlpha(0.1f);
        item.animate().alpha(0.1f).setDuration(LIGHT_DURATION);
    }

    private void showLight(View item) {
//        item.setAlpha(1f);
        item.animate().alpha(1f).setDuration(LIGHT_DURATION);
    }


    int mmm=0;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), android.R.layout.simple_list_item_1, null);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideLight(view);
                mmm++;
                aaa =true;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.mTextView.setText(holder.hashCode() + " ");
        holder.mTextView.setText(position+"");

        showLight(holder.itemView);

        //查看一共有多少 Holder 对象
        boolean success = holderObjectSet.add(holder.hashCode() + "");
        if (!success) {
            Log.e("Track", "false : " + holder.hashCode());
        }
        if (position == mDataSet.length - 1) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String ss : holderObjectSet) {
                stringBuilder.append(ss);
                stringBuilder.append("\r\n");
            }
            Log.e("Track", stringBuilder.toString());
            Log.e("Track", holderObjectSet.size() + "");
        }
    }

    boolean aaa= false;

    @Override
    public int getItemCount() {
        return mDataSet.length+(aaa?1:0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }
    }
}