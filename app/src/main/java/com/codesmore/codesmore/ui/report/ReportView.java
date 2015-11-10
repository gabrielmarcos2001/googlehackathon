package com.codesmore.codesmore.ui.report;

import com.codesmore.codesmore.model.pojo.Category;

import java.util.List;

/**
 * Created by demouser on 11/9/15.
 */
public interface ReportView {

    void showCategoriesChooser(List<Category> categories);

    void onDataSaved();
}
