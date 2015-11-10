package com.codesmore.codesmore.model.pojo;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by demouser on 11/9/15.
 */
public class Report {

    public static Report from(Cursor cursor) {
        throw new RuntimeException("Implement me!!!");
    }

    private Category category;
    private String description;

    public Report(Category category, String description) {
        this.category = category;
        this.description = description;
    }

    public ContentValues toContentValues() {
        throw new RuntimeException("Implement me!!!");
    }
    
    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
