package com.codesmore.codesmore.ui.completed;

import android.content.Intent;
import android.os.Bundle;

import com.codesmore.codesmore.BaseActivity;
import com.codesmore.codesmore.R;
import com.codesmore.codesmore.model.pojo.Issue;
import com.codesmore.codesmore.ui.IssueAdapter;
import com.codesmore.codesmore.ui.completeddetails.CompletedDetailsActivity;

import java.util.List;

import butterknife.ButterKnife;


public class CompletedActivity extends BaseActivity implements CompletedView {

    private static final String PASSEDISSUED = "PASSEDISSUE";

    private IssueAdapter mAdapter;
    private CompletedPresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);
        ButterKnife.bind(this);

        //initViews();

        mPresenter = new CompletedPresenterImpl(this);

    }

    @Override
    public void onProblemsLoaded(List<Issue> issues) {
        mAdapter.setData(issues);
    }

    @Override
    public void openCompletedIssueDetail(Issue issue) {
        Intent intent = new Intent(this, CompletedDetailsActivity.class);
        intent.putExtra(PASSEDISSUED, issue.getId());
        startActivity(intent);

    }
}
