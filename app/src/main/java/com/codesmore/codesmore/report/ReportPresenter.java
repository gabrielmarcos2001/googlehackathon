package com.codesmore.codesmore.report;

import com.codesmore.codesmore.model.pojo.Category;

/**
 * Created by demouser on 11/9/15.
 */
public interface ReportPresenter {
    void requestCategoriesChooser();
    void onCategoryClicked(Category category);
    void onCameraButtonClicked();
    void saveData(String description);
}
