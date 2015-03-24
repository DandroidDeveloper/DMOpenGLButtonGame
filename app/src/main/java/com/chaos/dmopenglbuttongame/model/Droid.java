package com.chaos.dmopenglbuttongame.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;


public class Droid {

    private Bitmap bitmap;	// the actual bitmap
    private int x;			// the X coordinate
    private int y;			// the Y coordinate
    private boolean touched;	// if droid is touched/picked up
    private int DroidId;
    public Droid(Bitmap bitmap, int x, int y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
    }
    public int getDroidId(){
        return DroidId;
    }
    public void setID(int DroidId ){
    this.DroidId=DroidId;
    }
    public Bitmap getBitmap() {
        return bitmap;
    }
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    public void draw(Canvas canvas) {
        Bitmap resizedbitmap=Bitmap.createScaledBitmap(bitmap, 250, 250, true);
        canvas.drawBitmap(resizedbitmap, x - (resizedbitmap.getWidth() / 2), y - (resizedbitmap.getHeight() / 2), null);
    }

    /**
     * Method which updates the droid's internal state every tick
     */
    public void update() {

    }


    /**
     * Handles the {@link MotionEvent.ACTION_DOWN} event. If the event happens on the
     * bitmap surface then the touched state is set to <code>true</code> otherwise to <code>false</code>
     * @param eventX - the event's X coordinate
     * @param eventY - the event's Y coordinate
     */
    public void handleActionDown(int eventX, int eventY) {
        if (eventX >= (x - 250 / 2) && (eventX <= (x + 250/2))) {
            if (eventY >= (y - 250 / 2) && (eventY <= (y + 250 / 2))) {
                // droid touched
                setTouched(true);
            } else {
                setTouched(false);
            }
        } else {
            setTouched(false);
        }

    }
}