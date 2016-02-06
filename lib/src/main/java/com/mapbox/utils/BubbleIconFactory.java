package com.mapbox.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A bubble icon is used to replace a marker icon. It is used to include snippets of information on
 * a marker itself such as price or address. They appear on a MapView looking like a miniature sized
 * infoWindow containing text of type String. This offers a huge advantage in that the markers are
 * already displaying vital information to users without them even having to click/tap the marker.
 * Since the bubble icons are generated on the fly you can customize things such as background color
 * and  text font color.
 * <p/>
 */
public class BubbleIconFactory {

    // Default color values
    public static final int bgColor = 0xffffffff;
    public static final int fontColor = 0xff7f7f7f;

    private TextView textView;
    private ViewGroup container;
    private BubbleDrawable background;

    /**
     * Construct a new BubbleIcon with the default color values.
     * @param context Same context as mapView.
     */
    public BubbleIconFactory(Context context){
        background = new BubbleDrawable(context.getResources());
        container = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.text_bubble, container, false);
        BubbleLayout bubbleLayout = (BubbleLayout) container.getChildAt(0);
        textView = (TextView) bubbleLayout.findViewById(R.id.text);
        setColor(bgColor);
        setTextColor(fontColor);
    }

    /**
     * This method generates a bubble map icon that includes a given string. The style needs to be
     * defined before calling or else the bubble will use default styling. If string text is null or
     * empty, the icons still drawn but with an empty center.
     * @param text What you want the icon to display.
     */
    public Bitmap makeIcon(String text) {
        if (textView != null) {
            textView.setText(text);
        }

        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        container.measure(measureSpec, measureSpec);

        int measuredWidth = container.getMeasuredWidth();
        int measuredHeight = container.getMeasuredHeight();

        container.layout(0, 0, measuredWidth, measuredHeight);

        Bitmap r = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
        r.eraseColor(Color.TRANSPARENT);

        Canvas canvas = new Canvas(r);

        container.draw(canvas);
        return r;
    }

    /**
     * Set the markers background color, the default is white.
     * @param color Hex color.
     */
    public void setColor(int color) {
        background.setColor(color);
        setBackground(background);
    }

    /**
     * Set the TextView font color.
     * @param color Hex color.
     */
    public void setTextColor(int color) {
        if (textView != null) {
            textView.setTextColor(color);
        }
    }

    /**
     * Here's where the backgrounds set. Supression of warning deprecation is used due to
     * setBackgroundDrawable() being deprecated after API level 16; now all it does is call
     * setBackground().
     * @param background BubbleDrawable object.
     */
    @SuppressWarnings("deprecation")
    public void setBackground(Drawable background) {
        container.setBackgroundDrawable(background);
    }

    /**
     * Allow for custom padding around TextView. Default values are: left & right = 10 and top & bottom = 5.
     * @param left int padding value for left of TextView.
     * @param top int padding value for top of TextView.
     * @param right int padding value for right of TextView.
     * @param bottom int padding value for bottom of TextView.
     */
    public void setTextPadding(int left, int top, int right, int bottom){
        textView.setPadding(left, top, right, bottom);
    }
}