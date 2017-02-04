package com.example.multitypeadapter.holder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.multitypeadapter.R;
import com.example.multitypeadapter.adapter.MultiTypeAdapter;
import com.example.multitypeadapter.model.One;

/**
 * Created by xuduokai on 2017/2/3.
 */

public class OneViewHolder extends BaseViewHolder<One> {
    private TextView mTextView;

    //之前的 ViewHolder 只是放置各种 View，现在我们将设置 ViewHolder 的方法也通过多态放进来了
    public OneViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.one_title);
    }

    @Override
    public void setupView(One model, int position, MultiTypeAdapter adapter) {
        mTextView.setTextColor(Color.RED);
    }
}
