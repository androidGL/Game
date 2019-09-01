package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.game.demo1.GameActivity;
import com.example.game.demo2.SurfaceViewActivity;
import com.example.game.demo3.CoverFlowActivity;
import com.example.game.demo5.GestureActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void togame1(View view) {
        startActivity(new Intent(this, GameActivity.class));
    }

    public void togame2(View view) {
        startActivity(new Intent(this, SurfaceViewActivity.class));
    }

    public void todemo3(View view) {
        startActivity(new Intent(this, CoverFlowActivity.class));
    }

    public void todemo6(View view) {
    }

    public void todemo5(View view) {
        startActivity(new Intent(this, GestureActivity.class));
    }

    public void todemo4(View view) {
    }
}
