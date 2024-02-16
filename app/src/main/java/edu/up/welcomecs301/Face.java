// Carolyn Sousa

package edu.up.welcomecs301;

import android.graphics.Canvas;
import java.util.Random;

public class Face {
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

    public Face() {
        randomize();
    }

    public void randomize() {
        Random random = new Random();
        skinColor = random.nextInt(0xFFFFFF + 1);
        eyeColor = random.nextInt(0xFFFFFF + 1);
        hairColor = random.nextInt(0xFFFFFF + 1);
        hairStyle = random.nextInt(5);
    }

    public void onDraw(Canvas canvas) {

    }
}
