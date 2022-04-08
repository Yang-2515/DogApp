package com.example.dogapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder>{

    private ArrayList<DogBreed> dogBreedArrayList;

    public DogAdapter(ArrayList<DogBreed> dogList) {
        dogBreedArrayList = dogList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewName.setText(dogBreedArrayList.get(position).getName());
        holder.textViewDes.setText(dogBreedArrayList.get(position).getOrigin());
        Picasso.get().load(dogBreedArrayList.get(position).getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dogBreedArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewName;
        public TextView textViewDes;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_avatar);
            textViewName = itemView.findViewById(R.id.tv_name);
            textViewDes = itemView.findViewById(R.id.tv_des);

        }
    }
}
