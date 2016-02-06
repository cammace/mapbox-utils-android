package com.mapbox.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class BubbleLayout extends FrameLayout {
    private int mRotation;

    public BubbleLayout(Context context) {
        super(context);
    }

    public BubbleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BubbleLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mRotation == 1 || mRotation == 3) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        /*
        if (mRotation == 0) {
            super.dispatchDraw(canvas);
            return;
        }

        if (mRotation == 1) {
            canvas.translate(getWidth(), 0);
            canvas.rotate(90, getWidth() / 2, 0);
            canvas.translate(getHeight() / 2, getWidth() / 2);
        } else if (mRotation == 2) {
            canvas.rotate(180, getWidth() / 2, getHeight() / 2);
        } else {
            canvas.translate(0, getHeight());
            canvas.rotate(270, getWidth() / 2, 0);
            canvas.translate(getHeight() / 2, -getWidth() / 2);
        }*/

        super.dispatchDraw(canvas);
    }
}