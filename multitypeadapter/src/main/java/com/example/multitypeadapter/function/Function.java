package com.example.multitypeadapter.function;

/**
 * Created by xuduokai on 2017/2/3.
 */

public interface Function<T, R> {
    //BaseViewHolder apply(View itemView);

    R apply(T t);
}
