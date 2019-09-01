package com.example.game.demo1;
//https://blog.csdn.net/u010756046/article/details/80710469
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.game.R;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppApplication.main = this;
        getSupportActionBar().hide();//隐藏标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation){
            DisplayMetrics dis  = getResources().getDisplayMetrics();
            AppApplication.width = dis.widthPixels;
            AppApplication.height = dis.heightPixels;
            AppApplication.ratio = (float) (Math.sqrt(AppApplication.width * AppApplication.height) / Math.sqrt(1920*1080));
            //加载图片
            AppApplication.border = BitmapFactory.decodeResource(AppApplication.main.getResources(), R.drawable.border);
            AppApplication.cancle = BitmapFactory.decodeResource(AppApplication.main.getResources(),R.drawable.cancel);
            AppApplication.down = BitmapFactory.decodeResource(AppApplication.main.getResources(),R.drawable.down);
            AppApplication.yaogan = BitmapFactory.decodeResource(AppApplication.main.getResources(),R.drawable.yaogan);
            AppApplication.cd = BitmapFactory.decodeResource(AppApplication.main.getResources(),R.drawable.cd);

            setContentView(new PaintView(this));
        }else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//横屏
        }
    }
}
