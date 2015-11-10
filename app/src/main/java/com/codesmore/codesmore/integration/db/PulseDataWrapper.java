package com.codesmore.codesmore.integration.db;

import android.content.ContentResolver;

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
    public List<Issue> getResolvedIssues() {
        return localDataWrapper.getResolvedIssues();
    }

    @Override
    public void insertIssue(Issue issue) {
        localDataWrapper.insertIssue(issue);
    }
}
