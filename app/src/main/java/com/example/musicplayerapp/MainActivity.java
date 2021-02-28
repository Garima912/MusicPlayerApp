package com.example.musicplayerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import java.sql.Time;
import java.util.Arrays;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int[] thumbnails = new int[]{R.drawable.attention, R.drawable.bad_guy, R.drawable.believer, R.drawable.blankspace,
            R.drawable.blindinglights, R.drawable.eastside, R.drawable.faded, R.drawable.likemebetter, R.drawable.loseyou,
            R.drawable.memories, R.drawable.middle, R.drawable.nightchanges, R.drawable.story_of_my_life, R.drawable.willow};

    private ArrayList<String> songTitles;
    private ArrayList<String>  artistNames;
    private TextView menuBarTxt;
    private RecyclerView recyclerView;
    private ArrayList<Uri> songVideoLinks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuBarTxt = (TextView) findViewById(R.id.menuTxt);

        setUpMenuBar();

        songTitles =  new ArrayList<>();
        artistNames = new ArrayList<>();
        songTitles.addAll(Arrays.asList(getResources().getStringArray(R.array.Song_Titles)));
        artistNames.addAll(Arrays.asList(getResources().getStringArray(R.array.Artist_Names)));
        setUpVideoLinks();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        MyAdapter adapter = new MyAdapter(songTitles,artistNames,thumbnails,songVideoLinks);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2)); //use this line to see as a grid
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //use this line to see as a standard vertical list


    }

    private void setUpVideoLinks() {

        songVideoLinks.add(Uri.parse("https://www.youtube.com/watch?v=nfs8NYg7yQM"));
        songVideoLinks.add(Uri.parse("https://www.youtube.com/watch?v=DyDfgMOUjCI"));
        songVideoLinks.add(Uri.parse("https://www.youtube.com/watch?v=7wtfhZwyrcc"));
        songVideoLinks.add(Uri.parse("https://www.youtube.com/watch?v=e-ORhEE9VVg"));
        songVideoLinks.add(Uri.parse("https://www.youtube.com/watch?v=4NRXx6U8ABQ"));
        songVideoLinks.add(Uri.parse("https://www.youtube.com/watch?v=56WBK4ZK_cw"));
        songVideoLinks.add(Uri.parse("https://www.youtube.com/watch?v=60ItHLz5WEA"));
        songVideoLinks.add(Uri.parse("https://www.youtube.com/watch?v=BcqxLCWn-CE"));
        songVideoLinks.add(Uri.parse("https://www.youtube.com/watch?v=zlJDTxahav0"));
        songVideoLinks.add(Uri.parse("https://www.youtube.com/watch?v=SlPhMPnQ58k"));
        songVideoLinks.add(Uri.parse("https://www.youtube.com/watch?v=M3mJkSqZbX4"));
        songVideoLinks.add(Uri.parse("https://www.youtube.com/watch?v=syFZfO_wfMQ"));
        songVideoLinks.add(Uri.parse("https://www.youtube.com/watch?v=W-TE_Ys4iwM"));
        songVideoLinks.add(Uri.parse("https://www.youtube.com/watch?v=RsEZmictANA"));
    }

    private void setUpMenuBar() {
        int currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if(currentTime >= 3 && currentTime < 12){
            menuBarTxt.setText("Good Morning");
        }
        if(currentTime >= 12 && currentTime <= 16){
            menuBarTxt.setText("Good Afternoon");
        }
        if(currentTime > 16 && currentTime <= 20){
            menuBarTxt.setText("Good Evening");
        }
        if(currentTime > 20 && currentTime < 3 ){
            menuBarTxt.setText("Good Night");
        }
    }
}