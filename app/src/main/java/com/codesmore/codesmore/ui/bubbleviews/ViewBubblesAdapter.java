package com.codesmore.codesmore.ui.bubbleviews;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.codesmore.codesmore.R;
import com.codesmore.codesmore.model.pojo.Issue;
import com.codesmore.codesmore.utils.UnitsConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by gabrielmarcos on 11/9/15.
 */
public class ViewBubblesAdapter extends RelativeLayout{

    public interface BubblesInterface {
        void onBubbleSelected(ViewBubble bubble);
        void onBubbleUnselected();
        void upVoteIssue(ViewBubble bubble);
        void downVoteIssue(ViewBubble bubble);
    }

    private List<Point> mBubblesPositions;
    private List<Issue> mItems;
    private List<ViewBubble> mBubbles;
    private ViewGroup mContainer;

    private BubblesInterface mInterface;

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

        initBubblesPoint();

    }

    public void showBubbles() {

        int delayOffset= 0;

        for (ViewBubble bubble : mBubbles) {
            Random r = new Random();

            bubble.showBubbleWithDelay(delayOffset + r.nextInt(100));
            delayOffset += 200;

        }
    }

    /**
     * We Initialize the Points were we are going to be displaying Bubbles
     */
    private void initBubblesPoint() {

        // We support 6 items at a time
        Point point = new Point((int) UnitsConverter.convertDpToPixel(-200, getContext()),(int) UnitsConverter.convertDpToPixel(-350, getContext()));
        Point point2 = new Point((int) UnitsConverter.convertDpToPixel(300, getContext()),(int) UnitsConverter.convertDpToPixel(0, getContext()));
        Point point3 = new Point((int) UnitsConverter.convertDpToPixel(-250, getContext()),(int) UnitsConverter.convertDpToPixel(300, getContext()));
        Point point4 = new Point((int) UnitsConverter.convertDpToPixel(0, getContext()),(int) UnitsConverter.convertDpToPixel(-50, getContext()));
        Point point5 = new Point((int) UnitsConverter.convertDpToPixel(270, getContext()),(int) UnitsConverter.convertDpToPixel(250, getContext()));
        Point point6 = new Point((int) UnitsConverter.convertDpToPixel(220, getContext()),(int) UnitsConverter.convertDpToPixel(-310, getContext()));

        mBubblesPositions.add(point);
        mBubblesPositions.add(point2);
        mBubblesPositions.add(point6);
        mBubblesPositions.add(point3);
        mBubblesPositions.add(point4);
        mBubblesPositions.add(point5);
    }

    public void setItems(final List<Issue> items) {

        if (haveVisibleBubbles()) {

            dismissVisibleBubbles();

            final Handler fakeContextualEvent = new Handler();
            fakeContextualEvent.postDelayed(new Runnable() {
                @Override
                public void run() {

                    mItems = items;
                    generateBubbles();
                    showBubbles();

                }
            }, 500);


        } else {

            this.mItems = items;
            generateBubbles();
            showBubbles();

        }

    }

    public void dismissVisibleBubbles() {
        for (ViewBubble bubble : mBubbles) {
            if (bubble.isVisible()) {
                bubble.dismissBubble();
            }
        }
    }

    public boolean haveVisibleBubbles() {

        if (mBubbles != null) {
            for (ViewBubble bubble : mBubbles) {
                if (bubble.isVisible()) {
                    return true;
                }
            }
        }

        return false;
    }

    public void setmInterface(BubblesInterface mInterface) {
        this.mInterface = mInterface;
    }

    private void generateBubbles() {

        //int delayOffset= 0;
        int bubbleIndex = 0;

        mBubbles = new ArrayList<>();

        for (Issue issue : mItems) {

            ViewBubble bubble = new ViewBubble(getContext());
            mContainer.addView(bubble);

            Point point = mBubblesPositions.get(bubbleIndex);

            bubble.setPosition(point.x,point.y);
            bubble.setmIssueData(issue);

            mBubbles.add(bubble);

            bubble.setmInterface(new ViewBubble.BubbleInterface() {
                @Override
                public void onReleased(ViewBubble bubble) {
                    mInterface.onBubbleUnselected();
                }

                @Override
                public void onSelected(ViewBubble bubble) {
                    mInterface.onBubbleSelected(bubble);
                }

                @Override
                public void onDownVoted(ViewBubble bubble) {
                    mInterface.downVoteIssue(bubble);
                }

                @Override
                public void onUpVoted(ViewBubble bubble) {
                    mInterface.upVoteIssue(bubble);
                }
            });

            bubbleIndex ++;

        }

    }

}
