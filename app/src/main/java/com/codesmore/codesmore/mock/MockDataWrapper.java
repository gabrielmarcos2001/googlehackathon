package com.codesmore.codesmore.mock;

import android.util.Log;

import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;

import java.util.ArrayList;
import java.util.List;

public class MockDataWrapper implements DataWrapper {

    @Override
    public List<Category> getCategories() {
        Category c = new Category("cat 1", null);
        Category c1 = new Category("cat 2", null);
        Category c2 = new Category("cat 3", null);
        Category c3 = new Category("cat 4", null);
        List<Category> result = new ArrayList<>(4);
        result.add(c);
        result.add(c1);
        result.add(c2);
        result.add(c3);

        Log.d("vesko", "getCategories() called with: " + "");
        return result;
    }

    @Override
    public void saveIssue(Issue issue) {

    }

    @Override
    public List<Issue> getResolvedIssues() {
        Category c  = new Category("Test", "www.test.com");
        Issue i1 = new Issue(c, "Test Issue 1");
        Issue i2 = new Issue(c, "Test Issue 2");
        Issue i3  = new Issue(c, "Test Issue 3");

        List<Issue> testList = new ArrayList<Issue>();
        testList.add(i1);
        testList.add(i2);
        testList.add(i3);
        return testList;
    }
}