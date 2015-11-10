package com.codesmore.codesmore.model.pojo;

/**
 * Created by demouser on 11/9/15.
 */
public class Category {

    private String name;
    private String imageUrl;

    public Category(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
