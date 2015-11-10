package com.codesmore.codesmore.integration.db;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darryl Staflund on 11/9/2015.
 */
public class PulseContract {
    public static final String CONTENT_AUTHORITY = "com.codesmore.codesmore";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Account table contract.
     */
    public static final class Account implements BaseColumns {
        public static final String TABLE_NAME = "account";
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + Account.TABLE_NAME;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + Account.TABLE_NAME;

        public static class Columns {
            public static final String USERNAME = "username";
            public static final String PASSWORD = "password";
        }
    }

    /**
     * Issue table contract.
     */
    public static final class Issue implements BaseColumns {
        public static final String TABLE_NAME = "issue";
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + Issue.TABLE_NAME;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + Issue.TABLE_NAME;

        public static class Columns {
            public static final String DESCRIPTION = "description";
            public static final String CREATOR_ID = "creator_id";
            public static final String FIXER_ID = "fixer_id";
            public static final String ISSUE_CATEGORY_ID = "issue_category_id";
            public static final String PRIORITY = "priority";
            public static final String UPVOTES = "updates";
            public static final String DOWNVOTES = "downvotes";
            public static final String FIXED_IND = "fixed_ind";
            public static final String CREATE_DATE = "create_date";
            public static final String FIX_DATE = "fix_date";
            public static final String LATITUDE = "latitude";
            public static final String LONGITUDE = "logitude";
            public static final String IMAGE = "image";
        }

        public static class Constraints {

            public static String BY_ISSUE_ID_CONSTRAINT =
                Issue.TABLE_NAME + "." + Issue._ID + " = ?";
        }

        public static class Builders {

            public static Uri buildForIssueId(long issueId){
                return Issue.CONTENT_URI
                    .buildUpon()
                    .appendPath(Long.toString(issueId))
                    .build();
            }
        }
    }

    /**
     * Comment table contract.
     */
    public static final class Comment implements BaseColumns {
        public static final String TABLE_NAME = "comment";
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + Comment.TABLE_NAME;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + Issue.TABLE_NAME;

        public static class Columns {
            public static final String COMMENT = "comment";
            public static final String COMMENTER_ID = "commentor_id";
            public static final String COMMENT_DATE = "comment_date";
            public static final String UPVOTES = "upvotes";
            public static final String DOWNVOTES = "downvotes";
        }
    }

    public static final class IssueCategory implements BaseColumns {
        public static final String TABLE_NAME = "issue_category";
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + IssueCategory.TABLE_NAME;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + IssueCategory.TABLE_NAME;

        public static class Columns {
            public static final String ISSUE_CATEGORY = "issue_category";
            public static final String ISSUE_CATEGORY_IMAGE = "issue_category_image";
        }

        public static class Constraints {

            public static String BY_ISSUE_CATEGORY_ID_CONSTRAINT =
                IssueCategory.TABLE_NAME + "." + IssueCategory._ID + " = ?";
        }

        public static class Builders {

            public static List<ContentValues> getContentValuesList(){
                final ContentValues cv1 = new ContentValues();
                final ContentValues cv2 = new ContentValues();
                final ContentValues cv3 = new ContentValues();
                final ContentValues cv4 = new ContentValues();

                cv1.put(IssueCategory._ID, 1);
                cv1.put(IssueCategory.Columns.ISSUE_CATEGORY, "Cleanliness");
                cv2.put(IssueCategory._ID, 2);
                cv2.put(IssueCategory.Columns.ISSUE_CATEGORY, "Improvement");
                cv3.put(IssueCategory._ID, 3);
                cv3.put(IssueCategory.Columns.ISSUE_CATEGORY, "Infrastructure");
                cv4.put(IssueCategory._ID, 4);
                cv4.put(IssueCategory.Columns.ISSUE_CATEGORY, "Safety");

                return new ArrayList<ContentValues>(){
                    {
                        add(cv1);
                        add(cv2);
                        add(cv3);
                        add(cv4);
                    }
                };
            }
        }
    }
}
