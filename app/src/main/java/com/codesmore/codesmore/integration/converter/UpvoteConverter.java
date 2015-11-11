package com.codesmore.codesmore.integration.converter;

import android.content.ContentValues;
import android.database.Cursor;

import com.codesmore.codesmore.integration.db.PulseContract;
import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Account;
import com.codesmore.codesmore.model.pojo.Issue;
import com.codesmore.codesmore.model.pojo.Upvote;

import static android.provider.BaseColumns._ID;
import static com.codesmore.codesmore.integration.db.PulseContract.getContentValuesFrom;

/**
 * Created by Darryl Staflund on 11/10/2015.
 */
public class UpvoteConverter implements Converter<Upvote> {
    private DataWrapper wrapper;

    public UpvoteConverter(){
    }

    public UpvoteConverter(DataWrapper wrapper){
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
    public ContentValues convert(Upvote object) {
        if (object == null){
            return null;
        }

        ContentValues values = new ContentValues();
        values.put(_ID, object.getId());
        values.put(PulseContract.Upvote.Columns.UPVOTED_ISSUE_ID, object.getUpvotedIssue().getId());
        values.put(PulseContract.Upvote.Columns.UPVOTER_ID, object.getUpvoter().getId());
        return values;
    }



    @Override
    public Upvote convert(ContentValues values) {
        if (values == null){
            return new Upvote();
        }

        String upvotedIssueParseId = values.getAsString(PulseContract.Upvote.Columns.UPVOTED_ISSUE_ID);
        Issue upvotedIssue = wrapper.getIssue(upvotedIssueParseId);

        Long upvoterId = values.getAsLong(PulseContract.Upvote.Columns.UPVOTER_ID);
        Account upvoter = wrapper.getAccount(upvoterId);

        Upvote upvote = new Upvote();
        upvote.setId(values.getAsLong(PulseContract.Upvote._ID));
        upvote.setUpvotedIssue(upvotedIssue);
        upvote.setUpvoter(upvoter);
        return upvote;

    }

}
