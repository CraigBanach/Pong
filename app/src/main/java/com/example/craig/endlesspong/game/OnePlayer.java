package com.example.craig.endlesspong.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import com.example.craig.endlesspong.R;

/**
 * This activity sets up the game for a single palyer, before passing to the main game activity.
 * Created by craig on 15/11/2015.
 */
public class OnePlayer extends AppCompatActivity {

  public static final byte ONE = 0;
  public static final byte TWO = 1;
  public static final byte MSPERUPDATE = 16;
  GameType mGameType;
  float dpHeight;
  float dpWidth;
  boolean eventHappened;
  private Direction[] event = new Direction[1];

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.oneplayer);

    DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

    dpHeight = displayMetrics.heightPixels / displayMetrics.density;
    dpWidth = displayMetrics.widthPixels / displayMetrics.density;
  }

  public boolean onTouchEvent(MotionEvent event) {
    float x = event.getX();
    float y = event.getY();
    handleTouch(x, y);
    return false;
  }

  public void handleTouch(float x, float y) {

    // This method finds out which half/quarter of the screen was touched and registers a
    // detection for the game loop to act upon.

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
    double previous = getCurrentTime();

    while (gameIsRunning) {

      double current = getCurrentTime();
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
        event[ONE] = null;
        event[TWO] = null;

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

    int currentPosition =
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
}}
