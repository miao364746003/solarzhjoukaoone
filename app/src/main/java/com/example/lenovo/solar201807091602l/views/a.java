package com.example.lenovo.solar201807091602l.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by lenovo on 2018/7/9.
 */

public class a extends android.support.v7.widget.AppCompatTextView {
    Paint paint,tpaint;
    Path path;
    Canvas mcanvas;
    private Bitmap bitmap;

    public a(Context context) {
        super(context);
    }

    public a(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public a(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init(Context context, AttributeSet attrs) {
        paint = new Paint();
        paint.setAlpha(0);
        paint.setStrokeWidth(50);
        paint.setStyle(Paint.Style.STROKE);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

        path = new Path();

        bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        mcanvas = new Canvas(bitmap);
        mcanvas.drawColor(Color.GRAY);

        tpaint = new Paint();
        tpaint.setColor(Color.WHITE);
        tpaint.setStrokeWidth(20);
        tpaint.setTextSize(100);

        mcanvas.drawText("挂我啊！",200,200,tpaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.reset();
                path.moveTo(event.getX(),event.getY());
                break;

            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(),event.getY());
                break;
        }
        mcanvas.drawPath(path,paint);
        invalidate();
        return true;
    }
}
