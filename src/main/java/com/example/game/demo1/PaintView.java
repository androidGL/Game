package com.example.game.demo1;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.RelativeLayout;
import com.example.game.R;

public class PaintView extends RelativeLayout implements Runnable {
    private Paint p;
    private Move m=new Move();//移动摇杆

    /*
    A 普通攻击
    Q 技能1
    W 技能2
    E 技能3
    R 没有R
     */

    public Skill A=new Skill(0,100, new CircleView(getContext(),Color.BLUE,60)) {
        @Override
        public void down_main() { }
        @Override
        public void move_main() { }
        @Override
        public void up() { }
    };
    public Skill Q=new Skill(1,1000,new CircleView(getContext(),Color.BLUE,60)) {
        @Override
        public void down_main() { }
        @Override
        public void move_main() { }
        @Override
        public void up() {
            down_main=false;
            if(!cancel){ //技能冷却时间
                last= System.currentTimeMillis();
            }
        }
    };
    public Skill W=new Skill(2,1000, new CircleView(getContext(),Color.BLUE,60)) {
        @Override
        public void down_main() { }
        @Override
        public void move_main() { }
        @Override
        public void up() {
            down_main=false;
            if(!cancel){
                last= System.currentTimeMillis();
            }
        }
    };
    public Skill E=new Skill(3,1000, BitmapFactory.decodeResource(getResources(),R.drawable.skill3)) {
        @Override
        public void down_main() { }
        @Override
        public void move_main() { }
        @Override
        public void up() {
            down_main=false;
            if(!cancel){
                last= System.currentTimeMillis();
            }
        }
    };
    public PaintView(Context context) {
        super(context);
        p = new Paint();
        setBackgroundColor(Color.BLACK);
        //实例化一个OnTouchMove
        OnTouchMove onTouchMove=new OnTouchMove(context,m);
        //把onTouchMove添加进来 宽度为屏幕的1/3 高度为屏幕的1/2
        addView(onTouchMove, AppApplication.width/3,AppApplication.height/2);
        //设置onTouchMove的位置
        onTouchMove.setX(0);
        onTouchMove.setY(AppApplication.height/2);

        //添加技能摇杆监听
        OnTouchSkill onTouchSkill=new OnTouchSkill(context,A,Q,W,E);//后添加的优先级高
        addView(onTouchSkill);
        onTouchSkill.setX(AppApplication.width*0.7f-85*AppApplication.ratio);
        onTouchSkill.setY(AppApplication.height/2-85*AppApplication.ratio);

        new Thread(this).start();//启动重绘线程
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m.onDraw(canvas,p);
//画技能
        A.onDraw(canvas,p);
        Q.onDraw(canvas,p);
        W.onDraw(canvas,p);
        E.onDraw(canvas,p);

    }

    @Override
    public void run() {
        while(true){
            try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
            //计算冷却时间
            A.next();
            Q.next();
            W.next();
            E.next();
            //释放技能
            if (AppApplication.skill != null) {
                AppApplication.skill.down_main();//教程用不到该方法
                AppApplication.skill.move_main();//教程用不到该方法
                if (AppApplication.skill.down == false) {
                    AppApplication.skill.up();
                    AppApplication.skill = null;
                }
            }
            postInvalidate();//重绘 在子线程重绘不能调用Invalidate()方法
        }
    }
}
