package com.codesmore.codesmore.ui.report;

import android.graphics.Bitmap;
import android.location.Location;
import android.support.annotation.NonNull;

import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;

import java.util.List;

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
        mDataWrapper.insertIssue(mCurrentIssue);
        mView.onDataSaved();
    }

    @Override
    public void onImageCaptured(Bitmap image) {
        mCurrentIssue.setImage(image);
    }

    @Override
    public void onLocationAvailable(@NonNull Location location) {
        mCurrentIssue.setLocation(location);
    }


}
