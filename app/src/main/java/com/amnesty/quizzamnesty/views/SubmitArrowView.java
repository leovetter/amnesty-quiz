package com.amnesty.quizzamnesty.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.amnesty.quizzamnesty.R;

public class SubmitArrowView extends View {

    Canvas myCanvas = null;
    private boolean init = true;

    public SubmitArrowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("onDraw", "yes yes yes");
        super.onDraw(canvas);

        this.myCanvas = canvas;
        if(this.init) {
            this.drawCircle();
            this.init = false;
        } else  {
            this.drawArrow();
        }
    }

    public void drawCircle() {

        Log.i("drawCircle", "yes yes yes");

        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.colorSubmit));
        background.setStrokeWidth(4);
        background.setStyle(Paint.Style.STROKE);
        this.myCanvas.drawCircle(getWidth() / 2, getHeight() / 2, 100, background);

        Path path = new Path();
        path.moveTo(getWidth() / 2 - 40, getHeight() / 2 + 5);
        path.lineTo(getWidth() / 2, getHeight() / 2 + 50);
        path.lineTo(getWidth() / 2 + 40, getHeight() / 2 - 40);
        path.lineTo(getWidth() / 2 + 35, getHeight() / 2 - 45);
        path.lineTo(getWidth() / 2, getHeight() / 2 + 45);
        path.lineTo(getWidth() / 2 - 35, getHeight() / 2);
        path.close();
        this.myCanvas.drawPath(path, background);
    }

    public void drawArrow() {

        Log.i("drawArrow", "yes yes yes");

        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.colorSubmit));
        this.myCanvas.drawRect(100, 40, getWidth() - 160, getHeight() - 40, background);

        Path path = new Path();
        path.moveTo(getWidth() - 160, 0);
        path.lineTo(getWidth() - 160, getHeight());
        path.lineTo(getWidth() - 100, getHeight() / 2);
        path.close();
        this.myCanvas.drawPath(path, background);
    }

    public void circleToArrow() {

        Log.i("circleToArrow", this.myCanvas.toString());
//        this.myCanvas.drawColor(Color.BLACK);
        this.invalidate();
        // this.drawArrow();
    }

}
