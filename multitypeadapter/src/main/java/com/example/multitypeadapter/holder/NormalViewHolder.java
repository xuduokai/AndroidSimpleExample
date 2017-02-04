package com.example.multitypeadapter.holder;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.multitypeadapter.adapter.MultiTypeAdapter;
import com.example.multitypeadapter.R;
import com.example.multitypeadapter.model.Normal;

/**
 * Created by xuduokai on 2017/2/3.
 */

public class NormalViewHolder extends BaseViewHolder<Normal> {
    public NormalViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setupView(Normal model, int position, MultiTypeAdapter adapter) {
        final TextView textView = (TextView) getView(R.id.normal_title);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(textView.getContext(), "normal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
