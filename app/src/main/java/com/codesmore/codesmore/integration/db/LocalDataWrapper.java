package com.codesmore.codesmore.integration.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

import com.codesmore.codesmore.integration.converter.Converter;
import com.codesmore.codesmore.integration.converter.AccountConverter;
import com.codesmore.codesmore.integration.converter.CategoryConverter;
import com.codesmore.codesmore.integration.converter.Converter;
import com.codesmore.codesmore.integration.converter.IssueConverter;
import com.codesmore.codesmore.integration.converter.UpvoteConverter;
import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Account;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;
import com.codesmore.codesmore.model.pojo.Upvote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Darryl Staflund on 11/10/2015.
 */
public class LocalDataWrapper implements DataWrapper {
    private ContentResolver contentResolver;
    private Converter<Account> accountConverter;
    private Converter<Category> categoryConverter;
    private Converter<Issue> issueConverter;
    private Converter<Upvote> upvoteConverter;

    /**
     * Default constructor.
     * @param contentResolver to place calls to.
     */
    public LocalDataWrapper(ContentResolver contentResolver){
        this(
            contentResolver,
            new AccountConverter(),
            new CategoryConverter(),
            new IssueConverter(),
            new UpvoteConverter()
        );
    }

    public LocalDataWrapper(
        ContentResolver contentResolver,
        Converter<Account> accountConverter,
        Converter<Category> categoryConverter,
        Converter<Issue> issueConverter,
        Converter<Upvote> upvoteConverter
    ){
        super();
        this.contentResolver = contentResolver;
        this.accountConverter = accountConverter;
        this.categoryConverter = categoryConverter;
        this.issueConverter = issueConverter;
        this.upvoteConverter = upvoteConverter;

        // I really don't like the following two lines.  See how we can change this.
        ((IssueConverter) issueConverter).setDataWrapper(this);
        ((UpvoteConverter) upvoteConverter).setDataWrapper(this);
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
                ContentValues values = categoryConverter.convert(cursor);
                Category category = categoryConverter.convert(values);
                categories.add(category);
            }
        }
        return categories;
    }

    @Override
    public List<Issue> getResolvedIssues(double lat, double lon) {
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
                ContentValues values = issueConverter.convert(cursor);
                Issue issue = issueConverter.convert(values);
                issues.add(issue);
            }
        }
        return issues;
    }

    @Override
    public List<Issue> getUnresolvedIssues(double lat, double lon) {
        Cursor cursor = contentResolver.query(
            PulseContract.Issue.CONTENT_URI,
            null,
            PulseContract.Issue.Constraints.BY_UNRESOLVED_STATUS,
            new String[] { Integer.toString(0) },
            PulseContract.Issue.TABLE_NAME + "." + PulseContract.Issue.Columns.UPVOTES + " DESC"
        );

        List<Issue> issues = new ArrayList<>();
        if (cursor != null){
            while(cursor.moveToNext()){
                ContentValues values = issueConverter.convert(cursor);
                Issue issue = issueConverter.convert(values);
                issues.add(issue);
            }
        }

        return issues;
    }

    @Override
    public void insertIssue(Issue issue) {
        if (issue == null){
            throw new IllegalArgumentException("Issue is required.");
        }

        ContentValues values = issueConverter.convert(issue);
        contentResolver.insert(PulseContract.Issue.CONTENT_URI, values);
    }

    @Override
    public void insertAccount(Account account) {
        if (account == null){
            throw new IllegalArgumentException("Account is required.");
        }

        ContentValues values = accountConverter.convert(account);
        contentResolver.insert(PulseContract.Account.CONTENT_URI, values);
    }

    @Override
    public Issue getIssue(Long id) {
        if (id == null){
            return null;
        }

        Cursor cursor = contentResolver.query(
            PulseContract.Issue.Builders.buildForIssueId(id),
            null,
            null,
            null,
            null
        );

        if (cursor == null){
            return null;
        }

        ContentValues values = issueConverter.convert(cursor);
        Issue issue = issueConverter.convert(values);
        return issue;
    }

    @Override
    public Account getAccount(Long id) {
        if (id == null){
            return null;
        }

        Cursor cursor = contentResolver.query(
            PulseContract.Account.Builders.buildForAccountId(id),
            null,
            null,
            null,
            null
        );

        if (cursor == null){
            return null;
        }

        ContentValues values = accountConverter.convert(cursor);
        Account account = accountConverter.convert(values);
        return account;
    }

    @Override
    public Category getCategory(Long id) {
        if (id == null){
            return null;
        }

        Cursor cursor = contentResolver.query(
            PulseContract.IssueCategory.Builders.buildForCategoryId(id),
            null,
            null,
            null,
            null
        );

        if (cursor == null){
            return null;
        }

        ContentValues values = categoryConverter.convert(cursor);
        Category category = categoryConverter.convert(values);
        return category;
    }

    @Override
    public void upvote(Issue issue, Account upvoter) {
        if (issue == null || upvoter == null){
            return;
        }

        /**
         * First, we need to increment the upvote count and save it to database.
         */
        issue.setUpvotes(issue.getUpvotes() + 1);
        ContentValues issueValues = issueConverter.convert(issue);
        contentResolver.update(
            PulseContract.Issue.Builders.buildForIssueId(issue.getId()),
            issueValues,
            null,
            null
        );

        /**
         * Next, we need to insert a new upvote record into the database.
         */
        Upvote upvote = new Upvote();
        upvote.setUpvoter(upvoter);
        upvote.setUpvotedIssue(issue);

        ContentValues upvoteValues = upvoteConverter.convert(upvote);
        contentResolver.insert(PulseContract.Upvote.CONTENT_URI, upvoteValues);
    }

    @Override
    public void downvote(Issue issue) {
        if (issue == null){
            return;
        }

        issue.setDownvotes(issue.getDownvotes() + 1);
        ContentValues issueValues = issueConverter.convert(issue);

        contentResolver.update(
            PulseContract.Issue.Builders.buildForIssueId(issue.getId()),
            issueValues,
            null,
            null
        );
    }

    @Override
    public void resolveIssue(Issue issue, Account resolver) {
        if (issue == null || resolver == null) {
            return;
        }

        issue.setFixer(resolver);
        ContentValues values = issueConverter.convert(issue);

        contentResolver.update(
            PulseContract.Issue.Builders.buildForIssueId(issue.getId()),
            values,
            null,
            null
        );
    }

    @Override
    public List<Issue> getCreatedOrUpvotedIssuesFor(Account ownerOrUpvoter) {
        if (ownerOrUpvoter == null){
            return Collections.emptyList();
        }

        Cursor cursor = contentResolver.query(
            PulseContract.Issue.Builders.buildForUpvotedIssues(ownerOrUpvoter.getId()),
            null,
            null,
            null,
            null
        );

        List<Issue> issues = new ArrayList<>();
        if (cursor != null){
            while(cursor.moveToNext()){
                ContentValues values = issueConverter.convert(cursor);
                Issue issue = issueConverter.convert(values);
                issues.add(issue);
            }
        }

        Set<Issue> uniqueIssues = new HashSet<>(issues);
        return new ArrayList<> (uniqueIssues);
    }
}
