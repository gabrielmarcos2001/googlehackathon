package com.codesmore.codesmore.ui.report;

import android.graphics.Bitmap;

import com.codesmore.codesmore.model.pojo.Category;

public interface ReportPresenter {
    void requestCategoriesChooser();
    void onCategoryClicked(Category category);
    void saveData(String description);
    void onImageCaptured(Bitmap image);
}
