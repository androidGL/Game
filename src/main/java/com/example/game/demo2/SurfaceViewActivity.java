package com.example.game.demo2;

import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;


public class SurfaceViewActivity extends AppCompatActivity {
    private RemoteSurfaceView remoteSurfaceView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.dance_relative_layout);
        remoteSurfaceView = new RemoteSurfaceView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        remoteSurfaceView.setLayoutParams(params);
        relativeLayout.addView(remoteSurfaceView);
    }
}
