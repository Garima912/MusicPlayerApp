package com.example.musicplayerapp;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<String> titles;
    private ArrayList<String> artists;
    private int[] songThumbnails;


    public MyAdapter(ArrayList<String> songTitles, ArrayList<String> songArtists, int[] thumbnails ) {
        this.titles = songTitles;
        this.artists = songArtists;
        this.songThumbnails = thumbnails;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(R.layout.song_view, parent, false);
        return new MyViewHolder(songView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.songTitle.setText(titles.get(position));
        holder.artistName.setText(artists.get(position));
        holder.songImageView.setImageResource(songThumbnails[position]);
    }

    @Override
    public int getItemCount() {
        return songThumbnails.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{

        ImageView songImageView;
        TextView songTitle;
        TextView artistName;
        View itemView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            songImageView = (ImageView) itemView.findViewById(R.id.songImage);
            songTitle = (TextView) itemView.findViewById(R.id.titleTxt);
            artistName = (TextView) itemView.findViewById(R.id.artistTxt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.i("ON_CLICK", "item was clicked !");
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }
    }
}
