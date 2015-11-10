package com.codesmore.codesmore.integration.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

import com.codesmore.codesmore.integration.db.PulseContract.IssueCategory;
import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;

import java.util.ArrayList;
import java.util.List;

import static com.codesmore.codesmore.integration.db.PulseContract.getContentValuesFrom;

public class PulseDataWrapper implements DataWrapper {
    private ContentResolver contentResolver;

    /**
     * Default constructor.
     * @param contentResolver to place calls to.
     */
    public PulseDataWrapper(ContentResolver contentResolver){
        this.contentResolver = contentResolver;
    }

    /**
     * Returning mock data until I can insert values in database.
     * @return
     */
    @Override
    public List<Category> getCategories() {
        Cursor cursor = contentResolver.query(IssueCategory.CONTENT_URI, null, null, null, null);

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
    public void saveIssue(Issue issue) {
        
    }

    @Override
    public List<Issue> getResolvedIssues() {
        return null;
    }

    @Override
    public void insertIssue(Issue issue) {
        if (issue == null){
            throw new IllegalArgumentException("Issue is required.");
        }
    }
}
