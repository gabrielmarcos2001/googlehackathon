package com.codesmore.codesmore.ui.navdrawer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codesmore.codesmore.R;

/**
 * Created by gabrielmarcos on 9/8/14.
 */
public class ListNavMenuItem extends RelativeLayout {

    /**
     * Views references
     */
    private ImageView mImageView;
    private TextView mTextView;

    /**
     * Item hodling the View Data
     */
    private MenuItem mItem;

    /**
     * Default Constructor
     *
     * @param context
     */
    public ListNavMenuItem(Context context) {

        super(context);

        inflateLayout(context);
    }

    /**
     * Attributes Constructor
     *
     * @param context
     * @param attrs
     */
    public ListNavMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflateLayout(context);

    }

    /**
     * Inflates the layout
     *
     * @param context
     */
    private void inflateLayout(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item_nav_menu, this, true);

        mImageView = (ImageView) findViewById(R.id.item_image);
        mTextView = (TextView) findViewById(R.id.item_title);

        // It updates the views with the item data
        updateViews();

    }

    public void setItem(Object obj) {

        if (obj instanceof MenuItem) {

            mItem = (MenuItem) obj;
            updateViews();
        }
    }

    /**
     * Updates the views with the item data
     */
    private void updateViews() {

        // Validates that the item is not null and the views
        // are already inflated
        if (mItem != null && mTextView != null) {

            mTextView.setText(mItem.getmText());
            mImageView.setImageResource(mItem.getmImageResource());

            if (mItem.getmItemId() == 4) {
                mImageView.setVisibility(View.INVISIBLE);
                mTextView.setTextColor(getResources().getColor(R.color.background_red));
            } else {
                mImageView.setVisibility(View.VISIBLE);
                mTextView.setTextColor(getResources().getColor(R.color.gray));
            }
        }
    }

}
