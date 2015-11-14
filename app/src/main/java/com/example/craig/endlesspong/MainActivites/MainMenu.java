package com.example.craig.endlesspong.MainActivites;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.craig.endlesspong.R;

import org.w3c.dom.Text;

public class MainMenu extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main_menu);

    initializeViews();

  }

  private void initializeViews() {
    // Initialize Videoview, find source and start playback.
    VideoView mainVideoView = (VideoView) findViewById(R.id.mainPageVideoView);
    mainVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.documentariesandyou));
    mainVideoView.setZOrderOnTop(true);
    mainVideoView.start();

    // Initialize the mainTextView and set the font.
    TextView nameTitleScreen = (TextView) findViewById(R.id.nameTitleScreen);
    Typeface type = Typeface.createFromAsset(getAssets(),"fonts/CostaRica.ttf");
    nameTitleScreen.setTypeface(type);
  }

  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);

  }
}
