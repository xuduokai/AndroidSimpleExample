package com.example.multitypeadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.multitypeadapter.adapter.MultiTypeAdapter;
import com.example.multitypeadapter.model.Normal;
import com.example.multitypeadapter.model.One;
import com.example.multitypeadapter.model.Two;
import com.example.multitypeadapter.model.Visitable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecycler();
    }

    private void initRecycler() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<Visitable> models = new ArrayList<>();
        models.add(new One());
        models.add(new Two());
        models.add(new Normal());

        recyclerView.setAdapter(new MultiTypeAdapter(models));
    }
}
/**
 * 理一下逻辑：
 * 1、传一个 list 给 Adapter，代表最终展示的各个 View
 * 2、在 Adapter 的 onCreateViewHolder 中创建对应的 ViewHolder
 * */
