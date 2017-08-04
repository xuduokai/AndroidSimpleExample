package com.xu.clipimage;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView image;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();

        int height = bitmap.getHeight() / 8;
        Bitmap a = Bitmap.createBitmap(bitmap, 0, height, bitmap.getWidth(), bitmap.getHeight() - 2 * height);
        image.setImageBitmap(a);
        bitmap.recycle();
    }
}
