package com.codesmore.codesmore.mock;

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

        return result;
    }

    @Override
    public void insertIssue(Issue issue) {

    }
}
