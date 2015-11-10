package com.codesmore.codesmore.integration.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

import com.codesmore.codesmore.model.DataFetchedListener;
import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;

import java.util.ArrayList;
import java.util.List;

import static com.codesmore.codesmore.integration.db.PulseContract.getContentValuesFrom;

/**
 * Created by Darryl Staflund on 11/10/2015.
 */
public class LocalDataWrapper implements DataWrapper {
    private ContentResolver contentResolver;

    /**
     * Default constructor.
     * @param contentResolver to place calls to.
     */
    public LocalDataWrapper(ContentResolver contentResolver){
        this.contentResolver = contentResolver;
    }

    @Override
    public List<Category> getCategories() {
        Cursor cursor = contentResolver.query(
            PulseContract.IssueCategory.CONTENT_URI,
            null,
            null,
            null,
            null
        );

        List<Category> categories = new ArrayList<>();
        if (cursor != null){
            while(cursor.moveToNext()){
                ContentValues values = getContentValuesFrom(cursor);
                Category category = Category.from(values);
                categories.add(category);
            }
        }
        return categories;
    }

    @Override
    public void getResolvedIssues(double lat, double lon, DataFetchedListener listener) {
        Cursor cursor = contentResolver.query(
            PulseContract.Issue.CONTENT_URI,
            null,
            PulseContract.Issue.Constraints.BY_RESOLVED_STATUS,
            new String[] { Integer.toString(1) },
            PulseContract.Issue.TABLE_NAME + "." + PulseContract.Issue.Columns.UPVOTES + " DESC"
        );

        List<Issue> issues = new ArrayList<>();
        if (cursor != null){
            while(cursor.moveToNext()){
                ContentValues values = getContentValuesFrom(cursor);
                Issue issue = Issue.from(values);
                issues.add(issue);
            }
        }
        listener.onCompletedIssuesLoaded(issues);
        //return issues;
    }

    @Override
    public void insertIssue(Issue issue) {
        if (issue == null){
            throw new IllegalArgumentException("Issue is required.");
        }

        contentResolver.insert(PulseContract.Issue.CONTENT_URI, issue.toContentValues());
    }
}
