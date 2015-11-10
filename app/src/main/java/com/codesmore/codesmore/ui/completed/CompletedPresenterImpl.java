package com.codesmore.codesmore.ui.completed;

import android.location.Location;

import com.codesmore.codesmore.integration.db.PulseDataWrapper;
import com.codesmore.codesmore.model.DataFetchedListener;
import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Issue;
import com.codesmore.codesmore.ui.completeddetails.CompletedDetailsPresenter;

import java.util.List;

/**
 * Created by demouser on 11/9/15.
 */
public class CompletedPresenterImpl implements CompletedPresenter, DataFetchedListener{

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
    public void onProblemSelected(long issueId) {
        mView.openCompletedIssueDetail(issueId);
    }

    @Override
    public void onLocationAvailable(Location location) {
        //Triggers data load when a location is available
        mDataWrapper.getResolvedIssues(location.getLatitude(), location.getLongitude());
    }

    @Override
    public void onCompletedIssuesLoaded(List<Issue> issues) {
        //Sets the main view when problems become loaded
        mView.onProblemsLoaded(issues);
    }
}
