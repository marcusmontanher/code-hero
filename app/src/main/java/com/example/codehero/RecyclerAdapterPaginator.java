package com.example.codehero;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapterPaginator extends RecyclerView.Adapter<RecyclerAdapterPaginator.MyViewHolder> {

    private Context context;
    private List<String> pages;
    private int selected;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvPage;

        public MyViewHolder(View view) {
            super(view);
            tvPage =  view.findViewById(R.id.textViewPaginator);
        }
    }


    public void setSelected(int selected) {
        this.selected = selected;
        notifyDataSetChanged();
    }

    public RecyclerAdapterPaginator(Context context, List<String> pages, int selected) {
        this.context = context;
        this.pages = pages;
        this.selected = selected;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_paginator, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String page = pages.get(position);
        holder.tvPage.setText(page);

        if(selected == position){
            holder.tvPage.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable._custom_circle_shape_paginator_selected, null));
            holder.tvPage.setTextColor(context.getColor(R.color.white));
        } else {
            holder.tvPage.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable._custom_circle_shape_paginator_unselected, null));
            holder.tvPage.setTextColor(context.getColor(R.color.red));
        }
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }
}
