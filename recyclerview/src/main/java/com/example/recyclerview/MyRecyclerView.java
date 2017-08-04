package com.example.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

/**
 * Created by xuduokai on 2017/8/1.
 */

public class MyRecyclerView extends RecyclerView {
    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

/*    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case ACTION_MOVE:
                LinearLayoutManager lm = (LinearLayoutManager) getLayoutManager();

                if (lm.findFirstVisibleItemPosition() == 0) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else if (lm.findFirstVisibleItemPosition() == getAdapter().getItemCount() - 1) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }*/
}
