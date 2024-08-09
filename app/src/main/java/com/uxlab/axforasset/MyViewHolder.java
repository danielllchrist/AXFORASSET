package com.uxlab.axforasset;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameView, shortDescView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.assets_image);
            nameView = itemView.findViewById(R.id.assets_name);
            shortDescView = itemView.findViewById(R.id.assets_description);
        }
}
