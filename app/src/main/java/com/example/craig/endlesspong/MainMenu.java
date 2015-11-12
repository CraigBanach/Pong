package com.example.craig.endlesspong;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

public class MainMenu extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main_menu);

    VideoView mainVideoView = (VideoView) findViewById(R.id.mainPageVideoView);

    MediaPlayer mainVideoPlayer = MediaPlayer.create(this, +R.raw.documentariesandyou);

    mainVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.documentariesandyou));


    mainVideoView.setZOrderMediaOverlay(true);
    mainVideoView.setZOrderOnTop(true);

    mainVideoView.start();
  }
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);

  }
}
