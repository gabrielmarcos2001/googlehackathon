package com.codesmore.codesmore.integration.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.codesmore.codesmore.integration.db.PulseContract.Account;
import com.codesmore.codesmore.integration.db.PulseContract.Comment;
import com.codesmore.codesmore.integration.db.PulseContract.Issue;
import com.codesmore.codesmore.integration.db.PulseContract.IssueCategory;
import com.codesmore.codesmore.integration.db.PulseContract.Upvote;

/**
 * Created by Darryl Staflund on 11/9/2015.
 */
public class PulseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "pulse.db";

    /**
     * One-argument constructor.
     * @param context
     */
    public PulseHelper(Context context){
        this(context, null);
    }

    /**
     * Two-argument constructor.
     * @param context
     * @param factory
     */
    public PulseHelper(Context context, SQLiteDatabase.CursorFactory factory){
        this(context, factory, null);
    }

    /**
     * Three-argument constructor.
     * @param context
     * @param factory
     * @param errHandler
     */
    public PulseHelper(Context context, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errHandler){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION, errHandler);
    }

    /**
     * Creates the database.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d("Helper", "onCreate called.");
        String issueCategorySql = new StringBuilder()
            .append("CREATE TABLE " + IssueCategory.TABLE_NAME)
            .append("(")
            .append("    " + IssueCategory._ID + " INTEGER PRIMARY KEY")
            .append("  , " + IssueCategory.Columns.ISSUE_CATEGORY + " TEXT NOT NULL")
            .append("  , " + IssueCategory.Columns.ISSUE_CATEGORY_IMAGE + " BLOB NULL")
            .append("  , " + IssueCategory.Columns.PARSE_ID + " TEXT NOT NULL")
            .append("  , UNIQUE (" + IssueCategory.Columns.ISSUE_CATEGORY + ")")
            .append(");")
            .toString();
        db.execSQL(issueCategorySql);

        //  Since this is an enum, we will just insert the values.
        for(ContentValues cv : IssueCategory.Builders.getContentValuesList()){
            long id = db.insert(IssueCategory.TABLE_NAME, null, cv);
            if (id == -1){
                throw new RuntimeException("Problem encountered while insert value.");
            }
        }

        String accountSql = new StringBuilder()
            .append("CREATE TABLE " + Account.TABLE_NAME)
            .append("(")
            .append("    " + Account._ID + " INTEGER PRIMARY KEY")
            .append("  , " + Account.Columns.USERNAME + " TEXT NOT NULL")
            .append("  , " + Account.Columns.PASSWORD + " TEXT NOT NULL")
            .append("  , " + Account.Columns.PARSE_ID + " TEXT NOT NULL")
            .append("  , UNIQUE (" + Account.TABLE_NAME + ")")
            .append("  , UNIQUE (" + Account.Columns.PARSE_ID + ")")
            .append(");")
            .toString();
        db.execSQL(accountSql);

        String issueSql = new StringBuilder()
            .append("CREATE TABLE " + Issue.TABLE_NAME)
            .append("(")
            .append("    " + Issue._ID + " INTEGER PRIMARY KEY")
            .append("  , " + Issue.Columns.DESCRIPTION + " TEXT NULL")
            .append("  , " + Issue.Columns.CREATOR_ID + " INTEGER NOT NULL")
            .append("  , " + Issue.Columns.FIXER_ID + " INTEGER NULL")
            .append("  , " + Issue.Columns.ISSUE_CATEGORY_ID + " INTEGER NOT NULL")
            .append("  , " + Issue.Columns.PRIORITY + " INTEGER NOT NULL")
            .append("  , " + Issue.Columns.UPVOTES + " INTEGER NOT NULL DEFAULT 0")
            .append("  , " + Issue.Columns.DOWNVOTES + " INTEGER NOT NULL DEFAULT 0")
            .append("  , " + Issue.Columns.FIXED_IND + " INTEGER NOT NULL DEFAULT 0")
            .append("  , " + Issue.Columns.CREATE_DATE + " LONG NOT NULL")
            .append("  , " + Issue.Columns.FIX_DATE + " LONG NULL")
            .append("  , " + Issue.Columns.LATITUDE + " DOUBLE NOT NULL")
            .append("  , " + Issue.Columns.LONGITUDE + " DOUBLE NOT NULL")
            .append("  , " + Issue.Columns.IMAGE + " BLOB NOT NULL")
            .append("  , FOREIGN KEY (" + Issue.Columns.CREATOR_ID + ")")
            .append("     REFERENCES " + Account.TABLE_NAME + " (" + Account._ID + ")")
            .append("  , FOREIGN KEY (" + Issue.Columns.FIXER_ID + ")")
            .append("     REFERENCES " + Account.TABLE_NAME + " (" + Account._ID + ")")
            .append("  , FOREIGN KEY (" + Issue.Columns.ISSUE_CATEGORY_ID + ")")
            .append("     REFERENCES " + IssueCategory.TABLE_NAME + " (" + IssueCategory._ID + ")")
            .append(");")
            .toString();
        db.execSQL(issueSql);

        // Comment
        String commentSql = new StringBuilder()
            .append("CREATE TABLE " + Comment.TABLE_NAME)
            .append("(")
            .append("    " + Comment._ID + " INTEGER PRIMARY KEY")
            .append("  , " + Comment.Columns.COMMENT + " TEXT NOT NULL")
            .append("  , " + Comment.Columns.COMMENT_DATE + " LONG NOT NULL")
            .append("  , " + Comment.Columns.COMMENTER_ID + " INTEGER NOT NULL")
            .append("  , " + Comment.Columns.UPVOTES + " INTEGER NOT NULL DEFAULT 0")
            .append("  , " + Comment.Columns.DOWNVOTES + " INTEGER NOT NULL DEFAULT 0")
            .append("  , FOREIGN KEY (" + Comment.Columns.COMMENTER_ID + ")")
            .append("     REFERENCES " + Account.TABLE_NAME + " (" + Account._ID + ")")
            .append(");")
            .toString();
        db.execSQL(commentSql);

        // Upvoter
        String upvoterSql = new StringBuilder()
            .append("CREATE TABLE " + Upvote.TABLE_NAME)
            .append("(")
            .append("    " + Upvote._ID + " INTEGER PRIMARY KEY")
            .append("  , " + Upvote.Columns.UPVOTED_ISSUE_ID + " INTEGER NOT NULL")
            .append("  , " + Upvote.Columns.UPVOTER_ID + " INTEGER NOT NULL")
            .append("  , FOREIGN KEY (" + Upvote.Columns.UPVOTED_ISSUE_ID + ")")
            .append("     REFERENCES " + Issue.TABLE_NAME + " (" + Issue._ID + ")")
            .append("  , FOREIGN KEY (" + Upvote.Columns.UPVOTER_ID + ")")
            .append("     REFERENCES " + Account.TABLE_NAME + " (" + Account._ID + ")")
            .append(");")
            .toString();
        db.execSQL(upvoterSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.d("Helper", "onUpgrade called.");
        if (oldVersion < newVersion){
            db.execSQL("DROP TABLE " + Upvote.TABLE_NAME);
            db.execSQL("DROP TABLE " + Comment.TABLE_NAME);
            db.execSQL("DROP TABLE " + Issue.TABLE_NAME);
            db.execSQL("DROP TABLE " + Account.TABLE_NAME);
            db.execSQL("DROP TABLE " + IssueCategory.TABLE_NAME);
        }
    }
}
