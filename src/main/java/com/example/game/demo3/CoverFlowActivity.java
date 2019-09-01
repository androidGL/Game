package com.example.game.demo3;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoverFlowActivity extends AppCompatActivity {
    private CoverFlowViewPager mCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coverflow);

        mCover = (CoverFlowViewPager) findViewById(R.id.cover);

        // 初始化数据
        List<View> list = new ArrayList<>();
        for(int i = 0;i<10;i++){
//            ImageView img = new ImageView(this);
//            img.setBackgroundColor(Color.parseColor("#"+getRandColorCode()));
//            list.add(img);
            RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.base_view,null);
            relativeLayout.setBackgroundColor(Color.parseColor("#"+getRandColorCode()));
            list.add(relativeLayout);
        }
        //设置显示的数据
        mCover.setViewList(list);
        // 设置滑动的监听，该监听为当前页面滑动到中央时的索引
        mCover.setOnPageSelectListener(new OnPageSelectListener() {
            @Override
            public void select(int position) {
                Toast.makeText(getApplicationContext(),position+"",Toast.LENGTH_SHORT).show();
            }
        });

        // 点击事件
        for(int i =0;i<list.size();i++){
            final int finalI = i;
            list.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CoverFlowActivity.this, "点击了"+ finalI, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    /**
     * 获取随机颜色，便于区分
     * @return
     */
    public static String getRandColorCode(){
        String r,g,b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length()==1 ? "0" + r : r ;
        g = g.length()==1 ? "0" + g : g ;
        b = b.length()==1 ? "0" + b : b ;

        return r+g+b;
    }
}
