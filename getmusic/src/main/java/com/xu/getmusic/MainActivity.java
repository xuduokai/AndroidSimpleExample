package com.xu.getmusic;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText ed;

    AudioManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showContacts();

        List<MusicLoader.MusicInfo> list = MusicLoader.instance(getContentResolver()).getMusicList();
        for (MusicLoader.MusicInfo info : list) {
            Log.e("Track", "onCreate: " + info.getUrl());
        }

//        ed = (EditText) findViewById(R.id.ed);
//        ed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showStandardKeyboard();
//            }
//        });
//
//        manager = (AudioManager) this
//                .getSystemService(Context.AUDIO_SERVICE);

        View c = findViewById(R.id.ed);

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(c, View.ALPHA, 0.2f, 1f);
        ObjectAnimator transAnimator = ObjectAnimator.ofFloat(c, View.TRANSLATION_X, -130, 1f);
        AnimatorSet showGiftSet = new AnimatorSet();
        showGiftSet.setDuration(200);
        showGiftSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
//                doHit(itemId, giftItem);

            }
        });
        showGiftSet.playTogether(alphaAnimator, transAnimator);
        showGiftSet.start();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                manager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_RAISE,
                        AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Log.e("Track", "onKeyDown: ");
                manager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_LOWER,
                        AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
                return true;

            default:
                return super.onKeyDown(keyCode, event);
        }
    }


    public void showStandardKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(ed, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        }
    }
}
