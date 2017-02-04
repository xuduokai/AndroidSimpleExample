package com.example.multitypeadapter.model;

import com.example.multitypeadapter.type.TypeFactory;

/**
 * Created by xuduokai on 2017/2/3.
 */
public class One implements Visitable {
    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
