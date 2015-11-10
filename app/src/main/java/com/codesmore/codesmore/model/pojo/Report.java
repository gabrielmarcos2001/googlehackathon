package com.codesmore.codesmore.model.pojo;

/**
 * Created by demouser on 11/9/15.
 */
public class Report {

    private Category category;
    private String description;

    public Report(Category category, String description) {
        this.category = category;
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
