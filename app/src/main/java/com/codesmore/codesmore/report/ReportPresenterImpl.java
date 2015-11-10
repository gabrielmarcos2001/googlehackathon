package com.codesmore.codesmore.report;

import android.graphics.Bitmap;

import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;

import java.util.List;

/**
 * Created by demouser on 11/9/15.
 */
public class ReportPresenterImpl implements ReportPresenter {

    private Issue mCurrentIssue;
    private ReportView mView;
    private DataWrapper mDataWrapper;

    public ReportPresenterImpl(ReportView reportView, DataWrapper dataWrapper) {
        mView = reportView;
        mDataWrapper = dataWrapper;

        mCurrentIssue = new Issue();
    }

    @Override
    public void requestCategoriesChooser() {
        List<Category> categories = mDataWrapper.getCategories();
        mView.showCategoriesChooser(categories);
    }

    @Override
    public void onCategoryClicked(Category category) {
        mCurrentIssue.setCategory(category);
    }

    @Override
    public void saveData(String description) {
        mCurrentIssue.setDescription(description);
        mDataWrapper.saveReport(mCurrentIssue);
        mView.onDataSaved();
    }

    @Override
    public void onImageCaptured(Bitmap image) {
        mCurrentIssue.setImage(image);
    }


}
