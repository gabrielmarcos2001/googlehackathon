package com.codesmore.codesmore.model.pojo;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.codesmore.codesmore.integration.db.PulseContract.IssueCategory;

/**
 * Created by demouser on 11/9/15.
 */
public class Category implements Parcelable{

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imageUrl);
    }

    private Category(Parcel in) {
        name = in.readString();
        imageUrl = in.readString();
    }

    public static final Parcelable.Creator<Category> CREATOR
            = new Parcelable.Creator<Category>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

}
