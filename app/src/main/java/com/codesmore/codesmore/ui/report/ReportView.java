package com.codesmore.codesmore.ui.report;

import com.codesmore.codesmore.model.pojo.Category;

import java.util.List;

public interface ReportView {

    void showCategoriesChooser(List<Category> categories);

    void onDataSaved();
}
