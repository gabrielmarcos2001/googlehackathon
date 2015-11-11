package com.codesmore.codesmore.ui.report;

import android.graphics.Bitmap;
import android.location.Location;
import android.support.annotation.NonNull;

import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        mDataWrapper.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Category>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Category> categories) {
                        mView.showCategoriesChooser(categories);
                    }
                });
    }

    @Override
    public void onCategoryClicked(Category category) {
        mCurrentIssue.setCategory(category);
    }

    @Override
    public void saveData(String description, String title) {
        if (null == mCurrentIssue.getCategory()){
            mView.onNoCategorySelected();
            //TODO: Notify user to select a category
        }else{
            mCurrentIssue.setDescription(description);
            mCurrentIssue.setTitle(title);
            mDataWrapper.insertIssue(mCurrentIssue);
            mView.onDataSaved();
        }

    }

    @Override
    public void onImageCaptured(Bitmap image) {
        mCurrentIssue.setImage(image);
    }

    @Override
    public void onLocationAvailable(@NonNull Location location) {
        mCurrentIssue.setLocation(location);
    }

    @Override
    public void onCategoryClicked(int categoryId) {
       mCurrentIssue.setCategory(new Category(categoryId));
    }

}
