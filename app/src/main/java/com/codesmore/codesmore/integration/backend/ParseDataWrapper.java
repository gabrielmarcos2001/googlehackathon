package com.codesmore.codesmore.integration.backend;

import android.support.annotation.NonNull;

import com.codesmore.codesmore.integration.backend.pojo.ParseCategory;
import com.codesmore.codesmore.integration.backend.pojo.ParseIssue;
import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ParseDataWrapper implements DataWrapper {

    @Override
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        ParseQuery<ParseCategory> query = ParseQuery.getQuery("Category");
        try {
            List<ParseCategory> parseCategories = query.find();

            // TODO: 11/10/2015 proper conversion between Parse -> DB models
            categories = new ArrayList<>(parseCategories.size());
            for (ParseCategory pc : parseCategories) {
                categories.add(new Category(pc.getTitle(), pc.getImage()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public void updateIssue(Issue issue) {

    }

    @Override
    public List<Issue> getResolvedIssues() {
        return null;
    }

    @Override
    public void insertIssue(@NonNull Issue issue) {
        ParseIssue parseIssue = ParseIssue.from(issue);

        try {
            parseIssue.save();
        } catch (ParseException ignored) {
            // TODO: 11/10/2015 error handling
        }
    }
}
