package com.example.lenovo.solar201807091602l.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.lenovo.solar201807091602l.R;

/**
 * Created by lenovo on 2018/7/9.
 */

public class MyViews extends View {
    Paint paint, mpaint, contentPaint;
    Bitmap bgBackGround, mbgBackGround;
    Canvas mcanvas;
    Path path;


    public MyViews(Context context) {
        super(context);
        init(context, null);
    }

    public MyViews(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public MyViews(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        //初始化画笔
        paint = new Paint();
        paint.setAlpha(0);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(50);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

        path = new Path();
        bgBackGround = BitmapFactory.decodeResource(getResources(), R.drawable.bb);
        mbgBackGround = Bitmap.createBitmap(bgBackGround.getWidth(), bgBackGround.getHeight(), Bitmap.Config.ARGB_8888);

        mcanvas = new Canvas(mbgBackGround);

        contentPaint = new Paint();
        contentPaint.setColor(Color.WHITE);
        contentPaint.setTextSize(100);
        contentPaint.setStrokeWidth(20);

        mcanvas.drawColor(Color.GRAY);
        mcanvas.drawText("刮刮看~", mcanvas.getWidth() / 4, mcanvas.getHeight() / 4, contentPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bgBackGround, 0, 0, null);
        canvas.drawBitmap(mbgBackGround, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.reset();
                path.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(),event.getY());
                break;

        }
        mcanvas.drawPath(path, paint);
        invalidate();
        return true;
    }
}
