package com.byandfortechnologies.twistjam;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by William on 12/21/2015.
 */
public class BoardView extends View {

    private Path mPath;
    private Paint mPaint;
    Context context;


    public BoardView(Context context, AttributeSet attrs) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();
        Log.d("x+y: ", width + "+" + height);
        context = context;
        // we set a new Path
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        int myColor =
                ContextCompat.getColor(context, R.color.light_gray);
        mPaint.setAlpha(0x40);
        float radius = 30.0f;
        CornerPathEffect corEffect = new CornerPathEffect(radius);
        mPaint.setColor(myColor);
        mPaint.setPathEffect(corEffect);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);

        mPath.reset(); // only needed when reusing this path for a new build
        mPath.moveTo(width / 3, 20); // used for first point
        mPath.lineTo(width * 2 / 3, 20);
        mPath.lineTo(width * 4 / 5, height - 10);
        mPath.lineTo(width / 5, height - 10);
        mPath.lineTo(width / 3, 20);
        mPath.close();

        canvas.drawPath(mPath, mPaint);
        float rectWideth = (width / 3 - 60) / 11;
        float rectHeight = (width / 3 - 60) / 32;
        float upWidth  = width / 3 - 60;
        float upStart = width / 3 + 30;

        float downWidth = width * 3 / 5 - 60;
        float downStart = width / 5 + 30;
        float downRectWidth = downWidth / 11;
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(width / 3 + 30, 20, width / 3 + 30 + rectWideth, 20 + rectHeight, mPaint);
        canvas.drawRect(width / 3 + 30 + rectWideth * 2, 20, width / 3 + 30 + rectWideth * 3, 20 + rectHeight, mPaint);
        canvas.drawRect(width / 3 + 30 + rectWideth * 4, 20, width / 3 + 30 + rectWideth * 5, 20 + rectHeight, mPaint);
        canvas.drawRect(width / 3 + 30 + rectWideth * 6, 20, width / 3 + 30 + rectWideth * 7, 20 + rectHeight, mPaint);
        canvas.drawRect(width / 3 + 30 + rectWideth * 8, 20, width / 3 + 30 + rectWideth * 9, 20 + rectHeight, mPaint);
        canvas.drawRect(width / 3 + 30 + rectWideth * 10, 20, width / 3 + 30 + rectWideth * 11, 20 + rectHeight, mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(10f);
        float[] pts = {upStart + rectWideth / 2, 20 + rectHeight, downStart + downRectWidth / 2, height - 10,
                upStart + rectWideth * 5 / 2, 20 + rectHeight, downStart + downRectWidth * 5 / 2, height - 10,
                upStart + rectWideth * 9 / 2, 20 + rectHeight, downStart + downRectWidth * 9 / 2, height - 10,
                upStart + rectWideth * 13/ 2, 20 + rectHeight, downStart + downRectWidth * 13 / 2, height - 10,
                upStart + rectWideth * 17 / 2, 20 + rectHeight, downStart + downRectWidth * 17 / 2, height - 10,
                upStart + rectWideth * 21 / 2, 20 + rectHeight, downStart + downRectWidth * 21 / 2, height - 10};
        canvas.drawLines(pts, mPaint);
    }

}
