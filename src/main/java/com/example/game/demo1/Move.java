package com.example.game.demo1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Move {
    private float xBigCircle,yBigCircle;//按下时的坐标  大圆
    private float xSmallCircle,ySmallCircle;//移动时的坐标  小圆
    private final float rBigCircle,rSmallCircle;
    public float angle;
    public boolean down = false;
    public boolean in = false;
    public boolean move = false;
    public Bitmap img;
    public Move(){
        rBigCircle = 480 * 0.5f * AppApplication.ratio;
        rSmallCircle = 300 * 0.5f * AppApplication.ratio;
        img = AppApplication.yaogan;
    }
    public void down(float x,float y){
        if(x<rBigCircle)
            xBigCircle = rBigCircle;
        else
            xBigCircle = rSmallCircle;
        if(AppApplication.height - y < rBigCircle)
            yBigCircle = AppApplication.height - rBigCircle;
        else
            yBigCircle = y;
        down = true;
    }
    public void move(float xx,float yy){ //按下摇杆后移动的操作
        angle=getAngle(xx,yy);
        in=in(xx, yy);
        move=isMove(xx,yy);
        if (!in) {
            //下面会做解释
            xx= (float) (xBigCircle+ Math.sin(angle)*rBigCircle*0.7f);
            yy= (float) (yBigCircle+ Math.cos(angle)*rBigCircle*0.7f);
        }
        xSmallCircle=xx;
        ySmallCircle=yy;
    }
    public void up(){ //松开后的操作
        down=false;
    }

    public float getAngle(float xx,float yy){ //获取xBigCircleyBigCircle指向xSmallCircleySmallCircle的角度
        double angle,k;
        if (yBigCircle==yy)//斜率不存在时
            if (xBigCircle > xx)//判断xBigCircle指向xSmallCircle的方向
                angle=-Math.PI/2;
            else
                angle=Math.PI/2;
        else{
            k=(xBigCircle-xx)/(yBigCircle-yy); //两点的坐标求斜率,至于为什么是(xBigCircle-xSmallCircle)/(yBigCircle-ySmallCircle)不是(yBigCircle-ySmallCircle)/(xBigCircle-xSmallCircle)待会我们再做解释
            if (yBigCircle > yy) {//判断xBigCircleyBigCircle指向xSmallCircleySmallCircle的方向
                // 用反tan求角度 这个高中好像没学过 既然Math类已经帮我们封装好了就直接拿来用吧
                angle=Math.atan(k) + Math.PI;
            } else {
                angle=Math.atan(k);
            }
            //这段可写可不写 让计算出来的角度属于-PI/2到PI/2
            if(angle>Math.PI)
                angle-=Math.PI*2;
            else if(angle<-Math.PI)
                angle+=Math.PI*2;
        }
        return (float) angle;
    }

    public boolean in(float xx, float yy) { //防止小圆被脱太远 拖动范围不超出rBigCircle的70%
        double r = Math.sqrt((xBigCircle - xx) * (xBigCircle - xx) + (yBigCircle - yy) * (yBigCircle - yy));//两点间距离公式
        if (r < rBigCircle*0.7f)
            return true;
        else return false;
    }
    public boolean isMove(float xx, float yy) { //判断按下摇杆后 是否移动,如果xBigCircleyBigCircle xSmallCircleySmallCircle的距离大于rBigCircle*0.15视为移动
        // AppApplication实际开发中用到,该教程用不到此变量
        double r = Math.sqrt((xBigCircle - xx) * (xBigCircle - xx) + (yBigCircle - yy) * (yBigCircle - yy));//两点间距离公式
        if (r > rBigCircle*0.15f)
            return true;
        else return false;
    }
    public void onDraw(Canvas g, Paint p){ //画摇杆
        if(down) { //当摇杆被按下时 才显示
            //怎么用Canvas画图这里就不说了
            AppApplication.re.left = xBigCircle - rBigCircle;
            AppApplication.re.top = yBigCircle - rBigCircle;
            AppApplication.re.right = xBigCircle + rBigCircle;
            AppApplication.re.bottom = yBigCircle+ rBigCircle;
            g.drawBitmap(img, null, AppApplication.re, p); //画大圆
            AppApplication.re.left = xSmallCircle - rSmallCircle;
            AppApplication.re.top = ySmallCircle - rSmallCircle;
            AppApplication.re.right = xSmallCircle + rSmallCircle;
            AppApplication.re.bottom = ySmallCircle + rSmallCircle;
            g.drawBitmap(img, null, AppApplication.re, p); //画小圆
        }
    }


}
