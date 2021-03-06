package com.codesmore.codesmore.ui.report;

import android.graphics.Bitmap;
import android.location.Location;

import com.codesmore.codesmore.model.pojo.Category;

public interface ReportPresenter {
    void requestCategoriesChooser();
    void onCategoryClicked(Category category);
    void onCategoryClicked(int categoryId);
    void saveData(String description, String title);
    void onImageCaptured(Bitmap image);


    void onLocationAvailable(Location location);
}
