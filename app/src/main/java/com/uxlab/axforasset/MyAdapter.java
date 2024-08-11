package com.uxlab.axforasset;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private Context context;
    private ArrayList<Asset> assets;
    private User user;

    public MyAdapter(Context context, ArrayList<Asset> assets, User user) {
        this.context = context;
        this.assets = assets;
        this.user = user;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.asset_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        cek function ini
        holder.nameView.setText(assets.get(position).getName());
        holder.shortDescView.setText(assets.get(position).getShortDesc());
        holder.imageView.setImageResource(assets.get(position).getImage());
        holder.learnMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GameAssetDetail.class);
                intent.putExtra("Image", assets.get(position).getImage());
                intent.putExtra("Name", assets.get(position).getName());
                intent.putExtra("LongDesc", assets.get(position).getLongDesc());
                intent.putExtra("user", user);
                intent.putExtra("assets", assets);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return assets.size();
    }
}
