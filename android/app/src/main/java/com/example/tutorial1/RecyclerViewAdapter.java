package com.example.tutorial1;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter {

    private static final String TAG = "RecyclerViewAdapter";

    public class ViewHolder extends RecyclerView.ViewHolder{


        CircleImageView image;

        TextView imageName;

        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
