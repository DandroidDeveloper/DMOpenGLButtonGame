package com.chaos.dmopenglbuttongame;

import com.chaos.dmopenglbuttongame.model.Droid;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Arrays;
import java.util.Collections;


/**
 * This is the main surface that handles the ontouch events and draws
 * the image to the screen.
 */
public class ButtonGame extends SurfaceView implements
        SurfaceHolder.Callback {
    private double totalElapsedTime=0.0;
    private int buttonsClicked=16;
    private static final String TAG = ButtonGame.class.getSimpleName();
    int count=0;
    private Activity activity;
    public boolean dialogIsDisplayed=false;
    private MainThread thread;
    private Droid droid0;
    private Droid droid1;
    private Droid droid2;
    private Droid droid3;
    private Droid droid4;
    private Droid droid5;
    private Droid droid6;
    private Droid droid7;
    private Droid droid8;
    private Droid droid9;
    private Droid droid10;
    private Droid droid11;
    private Droid droid12;
    private Droid droid13;
    private Droid droid14;
    private Droid droid15;

    // the fps to be displayed
    private String avgFps;
    public void setAvgFps(String avgFps) {
        this.avgFps = avgFps;
    }

        public ButtonGame(Context context) {
            super(context);
            activity=(Activity) context;
            // adding the callback (this) to the surface holder to intercept events
            getHolder().addCallback(this);
            Integer[] idArray = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
            Collections.shuffle(Arrays.asList(idArray));
            Integer[] tempImageList = new Integer[]{R.drawable.button1, R.drawable.button2, R.drawable.button3,
                    R.drawable.button4, R.drawable.button5, R.drawable.button6, R.drawable.button7, R.drawable.button8,
                    R.drawable.button9, R.drawable.button10, R.drawable.button11, R.drawable.button12, R.drawable.button13,
                    R.drawable.button14, R.drawable.button15, R.drawable.button16,};
            Integer[] myImageList = new Integer[16];
            for (int i = 0; i < idArray.length; i++) {
                myImageList[i] = tempImageList[idArray[i]];
            }

            // create droid and load bitmap
            droid0 = new Droid(BitmapFactory.decodeResource(getResources(), myImageList[0]), 150, 150);
            droid0.setID(idArray[0]);
            droid1 = new Droid(BitmapFactory.decodeResource(getResources(), myImageList[1]), 400, 150);
            droid1.setID(idArray[1]);
            droid2 = new Droid(BitmapFactory.decodeResource(getResources(), myImageList[2]), 650, 150);
            droid2.setID(idArray[2]);
            droid3 = new Droid(BitmapFactory.decodeResource(getResources(), myImageList[3]), 900, 150);
            droid3.setID(idArray[3]);
            droid4 = new Droid(BitmapFactory.decodeResource(getResources(), myImageList[4]), 150, 400);
            droid4.setID(idArray[4]);
            droid5 = new Droid(BitmapFactory.decodeResource(getResources(), myImageList[5]), 400, 400);
            droid5.setID(idArray[5]);
            droid6 = new Droid(BitmapFactory.decodeResource(getResources(), myImageList[6]), 650, 400);
            droid6.setID(idArray[6]);
            droid7 = new Droid(BitmapFactory.decodeResource(getResources(), myImageList[7]), 900, 400);
            droid7.setID(idArray[7]);
            droid8 = new Droid(BitmapFactory.decodeResource(getResources(), myImageList[8]), 150, 650);
            droid8.setID(idArray[8]);
            droid9 = new Droid(BitmapFactory.decodeResource(getResources(), myImageList[9]), 400, 650);
            droid9.setID(idArray[9]);
            droid10 = new Droid(BitmapFactory.decodeResource(getResources(), myImageList[10]), 650, 650);
            droid10.setID(idArray[10]);
            droid11 = new Droid(BitmapFactory.decodeResource(getResources(), myImageList[11]), 900, 650);
            droid11.setID(idArray[11]);
            droid12 = new Droid(BitmapFactory.decodeResource(getResources(), myImageList[12]), 150, 900);
            droid12.setID(idArray[12]);
            droid13 = new Droid(BitmapFactory.decodeResource(getResources(), myImageList[13]), 400, 900);
            droid13.setID(idArray[13]);
            droid14 = new Droid(BitmapFactory.decodeResource(getResources(), myImageList[14]), 650, 900);
            droid14.setID(idArray[14]);
            droid15 = new Droid(BitmapFactory.decodeResource(getResources(), myImageList[15]), 900, 900);
            droid15.setID(idArray[15]);
            // create the game loop thread

            thread = new MainThread(getHolder(), this);

            // make the GamePanel focusable so it can handle events
            setFocusable(true);

        }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // at this point the surface is created and
        // we can safely start the game loop
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        Log.d(TAG, "Surface is being destroyed");
        // tell the thread to shut down and wait for it to finish
        // this is a clean shutdown
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // try again shutting down the thread
            }

        }
        Log.d(TAG, "Thread was shut down cleanly");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // delegating event handling to the droid
            droid0.handleActionDown((int)event.getX(), (int)event.getY());
            droid1.handleActionDown((int)event.getX(), (int)event.getY());
            droid2.handleActionDown((int)event.getX(), (int)event.getY());
            droid3.handleActionDown((int)event.getX(), (int)event.getY());
            droid4.handleActionDown((int)event.getX(), (int)event.getY());
            droid5.handleActionDown((int)event.getX(), (int)event.getY());
            droid6.handleActionDown((int)event.getX(), (int)event.getY());
            droid7.handleActionDown((int)event.getX(), (int)event.getY());
            droid8.handleActionDown((int)event.getX(), (int)event.getY());
            droid9.handleActionDown((int)event.getX(), (int)event.getY());
            droid10.handleActionDown((int)event.getX(), (int)event.getY());
            droid11.handleActionDown((int)event.getX(), (int)event.getY());
            droid12.handleActionDown((int)event.getX(), (int)event.getY());
            droid13.handleActionDown((int)event.getX(), (int)event.getY());
            droid14.handleActionDown((int)event.getX(), (int)event.getY());
            droid15.handleActionDown((int) event.getX(), (int) event.getY());

            // check if in the lower part of the screen we exit
            if (event.getY() > getHeight() - 50) {
                thread.setRunning(false);
                ((Activity)getContext()).finish();
            } else {
                Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
            }
        } if (event.getAction() == MotionEvent.ACTION_UP) {
            if (droid0.isTouched()){
                if(droid0.getDroidId()==count) {
                    droid0.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.whitebutton1));
                    count=count+1;
                }
                droid0.setTouched(false);
                }
            }
            if (droid1.isTouched()) {
                if(droid1.getDroidId()==count) {
                    droid1.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.whitebutton1));
                    count=count+1;
                }
                droid1.setTouched(false);
            }
            if (droid2.isTouched()) {
                if(droid2.getDroidId()==count) {
                    droid2.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.whitebutton1));
                    count=count+1;
                }
                droid2.setTouched(false);
            }
            if (droid3.isTouched()) {
                if(droid3.getDroidId()==count) {
                    droid3.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.whitebutton1));
                    count=count+1;
                }
                droid3.setTouched(false);
            }
            if (droid4.isTouched()) {
                if(droid4.getDroidId()==count) {
                    droid4.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.whitebutton1));
                    count=count+1;
                }
                droid4.setTouched(false);
            }
            if (droid5.isTouched()) {
                if(droid5.getDroidId()==count) {
                    droid5.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.whitebutton1));
                    count=count+1;
                }
                droid5.setTouched(false);
            }
            if (droid6.isTouched()) {
                if(droid6.getDroidId()==count) {
                    droid6.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.whitebutton1));
                    count=count+1;
                }
                droid6.setTouched(false);
            }
            if (droid7.isTouched()) {
                if(droid7.getDroidId()==count) {
                    droid7.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.whitebutton1));
                    count=count+1;
                }
                droid7.setTouched(false);
            }
            if (droid8.isTouched()) {
                if(droid8.getDroidId()==count) {
                    droid8.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.whitebutton1));
                    count=count+1;
                }
                droid8.setTouched(false);
            }
            if (droid9.isTouched()) {
                if(droid9.getDroidId()==count) {
                    droid9.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.whitebutton1));
                    count=count+1;
                }
                droid9.setTouched(false);
            }
            if (droid10.isTouched()) {
                if(droid10.getDroidId()==count) {
                    droid10.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.whitebutton1));
                    count=count+1;
                }
                droid10.setTouched(false);
            }
            if (droid11.isTouched()) {
                if(droid11.getDroidId()==count) {
                    droid11.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.whitebutton1));
                    count=count+1;
                }
                droid11.setTouched(false);
            }
            if (droid12.isTouched()) {
                if(droid12.getDroidId()==count) {
                    droid12.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.whitebutton1));
                    count=count+1;
                }
                droid12.setTouched(false);
            }
            if (droid13.isTouched()) {
                if(droid13.getDroidId()==count) {
                    droid13.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.whitebutton1));
                    count=count+1;
                }
                droid13.setTouched(false);
            }
            if (droid14.isTouched()) {
                if(droid14.getDroidId()==count) {
                    droid14.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.whitebutton1));
                    count=count+1;
                }
                droid14.setTouched(false);
            }
            if (droid15.isTouched()) {
                if(droid15.getDroidId()==count) {
                    droid15.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.whitebutton1));
                    count=count+1;
                }
                droid15.setTouched(false);
            }
        return true;
        }



    public void render(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        droid0.draw(canvas);
        droid1.draw(canvas);
        droid2.draw(canvas);
        droid3.draw(canvas);
        droid4.draw(canvas);
        droid5.draw(canvas);
        droid6.draw(canvas);
        droid7.draw(canvas);
        droid8.draw(canvas);
        droid9.draw(canvas);
        droid10.draw(canvas);
        droid11.draw(canvas);
        droid12.draw(canvas);
        droid13.draw(canvas);
        droid14.draw(canvas);
        droid15.draw(canvas);
        // display fps
        displayFps(canvas, avgFps);
    }

    /**
     * This is the game update method. It iterates through all the objects
     * and calls their update method if they have one or calls specific
     * engine's update method.
     */
    public void update() {
        droid0.update();
        droid1.update();
        droid2.update();
        droid3.update();
        droid4.update();
        droid5.update();
        droid6.update();
        droid7.update();
        droid8.update();
        droid9.update();
        droid10.update();
        droid11.update();
        droid12.update();
        droid13.update();
        droid14.update();
        droid15.update();
        if (count==16){
            thread.setRunning(false);
            showGameOverDialog(R.string.gameover);
        }

        }

    private void showGameOverDialog(int messageId)
    {
        // create a dialog displaying the given String
        final AlertDialog.Builder dialogBuilder =
                new AlertDialog.Builder(getContext());
        dialogBuilder.setTitle(getResources().getString(messageId));
        dialogBuilder.setCancelable(false);

        // display number of shots fired and total time elapsed
        dialogBuilder.setMessage(getResources().getString(
                R.string.results_format, buttonsClicked, totalElapsedTime));
        dialogBuilder.setPositiveButton(R.string.reset_game,
                new DialogInterface.OnClickListener()
                {
                    // called when "Reset Game" Button is pressed
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent i=new Intent(getContext(), MainActivity.class);
                        activity.startActivity(i);
                        dialogIsDisplayed = false;
                    } // end method onClick
                } // end anonymous inner class
        ); // end call to setPositiveButton

        activity.runOnUiThread(
                new Runnable() {
                    public void run()
                    {
                        dialogIsDisplayed = true;
                        dialogBuilder.show(); // display the dialog
                    } // end method run
                } // end Runnable
        ); // end call to runOnUiThread
    } // end method showGameOverDialog

    private void displayFps(Canvas canvas, String fps) {
        if (canvas != null && fps != null) {
            Paint paint = new Paint();
            paint.setARGB(255, 0, 0, 0);
            canvas.drawText(fps, this.getWidth() - 50, 20, paint);
        }
    }


}