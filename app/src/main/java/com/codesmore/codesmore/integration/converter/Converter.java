package com.codesmore.codesmore.integration.converter;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Darryl Staflund on 11/10/2015.
 */
public interface Converter<T> {
    ContentValues convert(Cursor cursor);
    ContentValues convert(T object);
    T convert(ContentValues values);
}
