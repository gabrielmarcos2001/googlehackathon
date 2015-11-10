package com.codesmore.codesmore.model;

import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Report;

import java.util.List;

/**
 * Created by demouser on 11/9/15.
 */
public interface DataWrapper {

    List<Category> getCategories();
    void saveReport(Report report);
}
