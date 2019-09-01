package com.example.game.demo1;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

import com.example.game.demo1.AppApplication;
import com.example.game.demo1.Move;

public class OnTouchMove extends View { //这个view负责监听移动摇杆的手势

    private Move m;

    public OnTouchMove(Context context, Move move) {
        super(context);
        this.m=move;
        setBackgroundColor(Color.BLACK);//背景色设为白色
        getBackground().setAlpha(AppApplication.ontouchAlpha);//设置触控区透明度
        setOnTouchListener(new OnTouchListener() { //设置触控监听
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                //加上getX() getY()因为这个view不是分布在左上角的
                final float xx = ev.getX() + getX(), yy = ev.getY() + getY();

                if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                    m.down(xx, yy);//按下时的操作
//                    m.move(xx, yy);
                }
                m.move(xx, yy);//移动时的操作
                if (ev.getAction() == MotionEvent.ACTION_UP) {
                    m.up();//松开时的操作
                }
                return true;//不要返回false
            }
        });
    }
}

