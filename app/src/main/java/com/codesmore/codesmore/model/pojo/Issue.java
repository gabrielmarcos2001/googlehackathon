package com.codesmore.codesmore.model.pojo;

import android.graphics.Bitmap;
import android.location.Location;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by demouser on 11/9/15.
 */
public class Issue {

    public Issue() {
    }

    private Long id;
    private Bitmap image;
    private double latitude;
    private double longtitude;
    private String title;
    private Category category;
    private String description;
    private Account creator;
    private Account fixer;
    private Integer priority;
    private Integer upvotes;
    private Integer downvotes;
    private Boolean fixedInd;
    private Date createDate;
    private Date fixedDate;

    public Long getId(){
        return id;
    }

    public void setId(Long value){
        id = value;
    }

    public Issue(Category category, String description) {
        this.category = category;
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setLocation(@NonNull Location location) {
        latitude = location.getLatitude();
        longtitude = location.getLongitude();
    }

    public Bitmap getImage() {
        return image;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
    }

    public Account getFixer() {
        return fixer;
    }

    public void setFixer(Account fixer) {
        this.fixer = fixer;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public Integer getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    public Boolean getFixedInd() {
        return fixedInd;
    }

    public void setFixedInd(Boolean fixedInd) {
        this.fixedInd = fixedInd;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getFixedDate() {
        return fixedDate;
    }

    public void setFixedDate(Date fixedDate) {
        this.fixedDate = fixedDate;
    }
}
