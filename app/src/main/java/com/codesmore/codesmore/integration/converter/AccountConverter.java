package com.codesmore.codesmore.integration.converter;

import android.content.ContentValues;
import android.database.Cursor;

import com.codesmore.codesmore.integration.db.PulseContract.Account.Columns;
import com.codesmore.codesmore.model.pojo.Account;

import static android.provider.BaseColumns._ID;
import static com.codesmore.codesmore.integration.db.PulseContract.getContentValuesFrom;

/**
 * Created by Darryl Staflund on 11/10/2015.
 */
public class AccountConverter implements Converter<Account> {

    @Override
    public ContentValues convert(Cursor cursor) {
        return getContentValuesFrom(cursor);
    }

    @Override
    public ContentValues convert(Account object) {
        if (object == null){
            return null;
        }

        ContentValues values = new ContentValues();
        values.put(_ID, object.getId());
        values.put(Columns.USERNAME, object.getUsername());
        values.put(Columns.PASSWORD, object.getPassword());
        return values;
    }

    @Override
    public Account convert(ContentValues values) {
        if (values == null){
            return new Account();
        }

        Account account = new Account();
        account.setId(values.getAsLong(_ID));
        account.setUser(values.getAsString(Columns.USERNAME));
        account.setPassword(values.getAsString(Columns.PASSWORD));
        return account;
    }
}
