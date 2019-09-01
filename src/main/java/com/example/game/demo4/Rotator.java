package com.example.game.demo4;

import android.content.Context;
import android.view.animation.AnimationUtils;

public class Rotator {
    private float mStartAngle;
    private float mCurrAngle;
    private long mStartTime;
    private long mDuration;
    private float mDeltaAngle;
    private boolean mFinished;
    private int direction;
    private float mCurrDeg;

    public Rotator(Context context)
    {
        mFinished = true;
    }

    public final boolean isFinished()
    {
        return mFinished;
    }

    /**
     * Force the finished field to a particular value.
     *
     * @param finished
     *            The new finished value.
     */
    public final void forceFinished(boolean finished)
    {
        mFinished = finished;
    }

    /**
     * Returns how long the scroll event will take, in milliseconds.
     *
     * @return The duration of the scroll in milliseconds.
     */
    public final long getDuration()
    {
        return mDuration;
    }

    /**
     * Returns the current X offset in the scroll.
     *
     * @return The new X offset as an absolute distance from the origin.
     */
    public final float getCurrAngle()
    {
        return mCurrAngle;
    }

    public final float getStartAngle()
    {
        return mStartAngle;
    }

    /**
     * Returns the time elapsed since the beginning of the scrolling.
     *
     * @return The elapsed time in milliseconds.
     */
    public int timePassed()
    {
        return (int) (AnimationUtils.currentAnimationTimeMillis() - mStartTime);
    }

    public int getdirection()
    {
        return this.direction;
    }

    public float getCurrDeg()
    {
        return this.mCurrDeg;
    }

    /**
     * Extend the scroll animation.
     */
    public void extendDuration(int extend)
    {
        int passed = timePassed();
        mDuration = passed + extend;
        mFinished = false;
    }

    /**
     * Stops the animation. Contrary to {@link #forceFinished(boolean)},
     * aborting the animating cause the scroller to move to the final x and y
     * position
     *
     * @see #forceFinished(boolean)
     */
    public void abortAnimation()
    {
        mFinished = true;
    }

    /**
     * Call this when you want to know the new location. If it returns true, the
     * animation is not yet finished. loc will be altered to provide the new
     * location.
     */
    public boolean computeAngleOffset()
    {
        if (mFinished)
        {
            return false;
        }
        long systemClock = AnimationUtils.currentAnimationTimeMillis();
        long timePassed = systemClock - mStartTime;
        if (timePassed < mDuration)
        {

            float sc = (float) timePassed / mDuration;
            mCurrAngle = mStartAngle + Math.round(mDeltaAngle * sc);
            mCurrDeg = direction == 0 ? (Math.round(360 * sc)) : (Math.round(-360 * sc));
            return true;
        }
        else
        {
            mCurrAngle = mStartAngle + mDeltaAngle;
            mCurrDeg = direction == 0 ? 360 : -360;
            mFinished = true;
            return false;
        }
    }

    public void startRotate(float startAngle, float dAngle, int duration, int direction)
    {
        mFinished = false;
        mDuration = duration;
        mStartTime = AnimationUtils.currentAnimationTimeMillis();
        mStartAngle = startAngle;
        mDeltaAngle = dAngle;
        this.direction = direction;
    }

}
