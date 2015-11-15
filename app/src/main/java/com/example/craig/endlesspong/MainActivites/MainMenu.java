package com.example.craig.endlesspong.MainActivites;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import com.example.craig.endlesspong.R;
import com.example.craig.endlesspong.game.OnePlayer;

import static android.view.View.*;

public class MainMenu extends AppCompatActivity implements OnClickListener {

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
    // mainVideoView.setZOrderOnTop(true);
    mainVideoView.start();

    // set the font.
    Typeface type = Typeface.createFromAsset(getAssets(),"fonts/CostaRica.ttf");
    TextView nameTitleScreen = (TextView) findViewById(R.id.nameTitleScreen);
    TextView oneTextView = (TextView) findViewById(R.id.oneTextView);
    TextView twoTextView = (TextView) findViewById(R.id.twoTextView);
    TextView optionsTextView = (TextView) findViewById(R.id.optionsTextView);

    nameTitleScreen.setOnClickListener(titleHandler);
    oneTextView.setOnClickListener(oneHandler);
    twoTextView.setOnClickListener(twoHandler);
    optionsTextView.setOnClickListener(optionsHandler);

    nameTitleScreen.setTypeface(type);
    oneTextView.setTypeface(type);
    twoTextView.setTypeface(type);
    optionsTextView.setTypeface(type);
  }

  View.OnClickListener titleHandler = new View.OnClickListener() {
    public void onClick(View v) {
      // Show credits/about.
    }
  };

  View.OnClickListener oneHandler = new View.OnClickListener() {
    public void onClick(View v) {
      Intent start1PGame = new Intent(MainMenu.this, OnePlayer.class);
      MainMenu.this.startActivity(start1PGame);
    }
  };

  View.OnClickListener twoHandler = new View.OnClickListener() {
    public void onClick(View v) {
      Toast.makeText(MainMenu.this, "Two", Toast.LENGTH_LONG ).show();
    }
  };

  View.OnClickListener optionsHandler = new View.OnClickListener() {
    public void onClick(View v) {
      Toast.makeText(MainMenu.this, "Options", Toast.LENGTH_LONG ).show();
    }
  };

  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
  }
  @Override
  public void onClick(View v) {

  }
}
