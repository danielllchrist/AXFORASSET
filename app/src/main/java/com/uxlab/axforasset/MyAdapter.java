package com.uxlab.axforasset;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    Context context;
    ArrayList<Asset> assets;

    public MyAdapter(Context context, ArrayList<Asset> assets) {
        this.context = context;
        this.assets = assets;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.asset_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameView.setText(assets.get(position).getName());
        holder.shortDescView.setText(assets.get(position).getShortDesc());
        holder.imageView.setImageResource(assets.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return assets.size();
    }
}
