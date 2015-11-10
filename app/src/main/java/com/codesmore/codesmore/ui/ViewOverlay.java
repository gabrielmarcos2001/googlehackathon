package com.codesmore.codesmore.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.codesmore.codesmore.R;
import com.codesmore.codesmore.utils.UnitsConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by gabrielmarcos on 11/10/15.
 */
public class ViewOverlay extends View {

    private Paint mPaint;
    private List<Circle> mCircles;

    public ViewOverlay(Context context) {
        super(context);

        mCircles = new ArrayList<>();

        for (int i=0; i < 200; i++) {

            Circle circle = new Circle();
            Random random = new Random();

            circle.posX = random.nextInt((int) UnitsConverter.convertDpToPixel(600, getContext()));
            circle.posY = random.nextInt((int) UnitsConverter.convertDpToPixel(600, getContext())) + (int) UnitsConverter.convertDpToPixel(600, getContext());
            circle.radious = random.nextInt((int) UnitsConverter.convertDpToPixel(10, getContext()));
            circle.alpha = random.nextInt(75);
            circle.speed = random.nextInt(2) + 1;

            mCircles.add(circle);
        }

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(getResources().getColor(R.color.background_red));
    }

    public ViewOverlay(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i=0; i < 200; i++) {

            Circle circle = mCircles.get(i);
            mPaint.setAlpha((int) circle.alpha);
            canvas.drawCircle(circle.posX, circle.posY, circle.radious, mPaint);

            circle.posY -= circle.speed;

            if (circle.posY < 0) {
                Random random = new Random();
                circle.posY = random.nextInt((int) UnitsConverter.convertDpToPixel(600, getContext())) + (int) UnitsConverter.convertDpToPixel(600, getContext());
            }

        }

        invalidate();

    }
}
