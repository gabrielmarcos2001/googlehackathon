package com.codesmore.codesmore.report;

import android.graphics.Bitmap;

import com.codesmore.codesmore.model.pojo.Category;

/**
 * Created by demouser on 11/9/15.
 */
public interface ReportPresenter {
    void requestCategoriesChooser();
    void onCategoryClicked(Category category);
    void saveData(String description);

    void onImageCaptured(Bitmap image);
}
