package com.codesmore.codesmore.ui.navdrawer;

/**
 * Created by gabrielmarcos on 12/2/14.
 */
public class MenuItem {

    private int mItemId;
    private int mImageResource;
    private String mText;

    /**
     * Default constructor
     *
     * @param mImageResource
     */
    public MenuItem(int itemId, int mImageResource) {
        this.mItemId = itemId;
        this.mImageResource = mImageResource;
        this.mText = "";
    }

    public int getmItemId() {
        return mItemId;
    }

    public void setmItemId(int mItemId) {
        this.mItemId = mItemId;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public void setmImageResource(int mImageResource) {
        this.mImageResource = mImageResource;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }
}
