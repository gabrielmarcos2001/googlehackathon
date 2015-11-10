package com.codesmore.codesmore.report;

import com.codesmore.codesmore.model.pojo.Category;

/**
 * Created by demouser on 11/9/15.
 */
public interface ReportPresenter {

    void requestCategoriesChoser();
    void onCategoryClicked(String currentCategory);
    void onCameraButtonClicked();
    void saveData(Category category, String description);
}