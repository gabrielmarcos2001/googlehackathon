package com.codesmore.codesmore.report;

import com.codesmore.codesmore.model.pojo.Category;

import java.util.List;

/**
 * Created by demouser on 11/9/15.
 */
public interface ReportView {

    void showCategoriesChoser(List<Category> categories);
}
