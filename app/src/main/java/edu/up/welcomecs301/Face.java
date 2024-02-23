// Carolyn Sousa

package edu.up.welcomecs301;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import java.util.Random;

public class Face {
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

    private static final String[] HAIR_STYLES = {"Short", "Medium", "Long"};

    public Face() {
        randomize();
    }

    public void randomize() {
        Random random = new Random();
        skinColor = random.nextInt(0xFFFFFF + 1);
        eyeColor = random.nextInt(0xFFFFFF + 1);
        hairColor = random.nextInt(0xFFFFFF + 1);
        hairStyle = random.nextInt(HAIR_STYLES.length);
    }

    public int getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(int skinColor) {
        this.skinColor = skinColor;
    }

    public int getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(int eyeColor) {
        this.eyeColor = eyeColor;
    }

    public int getHairColor() {
        return hairColor;
    }

    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
    }

    public int getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(int hairStyle) {
        this.hairStyle = hairStyle;
    }

    public static String[] getHairStyles() {
        return HAIR_STYLES;
    }


    public void onDraw(Canvas canvas, int viewWidth, int viewHeight) {
        // Draw hair
        Paint hairPaint = new Paint();
        hairPaint.setColor(hairColor);

        // Calculate hair position and size
        int hairTop = viewHeight / 2 - 200;
        int hairBottom = viewHeight / 2 + 100;
        int hairWidth = 350;

        if (hairStyle == 1) {
            hairBottom = viewHeight / 2 + 150;
        } else if (hairStyle == 2) {
            hairBottom = viewHeight / 2 + 175;
        }

        // Draw hair rectangle
        canvas.drawRect(new RectF(viewWidth / 2 - hairWidth / 2, hairTop,
                viewWidth / 2 + hairWidth / 2, hairBottom), hairPaint);

        // Draw face shape
        Paint facePaint = new Paint();
        facePaint.setColor(skinColor);
        canvas.drawCircle(viewWidth / 2, viewHeight / 2, 150, facePaint);

        // Draw eyes
        Paint eyePaint = new Paint();
        eyePaint.setColor(eyeColor);
        canvas.drawCircle(viewWidth / 2 - 75, viewHeight / 2 - 50, 15, eyePaint);
        canvas.drawCircle(viewWidth / 2 + 75, viewHeight / 2 - 50, 15, eyePaint);
    }
}