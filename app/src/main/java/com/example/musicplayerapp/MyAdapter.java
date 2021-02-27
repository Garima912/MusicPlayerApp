package com.example.musicplayerapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<String> titles;
    private ArrayList<String> artists;
    private int[] songThumbnails;
    private ArrayList<Uri> videoLinks ;
    Context context;



    public MyAdapter(ArrayList<String> songTitles, ArrayList<String> songArtists, int[] thumbnails , ArrayList<Uri> videos ) {
        this.titles = songTitles;
        this.artists = songArtists;
        this.songThumbnails = thumbnails;
        this.videoLinks = videos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(R.layout.song_view, parent, false);
        return new MyViewHolder(songView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.songTitle.setText(titles.get(position));
        holder.artistName.setText(artists.get(position));
        holder.songImageView.setImageResource(songThumbnails[position]);

        holder.songItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create intent, open video link in browser
                Log.i("CLICK", "song is clicked");
                Uri aUri = videoLinks.get(position) ;
                Intent aIntent = new Intent(Intent.ACTION_VIEW);
                aIntent.setData(aUri) ;
                aIntent.addCategory(Intent.CATEGORY_BROWSABLE) ;
                context.startActivity(aIntent);

            }
        });


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
        ConstraintLayout songItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            songImageView = (ImageView) itemView.findViewById(R.id.songImage);
            songTitle = (TextView) itemView.findViewById(R.id.titleTxt);
            artistName = (TextView) itemView.findViewById(R.id.artistTxt);
            songItem = itemView.findViewById(R.id.item_constraintLayout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.i("ON_CLICK", "playing song");
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }
    }
}
