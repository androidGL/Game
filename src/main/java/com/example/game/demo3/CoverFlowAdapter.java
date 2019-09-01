package com.example.game.demo3;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class CoverFlowAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

    /**
     * 子元素的集合
     */
    private List<View> mViewList;

    /**
     * 滑动监听的回调接口
     */
    private OnPageSelectListener listener;

    private Context mContext;

    private float mPositionOffset=0;

    public CoverFlowAdapter(List<View> mImageViewList, Context context) {
        this.mViewList = mImageViewList;
        mContext = context;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViewList.get(position);
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return mViewList == null ? 0 : mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        // 该方法回调ViewPager 的滑动偏移量
        if (mViewList.size() > 0 && position < mViewList.size()) {
            //当前手指触摸滑动的页面,从0页滑动到1页 offset越来越大，padding越来越大
            mViewList.get(position).setScaleX(1 - positionOffset * 0.17f);
            mViewList.get(position).setScaleY(1 - positionOffset * 0.17f);
            mViewList.get(position).setAlpha(1 - positionOffset * 0.5f);
            mViewList.get(position).setTranslationX(dp2px(60) * positionOffset);

            // position+1 为即将显示的页面，越来越大
            if (position < mViewList.size() - 1) {
                //这里是两边pager所实现的效果
                mViewList.get(position + 1).setScaleX(0.83f + positionOffset * 0.17f);
                mViewList.get(position + 1).setScaleY(0.83f + positionOffset * 0.17f);
                mViewList.get(position + 1).setAlpha(0.5f + positionOffset * 0.5f);
                //
                mViewList.get(position + 1).setTranslationX(dp2px(60) * (positionOffset - 1));
                //下面的代码比较重要，如果不加如下代码，最上面的pager有时候并不是中间的pager，根据positionOffset进行左右滑动的判定，来决定那个pager此时应该在最上面
                //bringToFront就是view在最上面的方法
                if(positionOffset>=mPositionOffset) {
                    if (positionOffset >= 0.7f) {
                        mViewList.get(position + 1).bringToFront();
                    }else if(positionOffset==0&&position==0){
                        mViewList.get(position).bringToFront();
                    }
                }else{
                    if(positionOffset<=0.6f){
                        mViewList.get(position).bringToFront();
                    }
                }
                //这里的代码可加可不加，这里是从中间数第三个pager的动画效果，如果屏幕只显示三个页，可以不设置，如果是五个页可以设置，具体效果可以自己调试
                if (position < mViewList.size() - 2) {
                    mViewList.get(position + 2).setScaleX(0.66f + positionOffset * 0.17f);
                    mViewList.get(position + 2).setScaleY(0.66f + positionOffset * 0.17f);
                    mViewList.get(position + 2).setTranslationX(dp2px(30) * (positionOffset - 1));
                }
            }

        }
        mPositionOffset=positionOffset;
    }

    @Override
    public void onPageSelected(int position) {
        // 回调选择的接口
        if (listener != null) {
            listener.select(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /**
     * 当将某一个作为最中央时的回调
     *
     * @param listener
     */
    public void setOnPageSelectListener(OnPageSelectListener listener) {
        this.listener = listener;
    }
    public int dp2px(int dp) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());

        return px;
    }

}

