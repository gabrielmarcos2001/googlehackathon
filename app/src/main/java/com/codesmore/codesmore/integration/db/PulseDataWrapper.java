package com.codesmore.codesmore.integration.db;

import android.content.ContentResolver;

import android.content.ContentValues;
import android.database.Cursor;
import android.location.Location;

import com.codesmore.codesmore.integration.db.PulseContract.IssueCategory;
import com.codesmore.codesmore.model.DataFetchedListener;

import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;

import java.util.List;

public class PulseDataWrapper implements DataWrapper {
    private DataWrapper localDataWrapper;

    /**
     * One-argument constructor.
     * @param contentResolver to place calls to.
     */
    public PulseDataWrapper(ContentResolver contentResolver){
        this(new LocalDataWrapper(contentResolver));
    }

    /**
     * Two-argument constructor.
     * @param localDataWrapper to place calls to local
     */
    public PulseDataWrapper(DataWrapper localDataWrapper){
        super();
        this.localDataWrapper = localDataWrapper;
    }

    @Override
    public List<Category> getCategories() {
        return localDataWrapper.getCategories();
    }


    @Override
    public List<Issue> getResolvedIssues(double lat, double lon) {
        return localDataWrapper.getResolvedIssues(lat, lon);
    }

    @Override
    public void insertIssue(Issue issue) {
        localDataWrapper.insertIssue(issue);
    }
}
