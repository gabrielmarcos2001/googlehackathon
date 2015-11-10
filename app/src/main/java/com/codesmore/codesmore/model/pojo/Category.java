package com.codesmore.codesmore.model.pojo;

import android.content.ContentValues;

import com.codesmore.codesmore.integration.db.PulseContract.IssueCategory;

/**
 * Created by demouser on 11/9/15.
 */
public class Category {

    public static Category from(ContentValues values) {
        if (values == null) {
            return  new Category(null, null);
        }

        Long id = values.getAsLong(IssueCategory._ID);
        String name = values.getAsString(IssueCategory.Columns.ISSUE_CATEGORY);
        String imageUrl = values.getAsString(IssueCategory.Columns.ISSUE_CATEGORY_IMAGE);

        Category category = new Category(name, imageUrl);
        category.setId(id);
        return category;
    }

    private Long id;
    private String name;
    private String imageUrl;

    public Category(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public ContentValues toContentValues() {
        throw new RuntimeException("Implement me!!!");
    }

    public Long getId(){
        return id;
    }

    public void setId(Long value){
        id = value;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return name;
    }
}
