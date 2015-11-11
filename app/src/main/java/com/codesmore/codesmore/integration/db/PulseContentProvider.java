package com.codesmore.codesmore.integration.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.SparseArray;

/**
 * Created by Darryl Staflund on 11/9/2015.
 */
public class PulseContentProvider extends ContentProvider {

    /**
     * Provider's match codes
     */
    public static class MatchCodes {
        public static final int ISSUE_CATEGORIES = 0;
        public static final int ISSUE_CATEGORY = 1;
        public static final int ISSUE = 2;
        public static final int ISSUES = 3;
        public static final int ACCOUNT = 4;
        public static final int ACCOUNTS = 5;
        public static final int ISSUES_BY_UPVOTER = 6;
    }

    /**
     * Provider's match paths
     */
    public static class MatchPaths {
        public static final String ISSUE_CATEGORIES = PulseContract.IssueCategory.TABLE_NAME;
        public static final String ISSUE_CATEGORY = PulseContract.IssueCategory.TABLE_NAME + "/#";
        public static final String ISSUE = PulseContract.Issue.TABLE_NAME;
        public static final String ISSUES = PulseContract.Issue.TABLE_NAME;
        public static final String ACCOUNT = PulseContract.Account.TABLE_NAME + "/#";
        public static final String ACCOUNTS = PulseContract.Account.TABLE_NAME;
        public static final String ISSUES_BY_UPVOTER = PulseContract.Issue.TABLE_NAME + "/upvoter/#";
    }

    /**
     * Initializes the content provider's {@link UriMatcher} instance.
     */
    public static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH){
        {
            addURI(PulseContract.CONTENT_AUTHORITY, MatchPaths.ISSUE_CATEGORIES, MatchCodes.ISSUE_CATEGORIES);
            addURI(PulseContract.CONTENT_AUTHORITY, MatchPaths.ISSUE_CATEGORY, MatchCodes.ISSUE_CATEGORY);
            addURI(PulseContract.CONTENT_AUTHORITY, MatchPaths.ISSUE, MatchCodes.ISSUE);
            addURI(PulseContract.CONTENT_AUTHORITY, MatchPaths.ISSUES, MatchCodes.ISSUES);
            addURI(PulseContract.CONTENT_AUTHORITY, MatchPaths.ACCOUNT, MatchCodes.ACCOUNT);
            addURI(PulseContract.CONTENT_AUTHORITY, MatchPaths.ACCOUNTS, MatchCodes.ACCOUNTS);
            addURI(PulseContract.CONTENT_AUTHORITY, MatchPaths.ISSUES_BY_UPVOTER, MatchCodes.ISSUES_BY_UPVOTER);
        }
    };

    /**
     * Initializes a map associating match codes with their return types.
     */
    public static final SparseArray<String> RETURN_TYPES = new SparseArray<String>(){
        {
            put(MatchCodes.ISSUE_CATEGORIES, PulseContract.IssueCategory.CONTENT_TYPE);
            put(MatchCodes.ISSUE_CATEGORY, PulseContract.IssueCategory.CONTENT_ITEM_TYPE);
            put(MatchCodes.ISSUE, PulseContract.Issue.CONTENT_ITEM_TYPE);
            put(MatchCodes.ISSUES, PulseContract.Issue.CONTENT_TYPE);
            put(MatchCodes.ACCOUNT, PulseContract.Account.CONTENT_ITEM_TYPE);
            put(MatchCodes.ACCOUNTS, PulseContract.Account.CONTENT_TYPE);
            put(MatchCodes.ISSUES_BY_UPVOTER, PulseContract.Issue.CONTENT_TYPE);
        }
    };

    /**
     * Reference to the database helper used by this provider.
     */
    private PulseHelper helper;

    /**
     * Not implemented since we're pulling data from a web service.
     * @return false;
     */
    @Override
    public boolean onCreate(){
        helper = new PulseHelper(getContext());
        return true;
    }

    /**
     * Returns the type for a given {@link Uri} instance.
     *
     * @param uri to get return type for
     * @return type to be returned b this {@link Uri} instance
     */
    @Override
    public String getType(Uri uri){
        int matchCode = URI_MATCHER.match(uri);

        if (RETURN_TYPES.indexOfKey(matchCode) >= 0){
            return RETURN_TYPES.get(matchCode);
        }

        throw new UnsupportedOperationException("Unknown uri:  " + uri);
    }

    /**
     * Returns a {@link Cursor} of results after performing the specified query.
     *
     * @param uri to be queried
     * @param projection to be returned
     * @param selection to constrain on
     * @param selectionArgs to constrain on
     * @param sortOrder to sort on
     * @return {@link Cursor} of matching records
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        final SQLiteDatabase db = helper.getReadableDatabase();

        switch (URI_MATCHER.match(uri)){
            case MatchCodes.ISSUE_CATEGORIES:
                return db.query(
                    PulseContract.IssueCategory.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
                );

            case MatchCodes.ISSUE_CATEGORY:
                return db.query(
                    PulseContract.IssueCategory.TABLE_NAME,
                    projection,
                    PulseContract.IssueCategory.Constraints.BY_ISSUE_CATEGORY_ID_CONSTRAINT,
                    new String[] {uri.getLastPathSegment()},
                    null,
                    null,
                    null
                );

            case MatchCodes.ISSUES:
                return db.query(
                    PulseContract.Issue.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
                );

            case MatchCodes.ISSUE:
                return db.query(
                    PulseContract.Issue.TABLE_NAME,
                    projection,
                    PulseContract.Issue.Constraints.BY_ISSUE_ID_CONSTRAINT,
                    new String[] {uri.getLastPathSegment()},
                    null,
                    null,
                    null
                );

            case MatchCodes.ACCOUNTS:
                return db.query(
                    PulseContract.Account.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
                );

            case MatchCodes.ACCOUNT:
                return db.query(
                    PulseContract.Account.TABLE_NAME,
                    projection,
                    PulseContract.Account.Constraints.BY_ACCOUNT_ID_CONSTRAINT,
                    new String[] {uri.getLastPathSegment()},
                    null,
                    null,
                    null
                );

            case MatchCodes.ISSUES_BY_UPVOTER:
                //  TODO

            default:
                db.close();
                throw new UnsupportedOperationException("Unknown uri:  " + uri);
        }
    }

    /**
     *
     * @param uri
     * @param values
     * @return
     */
    @Override
    public Uri insert(Uri uri, ContentValues values){
        final SQLiteDatabase db = helper.getWritableDatabase();

        switch(URI_MATCHER.match(uri)) {
            case MatchCodes.ISSUE:
                long issueId = db.insert(PulseContract.Issue.TABLE_NAME, null, values);
                if (issueId > 0) {
                    return PulseContract.Issue.Builders.buildForIssueId(issueId);
                }

            case MatchCodes.ACCOUNT:
                long accountId = db.insert(PulseContract.Issue.TABLE_NAME, null, values);
                if (accountId > 0) {
                    return PulseContract.Account.Builders.buildForAccountId(accountId);
                }

            default:
                db.close();
                throw new UnsupportedOperationException("Unknown uri:  " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        final SQLiteDatabase db = helper.getWritableDatabase();
        int recordsUpdated;

        switch(URI_MATCHER.match(uri)) {
            case MatchCodes.ISSUE_CATEGORY:
                recordsUpdated = db.update(
                    PulseContract.IssueCategory.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs
                );
                break;

            case MatchCodes.ISSUE:
                recordsUpdated = db.update(
                    PulseContract.Issue.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs
                );
                break;

            case MatchCodes.ACCOUNT:
                recordsUpdated = db.update(
                    PulseContract.Account.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs
                );
                break;

            default:
                db.close();
                throw new UnsupportedOperationException("Unknown uri:  " + uri);
        }

        return recordsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        throw new UnsupportedOperationException("Feature not yet implemented.");
    }
}
