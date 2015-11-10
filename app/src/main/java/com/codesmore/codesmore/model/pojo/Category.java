package com.codesmore.codesmore.model.pojo;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by demouser on 11/9/15.
 */
public class Category implements Parcelable{

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
