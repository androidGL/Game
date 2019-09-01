package com.example.game.demo4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.game.R;

//public class CarouselItem extends FrameLayout implements Comparable<CarouselItem> {
//
//    public ImageView mImage;
//    public TextView mText, mTextUp;
//    public Context context;
//    public int index;
//    public float currentAngle;
//    public float itemX;
//    public float itemY;
//    public float itemZ;
//    public float degX;
//    public float degY;
//    public float degZ;
//    public boolean drawn;
//
//    // It's needed to find screen coordinates
//    private Matrix mCIMatrix;
//
////    public CarouselItem(Context context) {
////
////        super(context);
////        this.context = context;
////        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
////
////        this.setLayoutParams(params);
////        LayoutInflater inflater = LayoutInflater.from(context);
////        View itemTemplate = inflater.inflate(R.layout.carousel_item, this, true);
////
////        mImage = (ImageView) itemTemplate.findViewById(R.id.item_image);
////        mText = (TextView) itemTemplate.findViewById(R.id.item_text);
////        mTextUp = (TextView) itemTemplate.findViewById(R.id.item_text_up);
////
////    }
//
//    public void setTextColor(int i) {
//        this.mText.setTextColor(context.getResources().getColorStateList(i));
//        this.mTextUp.setTextColor(context.getResources().getColorStateList(i));
//    }
//
//    public String getName() {
//        return mText.getText().toString();
//    }
//
//    public void setIndex(int index) {
//        this.index = index;
//    }
//
//    public int getIndex() {
//        return index;
//    }
//
//    public void setCurrentAngle(float currentAngle) {
//
//        if (index == 0 && currentAngle > 5) {
//            Log.d("", "");
//        }
//
//        this.currentAngle = currentAngle;
//    }
//
//    public float getCurrentAngle() {
//        return currentAngle;
//    }
//
//    public int compareTo(CarouselItem another) {
//        return (int) (another.itemZ - this.itemZ);
//    }
//
//    public void setItemX(float x) {
//        this.itemX = x;
//    }
//
//    public float getItemX() {
//        return itemX;
//    }
//
//    public void setItemY(float y) {
//        this.itemY = y;
//    }
//
//    public float getItemY() {
//        return itemY;
//    }
//
//    public void setItemZ(float z) {
//        this.itemZ = z;
//    }
//
//    public float getItemZ() {
//        return itemZ;
//    }
//
//    public float getDegX() {
//        return degX;
//    }
//
//    public void setDegX(float degX) {
//        this.degX = degX;
//    }
//
//    public float getDegY() {
//        return degY;
//    }
//
//    public void setDegY(float degY) {
//        this.degY = degY;
//    }
//
//    public float getDegZ() {
//        return degZ;
//    }
//
//    public void setDegZ(float degZ) {
//        this.degZ = degZ;
//    }
//
//    public void setDrawn(boolean drawn) {
//        this.drawn = drawn;
//    }
//
//    public boolean isDrawn() {
//        return drawn;
//    }
//
//    public void setImageBitmap(Bitmap bitmap) {
//        mImage.setImageBitmap(bitmap);
//
//    }
//
//    public void setText(int i) {
//        String s = context.getResources().getString(i);
//        mText.setText(s);
//        mTextUp.setText(s);
//    }
//
//    public void setText(String txt) {
//        mText.setText(txt);
//        mTextUp.setText(txt);
//    }
//
//    Matrix getCIMatrix() {
//        return mCIMatrix;
//    }
//
//    void setCIMatrix(Matrix mMatrix) {
//        this.mCIMatrix = mMatrix;
//    }
//
//    public void setImage(int i) {
//        mImage.setImageDrawable(context.getResources().getDrawable(i));
//
//    }
//
//    public void setVisiblity(int id) {
//        if (id == 0) {
//            mText.setVisibility(View.INVISIBLE);
//            mTextUp.setVisibility(View.VISIBLE);
//        } else {
//            mTextUp.setVisibility(View.INVISIBLE);
//            mText.setVisibility(View.VISIBLE);
//        }
//    }
//}
