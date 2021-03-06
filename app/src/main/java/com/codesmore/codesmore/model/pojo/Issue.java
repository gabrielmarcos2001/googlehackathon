package com.codesmore.codesmore.model.pojo;

import android.graphics.Bitmap;
import android.location.Location;
import android.support.annotation.NonNull;

import com.codesmore.codesmore.integration.backend.pojo.ParseIssue;

import java.util.Date;

/**
 * Created by demouser on 11/9/15.
 */
public class Issue {

    /**
     * Construct a DB Pojo from a Backend POJO
     * @param parseIssue
     */
    public Issue(ParseIssue parseIssue) {
        this.title = parseIssue.getTitle();
        this.description = parseIssue.getDescription();
        this.image = parseIssue.getImage();
        this.parseId = parseIssue.getObjectId();
        this.downvotes = parseIssue.getDownvotes();
        this.upvotes = parseIssue.getUpvoteEs();
        this.latitude = parseIssue.getLatitude();
        this.longitude = parseIssue.getLongitude();

        this.categoryName = parseIssue.getCategoryName();
        // TODO: 11/11/2015 vesko - proper object construction
    }

    public Issue() {
    }


    public String getCategoryName() {
        return categoryName;
    }
    private Long id;
    private Bitmap image;
    private double latitude;
    private double longitude;
    private String title;
    private String categoryName;
    private Category category;
    private String description;
    private Account creator;
    private Account fixer;
    private Integer priority;
    private int upvotes;
    private int downvotes;
    private boolean fixedInd;
    private Date createDate;
    private Date fixedDate;
    private String parseId;

    private boolean isInteractedWith;


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
        longitude = location.getLongitude();
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public boolean getFixedInd() {
        return fixedInd;
    }

    public void setFixedInd(boolean fixedInd) {
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

    public String getParseId() {
        return parseId;
    }

    public void setParseId(String parseId) {
        this.parseId = parseId;
    }

    public boolean isInteractedWith() {
        return isInteractedWith;
    }

    public void setIsInteractedWith(boolean isInteractedWith) {
        this.isInteractedWith = isInteractedWith;
    }
}
