package com.codesmore.codesmore.model.pojo;

import android.content.ContentValues;

import com.codesmore.codesmore.integration.db.PulseContract.Account.Columns;

import static android.provider.BaseColumns._ID;

/**
 * Created by Darryl Staflund on 11/10/2015.
 */
public class Account {

    public static Account from(ContentValues values) {
        if (values == null){
            return new Account();
        }

        Account account = new Account();
        account.setId(values.getAsLong(_ID));
        account.setUser(values.getAsString(Columns.USERNAME));
        account.setPassword(values.getAsString(Columns.PASSWORD));
        return account;
    }

    private Long id;
    private String username;
    private String password;

    public Long getId(){
        return id;
    }

    public void setId(Long value){
        id = value;
    }

    public String getUsername(){
        return username;
    }

    public void setUser(String value){
        username = value;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String value){
        password = value;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(_ID, id);
        values.put(Columns.USERNAME, username);
        values.put(Columns.PASSWORD, password);
        return values;
    }
}
