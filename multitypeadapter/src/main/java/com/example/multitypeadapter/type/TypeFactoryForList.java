package com.example.multitypeadapter.type;

import android.view.View;

import com.example.multitypeadapter.R;
import com.example.multitypeadapter.function.Function;
import com.example.multitypeadapter.holder.BaseViewHolder;
import com.example.multitypeadapter.holder.NormalViewHolder;
import com.example.multitypeadapter.holder.OneViewHolder;
import com.example.multitypeadapter.holder.TwoViewHolder;
import com.example.multitypeadapter.model.Normal;
import com.example.multitypeadapter.model.One;
import com.example.multitypeadapter.model.Two;

import java.util.HashMap;

/**
 * Created by xuduokai on 2017/2/3.
 */
public class TypeFactoryForList implements TypeFactory {
    private final int TYPE_RESOURCE_ONE = R.layout.layout_item_one;
    private final int TYPE_RESOURCE_TWO = R.layout.layout_item_two;
    private final int TYPE_RESOURCE_NORMAL = R.layout.layout_item_normal;

    private HashMap<Integer, Function<View, BaseViewHolder>> holderMap = new HashMap<>();

    private HashMap<Class, Integer> layoutIdMap = new HashMap<>();

    public TypeFactoryForList() {
        initHolderMap();
        initLayoutIdMap();
    }

    private void initHolderMap() {
        //如果有 lambda 表达式，则可以简写为
        //holderMap.put(TYPE_RESOURCE_ONE, (itemView) -> new OneViewHolder(itemView));
        holderMap.put(TYPE_RESOURCE_ONE, new Function<View, BaseViewHolder>() {
            @Override
            public BaseViewHolder apply(View view) {
                return new OneViewHolder(view);
            }
        });

        holderMap.put(TYPE_RESOURCE_TWO, new Function<View, BaseViewHolder>() {
            @Override
            public BaseViewHolder apply(View itemView) {
                return new TwoViewHolder(itemView);
            }
        });

        holderMap.put(TYPE_RESOURCE_NORMAL, new Function<View, BaseViewHolder>() {
            @Override
            public BaseViewHolder apply(View itemView) {
                return new NormalViewHolder(itemView);
            }
        });
    }

    private void initLayoutIdMap() {
        layoutIdMap.put(One.class, TYPE_RESOURCE_ONE);
        layoutIdMap.put(Two.class, TYPE_RESOURCE_TWO);
        layoutIdMap.put(Normal.class, TYPE_RESOURCE_NORMAL);
    }

    @Override
    public BaseViewHolder createViewHolder(int type, View itemView) {

/*        //一般写法：
        if (TYPE_RESOURCE_ONE == type) {
            return new OneViewHolder(itemView);
        } else if (TYPE_RESOURCE_TWO == type) {
            return new TwoViewHolder(itemView);
        } else if (TYPE_RESOURCE_NORMAL == type) {
            return new NormalViewHolder(itemView);
        }*/

        //表驱动法
        Function<View, BaseViewHolder> function = holderMap.get(type);
        if (function == null) {
            return null;
        } else {
            return function.apply(itemView);
        }

    }

    @Override
    public int getTypeLayoutId(Class c) {
        if (layoutIdMap.get(c) == null) {
            return 0;
        } else {
            return layoutIdMap.get(c);
        }
    }
}
