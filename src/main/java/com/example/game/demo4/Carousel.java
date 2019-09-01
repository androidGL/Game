package com.example.game.demo4;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.SyncStateContract;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Transformation;
import android.widget.BaseAdapter;

import com.example.game.R;

//public class Carousel extends CarouselSpinner implements SyncStateContract.Constants
//{
//
//    private int mAnimationDuration = 100;
//    private int mAnimationDurationMin = 50;
//    private Camera mCamera = null;
//    private FlingRotateRunnable mFlingRunnable = null;
//    private int mGravity = 0;
//    private View mSelectedChild = null;
//    private static int mSelectedItemIndex = 2;
//    private boolean mShouldStopFling = false;
//    private static final int LEFT = 0;
//    private static final int RIGHT = 1;
//    /**
//     * If true, do not callback to item selected listener.
//     */
//    private boolean mSuppressSelectionChanged = false;
//    private float mTheta = 0.0f;
//    private boolean isFocus = true;
//
//    private ImageAdapter adapter = null;
//
//    private static final int ONE_ITEM = 1;
//
//    private CarouselItemClickListener callback = null;
//
//    public Carousel(Context context)
//    {
//        this(context, null);
//    }
//
//    public Carousel(Context context, AttributeSet attrs)
//    {
//        this(context, attrs, 0);
//    }
//
//    public Carousel(Context context, AttributeSet attrs, int defStyle)
//    {
//        super(context, attrs, defStyle);
//        setChildrenDrawingOrderEnabled(false);
//        setStaticTransformationsEnabled(true);
//        TypedArray arr = getContext().obtainStyledAttributes(attrs, R.styleable.Carousel);
//        int imageArrayID = arr.getResourceId(R.styleable.Carousel_Items, -1);
//        TypedArray images = getResources().obtainTypedArray(imageArrayID);
//        int namesForItems = arr.getResourceId(R.styleable.Carousel_Names, -1);
//        TypedArray names = null;
//        if (namesForItems != -1)
//        {
//            names = getResources().obtainTypedArray(namesForItems);
//        }
//
//        initView(images, names);
//
//        arr.recycle();
//        images.recycle();
//        if (names != null)
//        {
//            names.recycle();
//        }
//    }
//
//    private void initView(TypedArray images, TypedArray names)
//    {
//        mCamera = new Camera();
//        mFlingRunnable = new FlingRotateRunnable();
//        mTheta = (float) (15.0f * (Math.PI / 180.0));
//
//        adapter = new ImageAdapter(getContext());
//        adapter.setImages(images, names);
//        setAdapter(adapter);
//        setSelectedPositionInt(mSelectedItemIndex);
//    }
//
//    @Override
//    protected int computeHorizontalScrollExtent()
//    {
//        // Only 1 item is considered to be selected
//        return ONE_ITEM;
//    }
//
//    @Override
//    protected int computeHorizontalScrollOffset()
//    {
//        // Current scroll position is the same as the selected position
//        return mSelectedPosition;
//    }
//
//    @Override
//    protected int computeHorizontalScrollRange()
//    {
//        // Scroll range is the same as the item count
//        return mItemCount;
//    }
//
//    public void setFocusFlag(boolean flag)
//    {
//
//        this.isFocus = flag;
//        adapter.notifyDataSetChanged();
//    }
//
//    public boolean getFocusFlag()
//    {
//        return this.isFocus;
//    }
//
//    public void setSelected(int index)
//    {
//        setNextSelectedPositionInt(index);
//        mSelectedItemIndex = index;
//    }
//
//    public void setCarouselItemClickCallBack(CarouselItemClickListener listener)
//    {
//        callback = listener;
//    }
//
//    public interface CarouselItemClickListener
//    {
//        public void CarouselClickCallBack(int itemPosition);
//    }
//
//    /**
//     * Handles left, right, and clicking
//     *
//     * @see android.view.View#onKeyDown
//     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event)
//    {
//        switch (keyCode)
//        {
//            case KEY_OK:
//            case KEY_CENTER:
//                callback.CarouselClickCallBack(mSelectedItemIndex);
//                return true;
//
//            case KEY_LEFT:
//                toNextLeftItem();
//                return true;
//
//            case KEY_RIGHT:
//                toNextRightItem();
//                return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    @Override
//    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect)
//    {
//        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
//
//        /*
//         * The gallery shows focus by focusing the selected item. So, give focus
//         * to our selected item instead. We steal keys from our selected item
//         * elsewhere.
//         */
//        if (gainFocus && mSelectedChild != null)
//        {
//            mSelectedChild.requestFocus(direction);
//        }
//
//    }
//
//    @Override
//    protected boolean checkLayoutParams(ViewGroup.LayoutParams p)
//    {
//        return p instanceof LayoutParams;
//    }
//
//    @Override
//    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p)
//    {
//        return new LayoutParams(p);
//    }
//
//    @Override
//    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs)
//    {
//        return new LayoutParams(getContext(), attrs);
//    }
//
//    @Override
//    protected void dispatchSetPressed(boolean pressed)
//    {
//        if (mSelectedChild != null)
//        {
//            mSelectedChild.setPressed(pressed);
//        }
//    }
//
//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event)
//    {
//        return false;
//    }
//
//    /**
//     * Transform an item depending on it's coordinates
//     */
//    @Override
//    protected boolean getChildStaticTransformation(View child, Transformation transformation)
//    {
//
//        transformation.clear();
//        transformation.setTransformationType(Transformation.TYPE_MATRIX);
//        // Center of the view
//        float centerX = (float) getWidth() / 2, centerY = (float) getHeight() / 2;
//        mCamera.save();
//        final Matrix matrix = transformation.getMatrix();
//        mCamera.translate(((CarouselItem) child).getItemX(), ((CarouselItem) child).getItemY(), ((CarouselItem) child).getItemZ());
//        mCamera.getMatrix(matrix);
//        matrix.preTranslate(-centerX, -centerY);
//        matrix.postTranslate(centerX, centerY);
//        float[] values = new float[9];
//        matrix.getValues(values);
//        mCamera.restore();
//        Matrix mm = new Matrix();
//        mm.setValues(values);
//        ((CarouselItem) child).setCIMatrix(mm);
//
//        child.invalidate();
//
//        return true;
//    }
//
//    // CarouselAdapter overrides
//
//    /**
//     * Setting up images
//     */
//    void layout(int delta, boolean animate)
//    {
//        Log.d("ORDER", "layout");
//        if (mDataChanged)
//        {
//            handleDataChanged();
//        }
//        if (mNextSelectedPosition >= 0)
//        {
//            setSelectedPositionInt(mNextSelectedPosition);
//        }
//        recycleAllViews();
//        detachAllViewsFromParent();
//        int count = getAdapter().getCount();
//        float angleUnit = 360.0f / count;
//
//        float angleOffset = mSelectedPosition * angleUnit;
//        for (int i = 0; i < getAdapter().getCount(); i++)
//        {
//            float angle = angleUnit * i - angleOffset;
//            if (angle < 0.0f)
//            {
//                angle = 360.0f + angle;
//            }
//            makeAndAddView(i, angle);
//        }
//        mRecycler.clear();
//        invalidate();
//        setNextSelectedPositionInt(mSelectedPosition);
//        checkSelectionChanged();
//        mNeedSync = false;
//        updateSelectedItemMetadata();
//    }
//
//    /**
//     * Setting up images after layout changed
//     */
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b)
//    {
//        super.onLayout(changed, l, t, r, b);
//        Log.d("ORDER", "onLayout");
//        /*
//         * Remember that we are in layout to prevent more layout request from
//         * being generated.
//         */
//        mInLayout = true;
//        layout(0, false);
//        mInLayout = false;
//    }
//
//    @Override
//    void selectionChanged()
//    {
//        if (!mSuppressSelectionChanged)
//        {
//            super.selectionChanged();
//        }
//    }
//
//    @Override
//    void setSelectedPositionInt(int position)
//    {
//        super.setSelectedPositionInt(position);
//        super.setNextSelectedPositionInt(position);
//        updateSelectedItemMetadata();
//    }
//
//    private class FlingRotateRunnable implements Runnable
//    {
//
//        private Rotator mRotator;
//
//        private float mLastFlingAngle;
//
//        public FlingRotateRunnable()
//        {
//            mRotator = new Rotator(getContext());
//        }
//
//        private void startCommon()
//        {
//            removeCallbacks(this);
//        }
//
//        public void startUsingDistance(float deltaAngle, int flag, int direction)
//        {
//            if (deltaAngle == 0)
//                return;
//            startCommon();
//            mLastFlingAngle = 0;
//
//            synchronized (this)
//            {
//                mRotator.startRotate(0.0f, -deltaAngle, flag == 0 ? mAnimationDuration : mAnimationDurationMin, direction);
//            }
//            post(this);
//        }
//
//        private void endFling(boolean scrollIntoSlots, int direction)
//        {
//            synchronized (this)
//            {
//                mRotator.forceFinished(true);
//            }
//            if (scrollIntoSlots)
//            {
//                scrollIntoSlots(direction);
//            }
//        }
//
//        public void run()
//        {
//            Log.d("ORDER", "run");
//            mShouldStopFling = false;
//
//            final Rotator rotator;
//            final float angle;
//            final float deg;
//            boolean more;
//            int direction;
//            synchronized (this)
//            {
//                rotator = mRotator;
//                more = rotator.computeAngleOffset();
//                angle = rotator.getCurrAngle();
//                deg = rotator.getCurrDeg();
//                direction = rotator.getdirection();
//            }
//            if (more && !mShouldStopFling)
//            {
//                Log.d("GETVIEW", "========go");
//                float delta = mLastFlingAngle - angle;
//                trackMotionScroll(delta, deg);
//                mLastFlingAngle = angle;
//                post(this);
//            }
//            else
//            {
//                Log.d("GETVIEW", "========end");
//                float delta = mLastFlingAngle - angle;
//                trackMotionScroll(delta, deg);
//                mLastFlingAngle = 0.0f;
//                endFling(false, direction);
//            }
//
//        }
//
//    }
//
//    private class ImageAdapter extends BaseAdapter
//    {
//        private Context mContext;
//        private CarouselItem[] mImages;
//
//        private int[] lightImages = { R.drawable.icons_light_network, R.drawable.icons_light_update, R.drawable.icons_light_app, R.drawable.icons_light_stb, R.drawable.icons_light_other,
//                R.drawable.icons_light_wallpaper, R.drawable.icons_light_media };
//
//        private final int[] normalImages = { R.drawable.icons_normal_network0, R.drawable.icons_normal_update0, R.drawable.icons_normal_app0, R.drawable.icons_normal_stb0,
//                R.drawable.icons_normal_other0, R.drawable.icons_normal_wallpaper0, R.drawable.icons_normal_meida0 };
//
//        private final int[] colors = { R.color.network_text_color, R.color.update_text_color, R.color.app_text_color, R.color.stb_text_color, R.color.other_text_color, R.color.wallpaper_text_color,
//                R.color.media_text_color, R.color.text_color_white };
//
//        // private final int[] names = { R.string.STR_NETWORK,
//        // R.string.STR_UPDATE, R.string.STR_APP, R.string.STR_STB,
//        // R.string.STR_OTHER, R.string.STR_WALLPAPER, R.string.STR_MEDIA };
//
//        public ImageAdapter(Context c)
//        {
//            mContext = c;
//        }
//
//        public void setImages(TypedArray array, TypedArray names)
//        {
//            Drawable[] drawables = new Drawable[array.length()];
//            mImages = new CarouselItem[array.length()];
//            for (int i = 0; i < array.length(); i++)
//            {
//                drawables[i] = array.getDrawable(i);
//                Bitmap originalImage = ((BitmapDrawable) drawables[i]).getBitmap();
//                CarouselItem item = new CarouselItem(mContext);
//                item.setIndex(i);
//                item.setImageBitmap(originalImage);
//                if (names != null)
//                {
//                    item.setText(names.getString(i));
//                }
//                if (i == mSelectedItemIndex || (i + 6) % 7 == mSelectedItemIndex || (i + 1) % 7 == mSelectedItemIndex)
//                {
//                    item.setVisiblity(1);
//                }
//                else
//                {
//                    item.setVisiblity(0);
//                }
//                mImages[i] = item;
//            }
//        }
//
//        public int getCount()
//        {
//            if (mImages == null)
//            {
//                return 0;
//            }
//            else
//            {
//                return mImages.length;
//            }
//        }
//
//        public Object getItem(int position)
//        {
//            return position;
//        }
//
//        public long getItemId(int position)
//        {
//            return position;
//        }
//
//        public View getView(int position, View convertView, ViewGroup parent)
//        {
//            if (position == mSelectedItemIndex || (position + 6) % 7 == mSelectedItemIndex || (position + 1) % 7 == mSelectedItemIndex)
//            {
//                mImages[position].setVisiblity(1);
//            }
//            else
//            {
//                mImages[position].setVisiblity(0);
//            }
//
//            if (position == mSelectedItemIndex && isFocus)
//            {
//                mImages[position].setImage(lightImages[position]);
//                mImages[position].setTextColor(colors[position]);
//            }
//            else
//            {
//                mImages[position].setImage(normalImages[position]);
//                mImages[position].setTextColor(colors[7]);
//            }
//            Log.d("GETVIEW", position + ":getView");
//
//            return mImages[position];
//        }
//    }
//
//    @SuppressLint("FloatMath")
//    private void Calculate3DPosition(CarouselItem child, int diameter, float angleOffset)
//    {
//        angleOffset = angleOffset * (float) (Math.PI / 180.0f);
//        float x = -(float) (diameter / 2 * android.util.FloatMath.sin(angleOffset) * 1.05) + diameter / 2 - child.getWidth() / 2;
//        float z = diameter / 2 * (1.0f - (float) android.util.FloatMath.cos(angleOffset));
//        float y = -getHeight() / 2 + (float) (z * android.util.FloatMath.sin(mTheta)) + 120;
//        child.setItemX(x);
//        child.setItemZ(z);
//        child.setItemY(y);
//    }
//
//    /**
//     * Figure out vertical placement based on mGravity
//     *
//     * @param child
//     *            Child to place
//     * @return Where the top of the child should be
//     */
//    private int calculateTop(View child, boolean duringLayout)
//    {
//        int myHeight = duringLayout ? getMeasuredHeight() : getHeight();
//        int childHeight = duringLayout ? child.getMeasuredHeight() : child.getHeight();
//
//        int childTop = 0;
//
//        switch (mGravity)
//        {
//            case Gravity.TOP:
//                childTop = mSpinnerPadding.top;
//                break;
//
//            case Gravity.CENTER_VERTICAL:
//                int availableSpace = myHeight - mSpinnerPadding.bottom - mSpinnerPadding.top - childHeight;
//                childTop = mSpinnerPadding.top + (availableSpace / 2);
//                break;
//
//            case Gravity.BOTTOM:
//                childTop = myHeight - mSpinnerPadding.bottom - childHeight;
//                break;
//        }
//
//        return childTop;
//    }
//
//    private void makeAndAddView(int position, float angleOffset)
//    {
//        Log.d("ORDER", "makeAndAddView");
//        CarouselItem child;
//
//        if (!mDataChanged)
//        {
//            child = (CarouselItem) mRecycler.get(position);
//            if (child != null)
//            {
//                // Position the view
//                setUpChild(child, child.getIndex(), angleOffset);
//            }
//            else
//            {
//                // Nothing found in the recycler -- ask the adapter for a view
//                child = (CarouselItem) mAdapter.getView(position, null, this);
//                Log.d("GETVIEW", "makeAndAddView1");
//                // Position the view
//                setUpChild(child, child.getIndex(), angleOffset);
//            }
//            return;
//        }
//
//        // Nothing found in the recycler -- ask the adapter for a view
//        child = (CarouselItem) mAdapter.getView(position, null, this);
//        Log.d("GETVIEW", "makeAndAddView2");
//
//        // Position the view
//        setUpChild(child, child.getIndex(), angleOffset);
//    }
//
//    private void onFinishedMovement()
//    {
//        if (mSuppressSelectionChanged)
//        {
//            mSuppressSelectionChanged = false;
//            super.selectionChanged();
//        }
//        checkSelectionChanged();
//        invalidate();
//    }
//
//    /**
//     * Brings an item with nearest to 0 degrees angle to this angle and sets it
//     * selected
//     */
//    private void scrollIntoSlots(int direction)
//    {
//        Log.d("ORDER", "scrollIntoSlots");
//        float angle;
//        int position;
//        ArrayList<CarouselItem> arr = new ArrayList<CarouselItem>();
//        for (int i = 0; i < getAdapter().getCount(); i++)
//        {
//            arr.add(((CarouselItem) getAdapter().getView(i, null, null)));
//            Log.d("GETVIEW", "scrollIntoSlots");
//        }
//        Collections.sort(arr, new Comparator<CarouselItem>()
//        {
//
//            public int compare(CarouselItem c1, CarouselItem c2)
//            {
//                int a1 = (int) c1.getCurrentAngle();
//                if (a1 > 180)
//                {
//                    a1 = 360 - a1;
//                }
//                int a2 = (int) c2.getCurrentAngle();
//                if (a2 > 180)
//                {
//                    a2 = 360 - a2;
//                }
//                return (a1 - a2);
//            }
//        });
//        angle = arr.get(0).getCurrentAngle();
//        if (angle > 180.0f)
//        {
//            angle = -(360.0f - angle);
//        }
//        if (Math.abs(angle) > 0.5f)
//        {
//            mFlingRunnable.startUsingDistance(-angle, 1, direction);
//        }
//        else
//        {
//            position = arr.get(0).getIndex();
//            setSelectedPositionInt(position);
//            onFinishedMovement();
//        }
//    }
//
//    public int getIndex()
//    {
//        return mSelectedItemIndex;
//    }
//
//    private void resetIndex()
//    {
//        if (mSelectedItemIndex == 7)
//        {
//            mSelectedItemIndex = 0;
//        }
//        if (mSelectedItemIndex == -1)
//        {
//            mSelectedItemIndex = 6;
//        }
//    }
//
//    public void toNextRightItem()
//    {
//        mSelectedItemIndex = mSelectedItemIndex - 1;
//        resetIndex();
//        scrollToChild(mSelectedItemIndex, RIGHT);
//        setSelectedPositionInt(mSelectedItemIndex);
//    }
//
//    public void toNextLeftItem()
//    {
//        mSelectedItemIndex = mSelectedItemIndex + 1;
//        resetIndex();
//        scrollToChild(mSelectedItemIndex, LEFT);
//        setSelectedPositionInt(mSelectedItemIndex);
//    }
//
//    void scrollToChild(int i, int v)
//    {
//        Log.d("ORDER", "scrollToChild");
//        CarouselItem view = (CarouselItem) getAdapter().getView(i, null, null);
//        Log.d("GETVIEW", "scrollToChild");
//        float angle = view.getCurrentAngle();
//        Log.d("selectCurrentAngle", "Angle:" + angle);
//        if (angle == 0)
//        {
//            return;
//        }
//        if (angle > 180.0f)
//        {
//            angle = 360.0f - angle;
//        }
//        else
//        {
//            angle = -angle;
//        }
//        mFlingRunnable.startUsingDistance(angle, 0, v);
//    }
//
//    public void setGravity(int gravity)
//    {
//        if (mGravity != gravity)
//        {
//            mGravity = gravity;
//            requestLayout();
//        }
//    }
//
//    private void setUpChild(CarouselItem child, int index, float angleOffset)
//    {
//        Log.d("ORDER", "setUpChild");
//        // Ignore any layout parameters for child, use wrap content
//        addViewInLayout(child, -1 /* index */, generateDefaultLayoutParams());
//        child.setSelected(index == mSelectedPosition);
//        int h;
//        int w;
//        int d;
//        if (mInLayout)
//        {
//            w = child.getMeasuredWidth();
//            h = child.getMeasuredHeight();
//            d = getMeasuredWidth();
//        }
//        else
//        {
//            w = child.getMeasuredWidth();
//            h = child.getMeasuredHeight();
//            d = getWidth();
//        }
//        child.setCurrentAngle(angleOffset);
//        child.measure(w, h);
//        int childLeft;
//        int childTop = calculateTop(child, true);
//        childLeft = 0;
//        child.layout(childLeft, childTop - 45, w, h);
//        Calculate3DPosition(child, d, angleOffset);
//    }
//
//    /**
//     * Tracks a motion scroll. In reality, this is used to do just about any
//     * movement to items (touch scroll, arrow-key scroll, set an item as
//     * selected).
//     */
//    void trackMotionScroll(float deltaAngle, float deg)
//    {
//        Log.d("ORDER", "trackMotionScroll");
//        for (int i = 0; i < getAdapter().getCount(); i++)
//        {
//            CarouselItem child = (CarouselItem) getAdapter().getView(i, null, null);
//            Log.d("GETVIEW", "trackMotionScroll");
//            float angle = child.getCurrentAngle();
//            angle += deltaAngle;
//            while (angle > 360.0f)
//            {
//                angle -= 360.0f;
//            }
//
//            while (angle < 0.0f)
//            {
//                angle += 360.0f;
//            }
//            child.setCurrentAngle(angle);
//            child.setDegY(deg);
//            Calculate3DPosition(child, getWidth(), angle);
//        }
//        mRecycler.clear();
//        invalidate();
//    }
//
//    private void updateSelectedItemMetadata()
//    {
//
//        View oldSelectedChild = mSelectedChild;
//        View child = mSelectedChild = getChildAt(mSelectedPosition - mFirstPosition);
//        if (child == null)
//        {
//            return;
//        }
//        child.setSelected(true);
//        child.setFocusable(true);
//        if (hasFocus())
//        {
//            child.requestFocus();
//        }
//        if (oldSelectedChild != null)
//        {
//            oldSelectedChild.setSelected(false);
//            oldSelectedChild.setFocusable(false);
//        }
//
//    }
//
//}