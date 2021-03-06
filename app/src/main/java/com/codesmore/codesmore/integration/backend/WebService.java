package com.codesmore.codesmore.integration.backend;

import android.support.annotation.NonNull;

import com.codesmore.codesmore.integration.backend.pojo.ParseCategory;
import com.codesmore.codesmore.integration.backend.pojo.ParseIssue;
import com.codesmore.codesmore.model.pojo.Account;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class WebService {

    public static final String TABLE_CATEGORY = "Category";
    public static final String TABLE_ISSUE = "Issue";
    public static final String COLUMN_FIXED_INDICATOR = "fixed_indicator";
    public static final String COLUMN_UPVOTES = "upvotes";
    public static final String COLUMN_DOWNVOTES = "downvotes";

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        ParseQuery<ParseCategory> query = ParseQuery.getQuery(TABLE_CATEGORY);
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

    public List<Issue> getResolvedIssues(double lat, double lon) {
        return getIssues(lat, lon, true);
    }

    public List<Issue> getUnresolvedIssues(double lat, double lon) {
        return getIssues(lat, lon, false);
    }

    private List<Issue> getIssues(double lat, double lon, boolean resolved) {
        List<Issue> issues = new ArrayList<>();
        ParseQuery<ParseIssue> query = ParseQuery.getQuery(TABLE_ISSUE);
        query.whereEqualTo(COLUMN_FIXED_INDICATOR, resolved);
        try {
            List<ParseIssue> parseIssues = query.find();

            // TODO: 11/10/2015 proper conversion between Parse -> DB models
            issues = new ArrayList<>(parseIssues.size());
            for (ParseIssue pi : parseIssues) {
                issues.add(new Issue(pi));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return issues;
    }

    public void insertIssue(@NonNull Issue issue) {
        ParseIssue parseIssue = ParseIssue.from(issue);

        try {
            parseIssue.save();
        } catch (ParseException ignored) {
            // TODO: 11/10/2015 error handling
        }
    }

    public void insertAccount(Account account) {

    }

    public Issue getIssue(String id) {
        try {
            ParseQuery<ParseIssue> query = ParseQuery.getQuery(TABLE_ISSUE);
            ParseIssue parseIssue = query.get(id);
            return new Issue(parseIssue);
        } catch (ParseException e) {
            e.printStackTrace();
            // TODO: 11/11/2015 notify user for problem
        }
        return null;
    }

    public Category getCategory(Long id) {
        return null;
    }

    public void upVote(Issue issue, Account upvoter) {
        try {
            ParseQuery<ParseIssue> query = ParseQuery.getQuery(TABLE_ISSUE);
            ParseIssue parseIssue = query.get(issue.getParseId());
            parseIssue.increment(COLUMN_UPVOTES);
            parseIssue.save();
        } catch (ParseException e) {
            e.printStackTrace();
            // TODO: 11/11/2015 notify user for problem
        }
    }

    public void downVote(Issue issue) {
        try {
            ParseQuery<ParseIssue> query = ParseQuery.getQuery(TABLE_ISSUE);
            ParseIssue parseIssue = query.get(issue.getParseId());
            parseIssue.increment(COLUMN_DOWNVOTES);
            parseIssue.save();
        } catch (ParseException e) {
            e.printStackTrace();
            // TODO: 11/11/2015 notify user for problem
        }
    }

    public List<Issue> getCreatedOrUpvotedIssuesFor(Account owner) {
        List<Issue> issues = new ArrayList<>();
        ParseQuery<ParseIssue> query = ParseQuery.getQuery(TABLE_ISSUE);
        query.whereEqualTo("test_created_by", "test");
        try {
            List<ParseIssue> parseIssues = query.find();

            // TODO: 11/10/2015 proper conversion between Parse -> DB models
            issues = new ArrayList<>(parseIssues.size());
            for (ParseIssue pi : parseIssues) {
                issues.add(new Issue(pi));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return issues;
    }

    public void resolveIssue(Issue issue, Account resolver) {
        try {
            ParseQuery<ParseIssue> query = ParseQuery.getQuery(TABLE_ISSUE);
            ParseIssue parseIssue = query.get(issue.getParseId());
            parseIssue.setFixedIndicator(true);
            parseIssue.save();
        } catch (ParseException e) {
            e.printStackTrace();
            // TODO: 11/11/2015 notify user for problem
        }
    }
}
