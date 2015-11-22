package com.example.craig.endlesspong.game;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.craig.endlesspong.R;

import java.util.concurrent.TimeUnit;

/**
 * This activity sets up the game for a single palyer, before passing to the main game activity.
 * Created by craig on 15/11/2015.
 */
public class OnePlayer extends AppCompatActivity {

  public static final byte SIZEOFPADDLE = 100;
  public static final byte ONE = 0, X = 0;
  public static final byte TWO = 1, Y = 1;
  public static final int NSPERUPDATE = 20000;
  public static final String TAG = Activity.class.getSimpleName();
  GameType mGameType;
  float dpHeight;
  float dpWidth;
  ImageView paddleImageView[] = new ImageView[2];

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.oneplayer);

    DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

    dpHeight = displayMetrics.heightPixels;
    dpWidth = displayMetrics.widthPixels;

    mGameType = GameType.TWOPLAYER;
    paddleImageView[ONE] = (ImageView) findViewById(R.id.p1Paddle);
    paddleImageView[TWO] = (ImageView) findViewById(R.id.p2Paddle);

  }

  public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);

    gameLoop();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {

    byte number = (byte) event.getPointerCount();
    float x[] = new float[number];
    float y[] = new float[number];

    for (int i = 0; i < number; i++) {
      x[i] = event.getX(i);
      y[i] = event.getY(i);
      handleTouch(x[i], y[i]);
    }

    Log.d(TAG, x + " " + y);
    return false;
  }

  public void handleTouch(float x, float y) {


    switch (mGameType) {
      case TWOPLAYER:
        if (y < dpHeight / 2) {
          paddleImageView[TWO].setX(x - SIZEOFPADDLE);
          break; // break the switch, not the if.
        }
        // fall through if y < dpHeight/2 as this means it is moving p1's paddle
      case ONEPLAYER:
        paddleImageView[ONE].setX(x - SIZEOFPADDLE);
        break;

    }

    // This method finds out which half/quarter of the screen was touched and registers a
    // detection for the game loop to act upon.

    //Toast.makeText(this, "Touched", Toast.LENGTH_LONG).show();



  }

  private void gameLoop() {



    boolean gameIsRunning = true;
    long lastTickTime = System.nanoTime();
    long timeDiff;
    long currentTime;

    while (gameIsRunning) {

      currentTime = System.nanoTime();
      timeDiff = lastTickTime + NSPERUPDATE - currentTime;

      if (timeDiff > 0) {
        try {
          TimeUnit.NANOSECONDS.sleep(timeDiff);
        } catch (InterruptedException e) {
          Log.d(TAG, String.valueOf(e));
          return;
        }
      }

      Log.d(TAG, String.valueOf(System.nanoTime()));
      // check if ball collided
      //if ball collided work out new velocity vector

      // update ball position
      //render

    }
  }

  public enum GameType {ONEPLAYER, TWOPLAYER}
}
