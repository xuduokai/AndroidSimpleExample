package com.example.multitypeadapter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.multitypeadapter.holder.BaseViewHolder;
import com.example.multitypeadapter.model.Visitable;
import com.example.multitypeadapter.type.TypeFactory;
import com.example.multitypeadapter.type.TypeFactoryForList;


import java.util.List;

/**
 * Created by xuduokai on 2017/2/3.
 */

public class MultiTypeAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private TypeFactory typeFactory;
    private List<Visitable> models;

    public MultiTypeAdapter(List<Visitable> models) {
        this.typeFactory = new TypeFactoryForList();
        this.models = models;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = View.inflate(context, viewType, null);
        return typeFactory.createViewHolder(viewType, itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setupView(models.get(position), position, this);
    }

    @Override
    public int getItemCount() {
        if (models == null) {
            return 0;
        }

        return models.size();
    }

    /*这个函数本质上是想返回 layout 的 ID 值
    * models 对应的是我们的 OVideo,Publisher,OAlbum 这些类*/

    @Override
    public int getItemViewType(int position) {
//        return models.get(position).type(typeFactory);
        return typeFactory.getTypeLayoutId(models.get(position).getClass());
    }
}
