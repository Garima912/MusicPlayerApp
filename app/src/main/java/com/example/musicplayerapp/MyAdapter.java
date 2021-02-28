package com.example.musicplayerapp;

import android.content.Context;
import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.net.Uri;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private ArrayList<Uri> songWikiList =  new ArrayList<>();
    private ArrayList<Uri> artistWikiList = new ArrayList<>();


    public MyAdapter(ArrayList<String> songTitles, ArrayList<String> songArtists, int[] thumbnails , ArrayList<Uri> videos ) {
        this.titles = songTitles;
        this.artists = songArtists;
        this.songThumbnails = thumbnails;
        this.videoLinks = videos;
        setUpSongWikiLinks();
        setUpArtistWikiLinks();
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

//        holder.songItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // create intent, open video link in browser
//                Log.i("CLICK", "song is clicked");
//                openWebURL(videoLinks,position);
////                Uri aUri = videoLinks.get(position) ;
////                Intent aIntent = new Intent(Intent.ACTION_VIEW);
////                aIntent.setData(aUri) ;
////                aIntent.addCategory(Intent.CATEGORY_BROWSABLE) ;
////                context.startActivity(aIntent);
//
//            }
//        });


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
            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnClickListener(this);

            //itemView.setOnLongClickListener(this.onCreateContextMenu());
        }

        @Override
        public void onClick(View v) {
            Log.i("CLICK", "song is clicked");
            openWebURL(videoLinks,getAdapterPosition());
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = new MenuInflater(v.getContext());
            inflater.inflate(R.menu.context_menu, menu );
            menu.getItem(0).setOnMenuItemClickListener(menuItemListener);
            menu.getItem(1).setOnMenuItemClickListener(menuItemListener);
            menu.getItem(2).setOnMenuItemClickListener(menuItemListener);
            Log.i("LONG_CLICK", "long click: create menu");
        }

        public final MenuItem.OnMenuItemClickListener menuItemListener = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.playMenu){
                    openWebURL(videoLinks,getAdapterPosition());  // same as a simple click - play song video
                }
                if(item.getItemId() == R.id.songWikiMenu){
                    openWebURL(songWikiList,getAdapterPosition());
                }
                if(item.getItemId() == R.id.artistWikiMenu){
                    openWebURL(artistWikiList,getAdapterPosition());
                }
                return true;
            }
        };

    }

    private void openWebURL(ArrayList<Uri> urlList,int position){
        Uri aUri = urlList.get(position) ;
        Intent aIntent = new Intent(Intent.ACTION_VIEW);
        aIntent.setData(aUri) ;
        aIntent.addCategory(Intent.CATEGORY_BROWSABLE) ;
        context.startActivity(aIntent);
    }


    private void setUpArtistWikiLinks() {
        artistWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Charlie_Puth"));
        artistWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Billie_Eilish"));
        artistWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Imagine_Dragons"));
        artistWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Taylor_Swift"));
        artistWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/The_Weeknd"));
        artistWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Benny_Blanco"));
        artistWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Alan_Walker_(music_producer)"));
        artistWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Lauv"));
        artistWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Selena_Gomez"));
        artistWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Maroon_5"));
        artistWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Maren_Morris"));
        artistWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/One_Direction"));
        artistWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/One_Direction"));
        artistWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Taylor_Swift"));

    }

    private void setUpSongWikiLinks() {
        songWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Attention_(Charlie_Puth_song)"));
        songWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Bad_Guy_(Billie_Eilish_song)"));
        songWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Believer_(Imagine_Dragons_song)"));
        songWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Blank_Space"));
        songWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Blinding_Lights"));
        songWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Eastside_(song)"));
        songWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Faded_(Alan_Walker_song)"));
        songWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/I_Like_Me_Better"));
        songWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Lose_You_to_Love_Me"));
        songWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Memories_(Maroon_5_song)"));
        songWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/The_Middle_(Zedd,_Maren_Morris_and_Grey_song)"));
        songWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Night_Changes"));
        songWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Story_of_My_Life_(One_Direction_song)"));
        songWikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Willow_(song)"));
    }

}
