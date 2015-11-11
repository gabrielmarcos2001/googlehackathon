package com.codesmore.codesmore.integration.converter;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.BitmapFactory;

import com.codesmore.codesmore.integration.db.PulseContract;
import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Account;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;
import com.codesmore.codesmore.utils.CameraUtils;

import java.util.Date;

import static android.provider.BaseColumns._ID;
import static com.codesmore.codesmore.integration.db.PulseContract.getContentValuesFrom;

/**
 * Created by Darryl Staflund on 11/10/2015.
 */
public class IssueConverter implements Converter<Issue> {
    private DataWrapper wrapper;

    public IssueConverter(){
    }

    public IssueConverter(DataWrapper wrapper){
        this.wrapper = wrapper;
    }

    public void setDataWrapper(DataWrapper value){
        wrapper = value;
    }

    @Override
    public ContentValues convert(Cursor cursor) {
        return getContentValuesFrom(cursor);
    }

    @Override
    public ContentValues convert(Issue object) {
        if (object == null){
            return null;
        }
        ContentValues values = new ContentValues();
        values.put(_ID, object.getId());
        values.put(PulseContract.Issue.Columns.LATITUDE, object.getLatitude());
        values.put(PulseContract.Issue.Columns.LONGITUDE, object.getLongitude());
        values.put(PulseContract.Issue.Columns.IMAGE, CameraUtils.toByteArray(object.getImage()));
        values.put(PulseContract.Issue.Columns.TITLE, object.getTitle());
        values.put(PulseContract.Issue.Columns.ISSUE_CATEGORY_ID, object.getCategory().getId());
        values.put(PulseContract.Issue.Columns.DESCRIPTION, object.getDescription());
        values.put(PulseContract.Issue.Columns.CREATOR_ID, object.getCreator().getId());
        values.put(PulseContract.Issue.Columns.FIXER_ID, object.getFixer().getId());
        values.put(PulseContract.Issue.Columns.PRIORITY, object.getPriority());
        values.put(PulseContract.Issue.Columns.UPVOTES, object.getUpvotes());
        values.put(PulseContract.Issue.Columns.DOWNVOTES, object.getDownvotes());
        values.put(PulseContract.Issue.Columns.FIXED_IND, object.getFixedInd());
        values.put(PulseContract.Issue.Columns.CREATE_DATE, object.getCreateDate().getTime());
        values.put(PulseContract.Issue.Columns.FIX_DATE, object.getFixedDate().getTime());
        values.put(PulseContract.Issue.Columns.PARSE_ID, object.getParseId());
        return values;
    }

    @Override
    public Issue convert(ContentValues values) {
        if (values == null){
            return null;
        }

        byte[] imageData = values.getAsByteArray(PulseContract.Issue.Columns.IMAGE);

        Long categoryId = values.getAsLong(PulseContract.Issue.Columns.ISSUE_CATEGORY_ID);
        Category category = wrapper.getCategory(categoryId);

        Long creatorId = values.getAsLong(PulseContract.Issue.Columns.CREATOR_ID);
        Account creator = wrapper.getAccount(creatorId);

        Long fixerId = values.getAsLong(PulseContract.Issue.Columns.FIXER_ID);
        Account fixer = wrapper.getAccount(fixerId);

        Issue issue = new Issue();
        issue.setId(values.getAsLong(_ID));
        issue.setImage(BitmapFactory.decodeByteArray(imageData, 0, imageData.length));
        issue.setLatitude(values.getAsDouble(PulseContract.Issue.Columns.LATITUDE));
        issue.setLongitude(values.getAsDouble(PulseContract.Issue.Columns.LONGITUDE));
        issue.setTitle(values.getAsString(PulseContract.Issue.Columns.TITLE));
        issue.setCategory(category);
        issue.setDescription(values.getAsString(PulseContract.Issue.Columns.DESCRIPTION));
        issue.setCreator(creator);
        issue.setFixer(fixer);
        issue.setPriority(values.getAsInteger(PulseContract.Issue.Columns.PRIORITY));
        issue.setUpvotes(values.getAsInteger(PulseContract.Issue.Columns.UPVOTES));
        issue.setDownvotes(values.getAsInteger(PulseContract.Issue.Columns.DOWNVOTES));
        issue.setFixedInd(values.getAsBoolean(PulseContract.Issue.Columns.FIXED_IND));
        issue.setCreateDate(new Date(values.getAsLong(PulseContract.Issue.Columns.CREATE_DATE)));
        issue.setFixedDate(new Date(values.getAsLong(PulseContract.Issue.Columns.FIX_DATE)));
        issue.setParseId(values.getAsString(PulseContract.Issue.Columns.PARSE_ID));
        return issue;
    }
}
