package com.example.multitypeadapter.holder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.example.multitypeadapter.adapter.MultiTypeAdapter;

/**
 * Created by xuduokai on 2017/2/3.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    private SparseArray<View> views;
    private View mItemView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        views = new SparseArray<>();
        this.mItemView = itemView;
    }

    public View getView(int resID) {
        View view = views.get(resID);

        if (view == null) {
            view = mItemView.findViewById(resID);
            views.put(resID, view);
        }

        return view;
    }

    //不同的ViewHolder继承BaseViewHolder并实现setUpView方法即可。
    //在这里，我们用多态替代了 instanceof 的判断（这个方法也是另一种表驱动法）。
    public abstract void setupView(T model, int position, MultiTypeAdapter adapter);
}
