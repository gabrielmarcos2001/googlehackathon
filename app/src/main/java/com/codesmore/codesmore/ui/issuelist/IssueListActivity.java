package com.codesmore.codesmore.ui.issuelist;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.codesmore.codesmore.BaseActivity;
import com.codesmore.codesmore.R;
import com.codesmore.codesmore.integration.backend.WebService;
import com.codesmore.codesmore.model.pojo.Issue;
import com.codesmore.codesmore.ui.GenericIssueAdapter;
import com.codesmore.codesmore.ui.IssueAdapter;
import com.codesmore.codesmore.ui.IssueSelectedListener;
import com.codesmore.codesmore.ui.completeddetails.CompletedDetailsActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;


public class IssueListActivity extends BaseActivity implements IssueListView, IssueSelectedListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener  {

    private static final String PASSEDISSUED = "PASSEDISSUE";
    private static final String ISSUETYPE = "ISSUETYPE";
    private GenericIssueAdapter mAdapter;
    private IssueListPresenter mPresenter;
    private RecyclerView mCompletedItems;
    private GoogleApiClient mGoogleApiClient;

    //0: Resolved
    //1: UpVoted
    private int issueType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);

        Intent intent = getIntent();
        issueType = intent.getIntExtra(ISSUETYPE, -1);

        initViews();

        connectGoogleApiClient();

        mPresenter = new IssueListPresenterImpl(this, new WebService());
    }

    private void initViews(){
        mCompletedItems = (RecyclerView) findViewById(R.id.completed_items_recycler);
        mAdapter = new GenericIssueAdapter(this, issueType);
        mCompletedItems.setAdapter(mAdapter);
        mCompletedItems.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Location locA = new Location("Test Loc");
        locA.setLatitude(10d);
        locA.setLongitude(10d);
        onFakeLocation(locA);
    }

    private synchronized void connectGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onProblemsLoaded(List<Issue> issues) {
        mAdapter.setData(issues);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void openCompletedIssueDetail(String issueParseId) {
        Intent intent = new Intent(this, CompletedDetailsActivity.class);

        intent.putExtra(PASSEDISSUED, issueParseId);

        startActivity(intent);
    }

    @Override
    public void onIssueSelected(String issueParseId) {
        mPresenter.onProblemSelected(issueParseId);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location location =
                LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location != null) {
            mPresenter.onLocationAvailable(location, issueType);
        }
    }

    public void onFakeLocation(Location location){
        mPresenter.onLocationAvailable(location, issueType);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void showNoItemsAvailableMessage() {
        TextView errorMessage = (TextView) findViewById(R.id.message_no_resolved_items);
        errorMessage.setVisibility(View.VISIBLE);
        //Toast toast = Toast.makeText(this, "Showing error message", Toast.LENGTH_SHORT);
        //toast.show();

    }
}
