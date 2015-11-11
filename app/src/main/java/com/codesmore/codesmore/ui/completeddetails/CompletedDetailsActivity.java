package com.codesmore.codesmore.ui.completeddetails;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.codesmore.codesmore.BaseActivity;
import com.codesmore.codesmore.R;
import com.codesmore.codesmore.integration.db.PulseDataWrapper;
import com.codesmore.codesmore.model.pojo.Issue;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CompletedDetailsActivity extends BaseActivity implements CompletedDetailsView, OnMapReadyCallback {

    private static final String LOG_TAG = CompletedDetailsActivity.class.getSimpleName();
    private CompletedDetailsPresenter mPresenter;
    private GoogleMap mMap;

    @Bind(R.id.item_issue_title)
    TextView mIssueTitle;

    @Bind(R.id.item_issue_description)
    TextView mIssueDescription;

    @Bind(R.id.item_issue_latitude)
    TextView mIssueLatitude;

    @Bind(R.id.item_issue_longitude)
    TextView mIssueLongitude;

    @Bind(R.id.item_issue_create_date)
    TextView mIssueCreateDate;

    @Bind(R.id.item_issue_fixed_date)
    TextView mIssueFixedDate;

    @Bind(R.id.item_issue_up_votes)
    TextView mIssueUpVotes;

    @Bind(R.id.item_issue_down_votes)
    TextView mIssueDownVotes;

    @Bind(R.id.item_issue_image)
    ImageView mIssuePicture;

    private String mIssueParseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_details);
        ButterKnife.bind(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mIssueParseId = getIntent().getStringExtra("PASSEDISSUE");
        if (TextUtils.isEmpty(mIssueParseId)) {
            throw new IllegalStateException("We didn't receive an issue id!");
        }

        mPresenter = new CompletedDetailsPresenterImpl(this, new PulseDataWrapper());
    }

    private void loadIssue(String issueParseId) {
        mPresenter.loadIssueByParseId(issueParseId);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // request issue
        loadIssue(mIssueParseId);
    }

    @Override
    public void onIssueLoaded(Issue resolvedIssue) {
        if (resolvedIssue != null) {

            // Add a marker in Mountain View and move the camera
            LatLng mtnView = new LatLng(resolvedIssue.getLatitude(), resolvedIssue.getLongitude());
            mMap.addMarker(new MarkerOptions().position(mtnView).title("Marker in Mountain View"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(mtnView));
            // TODO: Marker is not centered until screen is rotated
            mMap.moveCamera(CameraUpdateFactory.zoomTo(10));

            mIssueTitle.setText(resolvedIssue.getTitle());
            mIssueDescription.setText(resolvedIssue.getDescription());
            mIssueLatitude.setText(Double.toString(resolvedIssue.getLatitude()));
            mIssueLongitude.setText(Double.toString(resolvedIssue.getLongitude()));

            String createDate = "";
            if (resolvedIssue.getCreateDate() != null) {
                createDate = resolvedIssue.getCreateDate().toString();
            }
            mIssueCreateDate.setText(createDate);

            String fixedDate = "";
            if (resolvedIssue.getFixedDate() != null) {
                fixedDate = resolvedIssue.getFixedDate().toString();
            }
            mIssueFixedDate.setText(fixedDate);

            String upVotes = "";
            try {
                upVotes = Integer.toString(resolvedIssue.getUpvotes());
            } catch (Exception e) {
                Log.v(LOG_TAG, e.toString());
            }
            mIssueUpVotes.setText(upVotes);

            String downVotes = "";
            try {
                downVotes = Integer.toString(resolvedIssue.getDownvotes());
            } catch (Exception e) {
                Log.v(LOG_TAG, e.toString());
            }
            mIssueDownVotes.setText(downVotes);
            mIssuePicture.setImageBitmap(resolvedIssue.getImage());
        }
    }
}
