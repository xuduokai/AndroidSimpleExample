package com.example.multitypeadapter.type;

import android.view.View;

import com.example.multitypeadapter.holder.BaseViewHolder;
import com.example.multitypeadapter.holder.NormalViewHolder;
import com.example.multitypeadapter.holder.OneViewHolder;
import com.example.multitypeadapter.holder.TwoViewHolder;
import com.example.multitypeadapter.model.Normal;
import com.example.multitypeadapter.model.One;
import com.example.multitypeadapter.model.Two;

/**
 * Created by xuduokai on 2017/2/3.
 */

public interface TypeFactory {
    int type(One one);

    int type(Two two);

    int type(Normal normal);

    BaseViewHolder createViewHolder(int type, View itemView);

    int getTypeLayoutId(Class c);
}

