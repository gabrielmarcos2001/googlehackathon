package com.codesmore.codesmore.model.pojo;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by demouser on 11/9/15.
 */
public class Category {

    public static Category from(Cursor cursor) {
        throw new RuntimeException("Implement me!!!");
    }

    private String name;
    private String imageUrl;

    public Category(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public ContentValues toContentValues() {
        throw new RuntimeException("Implement me!!!");
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
