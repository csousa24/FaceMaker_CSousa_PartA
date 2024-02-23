package edu.up.welcomecs301;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

//https://developer.android.com/reference/android/view/SurfaceHolder
public class FaceView extends SurfaceView implements SurfaceHolder.Callback {
    private Face face;

    public FaceView(Context context) {
        super(context);
        init();
    }

    public FaceView(Context context, AttributeSet attrSet) {
        super(context, attrSet);
        init();
    }

    public FaceView(Context context, AttributeSet attrs, int styleAttr) {
        super(context, attrs, styleAttr);
        init();
    }

    private void init() {
        getHolder().addCallback(this);
        face = new Face();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawFace();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        drawFace();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    //surfaceView showing all black without lockCanvas and unlockCanvasAndPost?
    //https://stackoverflow.com/questions/3322144/what-does-lockcanvas-mean-elaborate#:~:text=lockCanvas%20%28%29%20creates%20a%20surface%20area%20that%20you,to%20the%20surface%20until%20your%20code%20is%20finished.
    private void drawFace() {
        Canvas canvas = getHolder().lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            face.onDraw(canvas, getWidth(), getHeight());
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    public void updateFace(Face newFace) {
        this.face = newFace;
        drawFace();
    }
}