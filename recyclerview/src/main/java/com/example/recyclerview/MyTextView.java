package com.example.recyclerview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by xuduokai on 2017/2/17.
 */

public class MyTextView extends TextView{
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void startAnimation(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(this,"alpha",1.0f,0.1f);
        animator.setDuration(10000);
        animator.start();
    }

}
