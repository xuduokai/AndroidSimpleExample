package com.example.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

/**
 * Created by xuduokai on 2017/8/1.
 */

public class MyScrollView extends ScrollView {
    RecyclerView recyclerView;
    TextView mFoot;
    TextView mHead;
    private Scroller mScroller;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_vertical);
        mFoot = (TextView) findViewById(R.id.foot);
        mHead = (TextView) findViewById(R.id.head);
        mScroller = new Scroller(getContext());
    }

    //解决 ScrollView 和 RecyclerView 的滑动冲突，但不是那种典型的滑动冲突，
    // 网上有相关的解决办法，完全和一般意义上的滑动冲突不一样
/*    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        switch (ev.getAction()) {
            case ACTION_DOWN:
                intercept = super.onInterceptTouchEvent(ev);
                break;
            case ACTION_MOVE:
                if (isTop()|isBottom()) {
                    intercept = true;
                } else {
                    intercept = false;
                }
                break;
            case ACTION_UP:
                intercept = false;
                break;
        }
        return intercept;
    }*/


    //判断是否滑到上面或者下面
/*    private boolean isTop() {
        View firstChildView = recyclerView.getLayoutManager().getChildAt(0);
        int fistChildTop = firstChildView.getTop();
        int recyclerTop = recyclerView.getTop() - recyclerView.getPaddingTop();
        int firstPosition = recyclerView.getLayoutManager().getPosition(firstChildView);
        return fistChildTop == 0 && firstPosition == 0;
    }

    private boolean isBottom() {
        View lastChildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount() - 1);
        int lastChildBottom = lastChildView.getBottom();
        int recyclerBottom = recyclerView.getBottom() - recyclerView.getPaddingBottom();
        int lastPosition = recyclerView.getLayoutManager().getPosition(lastChildView);
        return lastChildBottom == recyclerView.getHeight() && lastPosition == recyclerView.getLayoutManager().getItemCount() - 1;
    }*/

    //弹性滑动上下两部分
/*    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (getScrollY() == 0) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScroller.startScroll(0, getScrollY(), 0, mHead.getHeight(), 1000);
                    invalidate();

                }
            }, 1000);
        }
        if (getScrollY() + getHeight() - getPaddingTop() - getPaddingBottom() == getChildAt(0).getHeight()) {
            // 小心踩坑2: 这里不能是 >=
            // 小心踩坑3（可能忽视的细节2）：这里最容易忽视的就是ScrollView上下的padding　
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScroller.startScroll(0, getScrollY(), 0, -mFoot.getHeight(), 1000);
                    invalidate();

                }
            }, 1000);
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }*/
}
