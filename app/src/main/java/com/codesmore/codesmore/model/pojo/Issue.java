package com.codesmore.codesmore.model.pojo;

import android.content.ContentValues;
import android.database.Cursor;
<<<<<<< HEAD
import android.os.Parcel;
import android.os.Parcelable;
=======
import android.graphics.Bitmap;
>>>>>>> master

/**
 * Created by demouser on 11/9/15.
 */
public class Issue implements Parcelable{

    private Bitmap image;

    public Issue() {

    }

    public static Issue from(Cursor cursor) {
        throw new RuntimeException("Implement me!!!");
    }

    private Category category;
    private String description;
    private String imageUrl;
    private String submittedBy;
    private int upVotes;
    private int downVotes;
    private String resolvedBy;
    private String resolvedOn;
    private int priority;

    public Issue(Category category, String description) {
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public String getResolvedBy() {
        return resolvedBy;
    }

    public String getResolvedOn() {
        return resolvedOn;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public void setResolvedBy(String resolvedBy) {
        this.resolvedBy = resolvedBy;
    }

    public void setResolvedOn(String resolvedOn) {
        this.resolvedOn = resolvedOn;
    }

    public int getPriority(){ return priority;}

    public void setPriority(int priority){ this.priority = priority;}

    public void upVote(){
        this.upVotes ++;
        //TODO: Push changes to data source
    }

    public void downVote(){
        this.downVotes ++;
        //TODO: Push changes to data source
    }

    public int getRankRating(){
        return this.upVotes - this.downVotes;
    }

    @Override
    public String toString() {
        return description;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(category, flags);
        dest.writeString(description);
    }

    private Issue(Parcel in) {
        category = in.readParcelable(Category.class.getClassLoader());
        description = in.readString();
    }

    public static final Parcelable.Creator<Issue> CREATOR
            = new Parcelable.Creator<Issue>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Issue createFromParcel(Parcel in) {
            return new Issue(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Issue[] newArray(int size) {
            return new Issue[size];
        }
    };

    public void setImage(Bitmap image) {
        this.image = image;
    }

}
