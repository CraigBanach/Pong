package com.example.craig.endlesspong.game;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.craig.endlesspong.R;

/**
 * This activity sets up the game for a single palyer, before passing to the main game activity.
 * Created by craig on 15/11/2015.
 */
public class OnePlayer extends AppCompatActivity {

  public static final byte ONE = 0, X = 0;
  public static final byte TWO = 1, Y = 1;
  public static final byte MSPERUPDATE = 20;
  public static final byte PADDLEJUMP = 10;
  public static final String TAG = Activity.class.getSimpleName();
  GameType mGameType;
  float dpHeight;
  float dpWidth;
  boolean eventHappened;
  int paddleLocation[] = new int[2];
  byte touching = 0;
  private Direction[] event = new Direction[2];

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.oneplayer);

    DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

    dpHeight = displayMetrics.heightPixels;
    dpWidth = displayMetrics.widthPixels;

    mGameType = GameType.ONEPLAYER;
    paddleLocation[ONE] = 0;
    paddleLocation[TWO] = 0;

  }

  @Override
  protected void onResume() {
    super.onResume();

    Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show();
    //gameLoop();
  }

  public boolean onTouchEvent(MotionEvent event) {

    // This method checks to see if the user touches or releases, and keeps a record of # of
    // presses and where there was a press.

    if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_POINTER_2_DOWN || event.getAction() == MotionEvent.ACTION_POINTER_3_DOWN) {
      touching += 1;
      //code to remember touch
    } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_POINTER_1_UP || event.getAction() == MotionEvent.ACTION_POINTER_UP || event.getAction() == MotionEvent.ACTION_POINTER_2_UP || event.getAction() == MotionEvent.ACTION_POINTER_3_UP) {
      touching -= 1;
      //code to forget touch
    }


    Log.d(TAG, touching + " " + touching + " " + touching);
    /*float x = event.getX();
    float y = event.getY();
    handleTouch(x, y);
    return false;
    */
  }

  public void handleTouch(float x, float y) {

    // This method finds out which half/quarter of the screen was touched and registers a
    // detection for the game loop to act upon.

    //Toast.makeText(this, "Touched", Toast.LENGTH_LONG).show();

    eventHappened = true;

    switch (mGameType) {
      case ONEPLAYER:
        if (x <= dpWidth / 2) {
          event[ONE] = Direction.LEFT;
        } else if (x <= dpWidth) {
          event[ONE] = Direction.RIGHT;
        }
        break;
      case TWOPLAYER:
        if (x <= dpWidth / 2) {
          if (y <= dpHeight / 2) {
            event[ONE] = Direction.LEFT;
          } else if (y <= dpHeight) {
            event[TWO] = Direction.LEFT;
          }
        } else if (x <= dpWidth) {
          if (y <= dpHeight / 2) {
            event[ONE] = Direction.RIGHT;
          } else if (y <= dpHeight) {
            event[TWO] = Direction.RIGHT;
          }
        }
        break;
      default:
        eventHappened = false;
        break;
    }
  }

  private void gameLoop() {

    boolean gameIsRunning = true;
    double lag = 0.0;
    double previous = System.currentTimeMillis();

    while (gameIsRunning) {

      double current = System.currentTimeMillis();
      double elapsed = current - previous;
      previous = current;
      lag += elapsed;

      if (eventHappened) {
        updatePositions();
      }

      while (lag >= MSPERUPDATE) {

        pushPositions();
        lag -= MSPERUPDATE;
        eventHappened = false;
        paddleLocation[0] = 0;
        paddleLocation[TWO] = 0;
        event = null;

      }
    }
  }

  private void updatePositions() {

    for (byte i = 0; i < 2; i++) {
      switch (event[i]) {
        case LEFT:
          findNewPosition(i, Direction.LEFT);
          break;
        case RIGHT:
          findNewPosition(i, Direction.LEFT);
          break;
      }
    }
  }

  private void findNewPosition(byte player, Direction direction) {

    ImageView paddle = null;
    int location[] = new int[2];

    if (player == ONE) {
      paddle = (ImageView) findViewById(R.id.p1Paddle);
    } else if (player == TWO) {
      paddle = (ImageView) findViewById(R.id.p2Paddle);
    }

    paddle.getLocationOnScreen(location);
    paddleLocation[player] = location[X];

    switch (direction) {
      case LEFT:
        paddleLocation[player] = -PADDLEJUMP;
        break;
      case RIGHT:
        paddleLocation[player] = PADDLEJUMP;
        break;
      default:
        break;
    }
  }

  private void pushPositions() {

    ImageView paddle[] = new ImageView[2];
    //RelativeLayout layout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);

    paddle[ONE] = (ImageView) findViewById(R.id.p1Paddle);
    paddle[TWO] = (ImageView) findViewById(R.id.p2Paddle);

    paddle[ONE].setTranslationX(paddleLocation[ONE]);
    paddle[TWO].setTranslationX(paddleLocation[TWO]);

  }

  public enum GameType {ONEPLAYER, TWOPLAYER}

  public enum Direction {LEFT, RIGHT}

    /*
  double previous = getCurrentTime();
  double lag = 0.0;
  while (true)
  {
    double current = getCurrentTime();
    double elapsed = current - previous;
    previous = current;
    lag += elapsed;

    processInput();

    while (lag >= MS_PER_UPDATE)
    {
      update();
      lag -= MS_PER_UPDATE;
    }

    render();
  }
    */

}
