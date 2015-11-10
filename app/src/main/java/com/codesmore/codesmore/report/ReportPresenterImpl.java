package com.codesmore.codesmore.report;

import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;

import java.util.List;

/**
 * Created by demouser on 11/9/15.
 */
public class ReportPresenterImpl implements ReportPresenter {

    private ReportView mView;
    private DataWrapper mDataWrapper;

    public ReportPresenterImpl(ReportView reportView) {
        mView = reportView;

    }

    @Override
    public void requestCategoriesChoser() {
        List<Category> categories = mDataWrapper.getCategories();
        mView.showCategoriesChoser(categories);
    }

    @Override
    public void onCategoryClicked(String currentCategory) {

    }

    @Override
    public void onCameraButtonClicked() {

    }

    @Override
    public void saveData(Category category, String description) {
        Issue issue = new Issue(category, description);
        mDataWrapper.saveReport(issue);
    }


}
