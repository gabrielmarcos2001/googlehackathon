package com.codesmore.codesmore.integration.backend.pojo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.codesmore.codesmore.PulseApp;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

public abstract class BaseParseObject extends ParseObject {

    private byte[] mImageData;
    private Bitmap mImageBitmap;

    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title", title);
    }

    public void setImageFile(ParseFile image) {
        put("image", image);
        try {
            this.mImageData = image.getData();
        } catch (ParseException ignored) {
        }
    }

    public Bitmap getImage() {
        if (mImageBitmap == null) {
            byte[] imageData = getImageData();
            if (imageData != null) {
                mImageBitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            }
        }

        return mImageBitmap;
    }

    private ParseFile getImageFile() {
        return (ParseFile) get("image");
    }

    private byte[] getImageData() {
        // Check if the imageData is already loaded
        if (mImageData != null) {
            return mImageData;
        }

        // Try to load the imageData from the ParseFile
        ParseFile imageFile = getImageFile();

        try {
            return imageFile.getData();
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d(PulseApp.TAG, "cannot load image for Item: " + getTitle());
        } catch (NullPointerException ignored) {
            Log.d(PulseApp.TAG, "no image found for Item: " + getTitle());
        }

        return null;
    }
}
