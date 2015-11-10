package com.codesmore.codesmore.ui;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.codesmore.codesmore.R;
import com.codesmore.codesmore.UtilsUnitsConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by gabrielmarcos on 11/9/15.
 */
public class ViewBubblesAdapter extends RelativeLayout{

    private List<Point> mBubblesPositions;
    private List<String> mItems;
    private List<ViewBubble> mBubbles;
    private ViewGroup mContainer;

    public ViewBubblesAdapter(Context context) {
        super(context);
        inflateLayout(context, null);
    }

    public ViewBubblesAdapter(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context, null);

    }

    private void inflateLayout(Context context, AttributeSet attrs) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_bubbles_adapter, this, true);

        mContainer = (ViewGroup)findViewById(R.id.container);

        mItems = new ArrayList<>();
        mBubblesPositions = new ArrayList<>();

        mockData();

        initBubblesPoint();

        generateBubbles();

    }

    public void showBubbles() {

        int delayOffset= 0;

        for (ViewBubble bubble : mBubbles) {
            Random r = new Random();

            bubble.showBubbleWithDelay(delayOffset + r.nextInt(100));
            delayOffset += 200;

        }
    }

    private void initBubblesPoint() {

        // We support 6 items at a time
        Point point = new Point((int)UtilsUnitsConverter.convertDpToPixel(-200,getContext()),(int)UtilsUnitsConverter.convertDpToPixel(-350,getContext()));
        Point point2 = new Point((int)UtilsUnitsConverter.convertDpToPixel(300,getContext()),(int)UtilsUnitsConverter.convertDpToPixel(0,getContext()));
        Point point3 = new Point((int)UtilsUnitsConverter.convertDpToPixel(-250,getContext()),(int)UtilsUnitsConverter.convertDpToPixel(300,getContext()));
        Point point4 = new Point((int)UtilsUnitsConverter.convertDpToPixel(0,getContext()),(int)UtilsUnitsConverter.convertDpToPixel(-50,getContext()));
        Point point5 = new Point((int)UtilsUnitsConverter.convertDpToPixel(270,getContext()),(int)UtilsUnitsConverter.convertDpToPixel(250,getContext()));
        Point point6 = new Point((int)UtilsUnitsConverter.convertDpToPixel(220,getContext()),(int)UtilsUnitsConverter.convertDpToPixel(-310,getContext()));

        mBubblesPositions.add(point);
        mBubblesPositions.add(point2);
        mBubblesPositions.add(point6);
        mBubblesPositions.add(point3);
        mBubblesPositions.add(point4);
        mBubblesPositions.add(point5);
    }

    private void mockData() {
        mItems.add("23");
        mItems.add("10");
        mItems.add("45");
        mItems.add("30");
        mItems.add("123");
        mItems.add("20");
    }

    public void setItems(List<String> items) {
        this.mItems = items;
    }


    private void generateBubbles() {

        //int delayOffset= 0;
        int bubbleIndex = 0;

        mBubbles = new ArrayList<>();

        for (String string : mItems) {

            ViewBubble bubble = new ViewBubble(getContext());
            mContainer.addView(bubble);

            Point point = mBubblesPositions.get(bubbleIndex);

            bubble.setPosition(point.x,point.y);
            bubble.setNumber(Integer.valueOf(string));

            mBubbles.add(bubble);

            bubbleIndex ++;

        }
    }


}
