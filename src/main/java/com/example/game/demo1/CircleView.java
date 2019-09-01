package com.example.game.demo1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;

@SuppressLint("AppCompatCustomView")
public class CircleView extends TextView {
    int color,r;
    Paint paint;
    public CircleView(Context context, @ColorInt int color,int r) {
        super(context);
        this.color = color;
        this.r = r;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(8);
        canvas.drawCircle(r, r, 200, paint);
    }
}
