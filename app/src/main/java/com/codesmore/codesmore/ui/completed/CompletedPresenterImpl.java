package com.codesmore.codesmore.ui.completed;

import android.location.Location;
import android.util.Log;

import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Issue;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by demouser on 11/9/15.
 */
public class CompletedPresenterImpl implements CompletedPresenter{

    private CompletedView mView;
    private DataWrapper mDataWrapper;

    public CompletedPresenterImpl(CompletedView mainView, DataWrapper dataWrapper) {
        mDataWrapper = dataWrapper;
        mView = mainView;

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onProblemSelected(String issueParseId) {
        mView.openCompletedIssueDetail(issueParseId);
    }

    @Override
    public void onLocationAvailable(Location location) {
        //Triggers data load when a location is available
        double lat = location.getLatitude();
        double lon = location.getLongitude();

        mDataWrapper.getResolvedIssues(lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Issue>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Issue> issues) {
                        onCompletedIssuesLoaded(issues);
                    }
                });
    }

    public void onCompletedIssuesLoaded(List<Issue> issues) {
        //Sets the main view when problems become loaded
        if (issues.size() == 0){
            Log.v("DEBUG", "No Data on System");
            mView.onProblemsLoaded(issues);
            onNoCompleteItems();
        }else{
            mView.onProblemsLoaded(issues);
        }

    }

    @Override
    public void onNoCompleteItems() {
        mView.showNoItemsAvailableMessage();
    }
}
