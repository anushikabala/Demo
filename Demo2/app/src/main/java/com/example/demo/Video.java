package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edmt.dev.videoplayer.VideoPlayerRecyclerView;
import edmt.dev.videoplayer.adapter.VideoPlayerRecyclerAdapter;
import edmt.dev.videoplayer.model.MediaObject;
import edmt.dev.videoplayer.utils.VerticalSpacingItemDecorator;

public class Video extends AppCompatActivity {

    @BindView(R.id.vp)
    VideoPlayerRecyclerView vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        init();
    }
    private void init(){
        LinearLayoutManager layout= new LinearLayoutManager(this);
        vp.setLayoutManager(layout);
        VerticalSpacingItemDecorator verticalSpacingItemDecorator=new VerticalSpacingItemDecorator(10);
        vp.addItemDecoration(verticalSpacingItemDecorator);

        ArrayList<MediaObject> sourceVideos = new ArrayList(sampleVideoList());
        vp.setMediaObjects(sourceVideos);
        VideoPlayerRecyclerAdapter adapter = new VideoPlayerRecyclerAdapter(sourceVideos,initGlide());
        vp.setAdapter(adapter);
    }

    private RequestManager initGlide() {
        RequestOptions options= new RequestOptions().placeholder(R.drawable.white).error(R.drawable.white);
        return Glide.with(this).setDefaultRequestOptions(options);
    }

    private List<MediaObject> sampleVideoList(){
        return Arrays.asList(
                new MediaObject("Big Buck Bunny",
                      "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                      "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Big_Buck_Bunny_thumbnail_vlc.png/1200px-Big_Buck_Bunny_thumbnail_vlc.png",
                      "By Blender Foundation"),
                new MediaObject("Elephant Dream",
                        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                        "https://crystalclearintuition.com/wp-content/uploads/2020/07/spiritual-meaning-of-an-elephant-in-your-dreams-1.jpg",
                        "The first Blender Open Movie from 2006"),
                new MediaObject("For Bigger Blazes",
                        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
                        "https://d2z1w4aiblvrwu.cloudfront.net/ad/76Ab/google-chromecast-bigger-blazes-large-7.jpg",
                        "HBO GO now works with Chromecast -- the easiest way to enjoy online video on your TV. For when you want to settle into your Iron Throne to watch the latest episodes. For $35.\\nLearn how to use Chromecast with HBO GO and more at google.com/chromecast."),
                new MediaObject("Sintel",
                        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
                        "http://www.ethanluke.com/wp-content/uploads/2016/02/sintel.jpg",
                        "Sintel is an independently produced short film, initiated by the Blender Foundation")

        );
    }
}