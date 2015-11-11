package com.codesmore.codesmore.ui.issuelist;

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
public class IssueListPresenterImpl implements IssueListPresenter {

    private IssueListView mView;
    private DataWrapper mDataWrapper;

    public IssueListPresenterImpl(IssueListView mainView, DataWrapper dataWrapper) {
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
    public void onLocationAvailable(Location location, int issueType) {
        //Triggers data load when a location is available
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        List<Issue> issues;
        if (issueType == 0) {
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
        } else if (issueType == 1) {
            //TODO: fix account user
            try{
                issues = mDataWrapper.getCreatedOrUpvotedIssuesFor(null);
                this.onCompletedIssuesLoaded(issues);
            }catch (Exception e){

            }

        }
    }

    public void onCompletedIssuesLoaded(List<Issue> issues) {
        //Sets the main view when problems become loaded
        if (null == issues || issues.size() == 0){
            Log.v("DEBUG", "No Data on System");
            //mView.onProblemsLoaded(issues);
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
