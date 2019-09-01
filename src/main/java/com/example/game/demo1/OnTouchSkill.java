package com.example.game.demo1;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

public class OnTouchSkill extends View {
    public Skill A,Q,W,E;

    public OnTouchSkill(Context context,Skill a,Skill q,Skill w,Skill e) {
        super(context);A=a;Q=q;W=w;E=e;
        setBackgroundColor(Color.BLACK);
        getBackground().setAlpha(AppApplication.ontouchAlpha);//0-255
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                final float xx = ev.getX() + getX(), yy = ev.getY() + getY();
                if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                    if ( A.in(xx, yy)) {
                        A.down();
                    } else if ( Q.in(xx, yy)) {
                        Q.down();
                    } else if ( W.in(xx, yy)) {
                        W.down();
                    } else if ( E.in(xx, yy)) {
                        E.down();
                    }

                }
                if (AppApplication.skill != null) AppApplication.skill.move(xx, yy);
                if(ev.getAction()== MotionEvent.ACTION_UP){
                    A.down = false;
                    Q.down = false;
                    W.down = false;
                    E.down = false;
                }
                return true;
            }
        });
    }
}
