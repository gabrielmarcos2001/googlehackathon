package com.codesmore.codesmore.integration.converter;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.codesmore.codesmore.integration.db.PulseContract.IssueCategory;
import com.codesmore.codesmore.model.pojo.Category;

import static com.codesmore.codesmore.integration.db.PulseContract.getContentValuesFrom;

/**
 * Created by Darryl Staflund on 11/10/2015.
 */
public class IssueCategoryConverter implements Converter<Category> {

    @Override
    public ContentValues convert(Cursor cursor) {
        return getContentValuesFrom(cursor);
    }

    @Override
    public ContentValues convert(Category category) {
        if (category == null){
            return null;
        }

        ContentValues values = new ContentValues();
        values.put(IssueCategory._ID, category.getId());
        values.put(IssueCategory.Columns.ISSUE_CATEGORY_IMAGE, category.getImageUrl());
        values.put(IssueCategory.Columns.ISSUE_CATEGORY, category.getName());
        return null;
    }

    @Override
    public Category convert(@NonNull ContentValues values) {

        Long id = values.getAsLong(IssueCategory._ID);
        String name = values.getAsString(IssueCategory.Columns.ISSUE_CATEGORY);
        String imageUrl = values.getAsString(IssueCategory.Columns.ISSUE_CATEGORY_IMAGE);

        Category category = new Category();
        category.setId(id);
        category.setName(name);
        category.setImageUrl(imageUrl);
        return category;
    }
}
