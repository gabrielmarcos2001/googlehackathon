package com.codesmore.codesmore.ui.completeddetails;

import com.codesmore.codesmore.model.DataWrapper;

/**
 * Created by demouser on 11/9/15.
 */
public class CompletedDetailsPresenterImpl implements CompletedDetailsPresenter {

    private CompletedDetailsView mView;
    private DataWrapper mDataWrapper;

    public CompletedDetailsPresenterImpl(CompletedDetailsView mainView, DataWrapper dataWrapper) {
        mDataWrapper = dataWrapper;
        mView = mainView;

    }
}
