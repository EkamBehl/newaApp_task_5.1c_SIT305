package com.example.newaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class horizontalAdapter extends RecyclerView.Adapter<horizontalAdapter.ViewHolder> implements Serializable {

    ArrayList<newsArticles> horizontalViewers;
    private Context con;

    public horizontalAdapter(ArrayList<newsArticles> horizontalViewers, Context con) {
        this.horizontalViewers = horizontalViewers;
        this.con = con;
    }

    @NonNull
    @Override
    public horizontalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_recycler_fragment,parent,false);
        return new horizontalAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull horizontalAdapter.ViewHolder holder, int position) {
        newsArticles articles=horizontalViewers.get(position);
        Picasso.get().load(articles.getUrlToImage()).into(holder.img);
        holder.txt.setText(articles.getTitle());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args=new Bundle();
                args.putSerializable("articles",(Serializable) horizontalViewers);
                Intent sendNews=new Intent(con,newwsDescription.class);
                sendNews.putExtra("title",articles.getTitle());
                sendNews.putExtra("description",articles.getDescription());
                sendNews.putExtra("img",articles.getUrlToImage());
                sendNews.putExtra("con",articles.getContent());
                sendNews.putExtra("BUNDLE",args);
                con.startActivity(sendNews);
            }
        });

    }

    @Override
    public int getItemCount() {
        return horizontalViewers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt;
        private ImageView img;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txt=itemView.findViewById(R.id.topStoriesText);
            img=itemView.findViewById(R.id.topStories);
            RecyclerView recyclerView=itemView.findViewById(R.id.horizontalRecycler);
        }
    }
}
