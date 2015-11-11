package com.codesmore.codesmore.integration.backend.pojo;

import android.graphics.Bitmap;

import com.codesmore.codesmore.model.pojo.Issue;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;

import java.io.ByteArrayOutputStream;

@ParseClassName("Issue")
public class ParseIssue extends BaseParseObject {

    /**
     * @param issue DB data model object
     * @return BACKEND data model object
     */
    public static ParseIssue from(Issue issue) {
        ParseIssue parseIssue = new ParseIssue();
        parseIssue.setTitle(issue.getTitle());
        parseIssue.setDescription(issue.getDescription());
        parseIssue.setUpvotes(issue.getUpvotes());
        parseIssue.setDownvotes(issue.getDownvotes());
        parseIssue.setFixedIndicator(issue.getFixedInd());

        // TODO: 11/11/2015  parseIssue.setCategory();

        // Add the image. Bit ugly for now
        Bitmap image = issue.getImage();
        if (image != null) {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 90, bytes);
            parseIssue.setImageFile(new ParseFile("image.png", bytes.toByteArray()));
        }

        ParseGeoPoint geoPoint = new ParseGeoPoint(issue.getLatitude(), issue.getLongtitude());
        parseIssue.setLocation(geoPoint);
        return parseIssue;
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }

    public void setLocation(ParseGeoPoint location) {
        put("location", location);
    }

    public void setCategory(ParseCategory category) {
        put("category", category);
    }

    public void setUpvotes(int upvotes) {
        put("upvotes", upvotes);
    }

    public int getUpvoteEs() {
        return getInt("upvotes");
    }

    public void setDownvotes(int downvotes) {
        put("downvotes", downvotes);
    }

    public int getDownvotes() {
        return getInt("downvotes");
    }

    public void setFixedIndicator(boolean fixedIndicator) {
        put("fixed_indicator", fixedIndicator);
    }
}
