package com.xu.getmusic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by xuduokai on 2017/6/21.
 */

public class TailorBar extends LinearLayout{

    //左侧的按钮,右侧的按钮
    private Button left_bt, right_bt;
    private TextView startTime;
    private TextView endTime;
    private RelativeLayout centreRela;
    private RelativeLayout.LayoutParams centre_layout;
    private SeekBar seekBar;

    private int recordWidth;
    private int rightMargin;
    private int leftMargin;

    public TailorBar(Context context) {
        super(context);
    }

    public TailorBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TailorBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        left_bt = (Button) findViewById(R.id.video_new_cut_txt_left);
        right_bt = (Button) findViewById(R.id.video_new_cut_txt_right);
        startTime = (TextView) findViewById(R.id.start_time);
        endTime = (TextView) findViewById(R.id.end_time);
        centreRela = (RelativeLayout) findViewById(R.id.video_new_cut_relative1);
        seekBar = (SeekBar) findViewById(R.id.music_progress_bar);
        seekBar.setEnabled(false);
    }
}
