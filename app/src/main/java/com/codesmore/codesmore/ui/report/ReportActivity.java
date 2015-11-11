package com.codesmore.codesmore.ui.report;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.codesmore.codesmore.BaseActivityWithImageSaving;
import com.codesmore.codesmore.R;
import com.codesmore.codesmore.integration.db.PulseDataWrapper;
import com.codesmore.codesmore.model.pojo.Category;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportActivity extends BaseActivityWithImageSaving implements ReportView,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "codes";

    //@Bind(R.id.report_category)
    //Spinner mCategories;

    @Bind(R.id.report_title)
    MaterialEditText mTitle;

    @Bind(R.id.report_description)
    MaterialEditText mDescription;

    @Bind(R.id.icon_category1)
    ImageView mCategory1;

    @Bind(R.id.icon_category2)
    ImageView mCategory2;

    @Bind(R.id.icon_category3)
    ImageView mCategory3;

    @Bind(R.id.icon_category4)
    ImageView mCategory4;

    //@Bind(R.id.report_creator)
    //EditText mCreator;

    //@Bind(R.id.report_fixer)
    //EditText mFixer;

    //@Bind(R.id.report_priority)
    //Spinner mPriority;

    //@Bind(R.id.report_upvotes)
    //Button mUpvotes;

    //@Bind(R.id.report_downvotes)
    //Button mDownvotes;

    //@Bind(R.id.report_fixedInd)
    //Button mFixedInd;

    //@Bind(R.id.report_image)
    //ImageView mImage;

    private GoogleApiClient mGoogleApiClient;
    private ReportPresenter mPresenter;
    private int mCategorySelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_2);
        ButterKnife.bind(this);

        connectGoogleApiClient();

//        mPresenter = new ReportPresenterImpl(this, new PulseDataWrapper(getContentResolver()));
        mPresenter = new ReportPresenterImpl(this, new PulseDataWrapper());
        mPresenter.requestCategoriesChooser();
    }

    private synchronized void connectGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @OnClick(R.id.attach_photo)
    void onAttachPhotoClicked() {
        //startImageChooser();
    }

    @OnClick(R.id.camera)
    void onCameraClicked() {
        //startImageChooser();
    }

    /*
    @SuppressWarnings("unused")
    @OnItemSelected(R.id.report_category)
    public void categorySelected(AdapterView<?> parent, int position) {
        Category category = (Category) parent.getItemAtPosition(position);
        mPresenter.onCategoryClicked(category);
    }*/


    @OnClick(R.id.save)
    void onSaveClicked() {
        performSave();
    }

    @OnClick(R.id.save_button)
    void onSaveButtonClicked() {
        performSave();
    }

    @OnClick(R.id.cancel_button)
    void onCancelClicked() {
        onBackPressed();
    }

    @OnClick(R.id.icon_category1)
    void onSelect1(){ selectCategory(1);}

    @OnClick(R.id.icon_category2)
    void onSelect2(){ selectCategory(2);}

    @OnClick(R.id.icon_category3)
    void onSelect3(){ selectCategory(3);}

    @OnClick(R.id.icon_category4)
    void onSelect4(){ selectCategory(4);}

    void performSave() {

        boolean valid = true;
        if (mTitle.getText().toString().isEmpty()) {
            mTitle.setError(getString(R.string.error_enter_title));
            valid = false;
        }

        if (mDescription.getText().toString().isEmpty()) {
            mDescription.setError(getString(R.string.error_enter_description));
            valid = false;
        }

        if (valid) {
            mPresenter.saveData(mDescription.getText().toString(), mTitle.getText().toString());
        }
    }

    void selectCategory(int i){
        mCategorySelected = i;
        mPresenter.onCategoryClicked(i);
        resetCategoryBackgrounds();
        switch (i){
            case 1:
                mCategory1.setBackgroundResource(R.drawable.filled_circle);
                break;

            case 2:
                mCategory2.setBackgroundResource(R.drawable.filled_circle);
                break;

            case 3:
                mCategory3.setBackgroundResource(R.drawable.filled_circle);
                break;

            case 4:
                mCategory4.setBackgroundResource(R.drawable.filled_circle);
                break;

        }
    }

    void resetCategoryBackgrounds(){
        mCategory1.setBackgroundResource(R.color.trans);
        mCategory2.setBackgroundResource(R.color.trans);
        mCategory3.setBackgroundResource(R.color.trans);
        mCategory4.setBackgroundResource(R.color.trans);

    }

    @Override
    public void showCategoriesChooser(List<Category> categories) {
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        // mCategories.setAdapter(adapter);
    }

    @Override
    public void onDataSaved() {
        onBackPressed();
    }

    @Override
    public void onImageCaptured(Bitmap image) {
        //mImage.setImageBitmap(image);
        mPresenter.onImageCaptured(image);
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Location location =
                LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location != null) {
            mPresenter.onLocationAvailable(location);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        // We don't really care about it the moment.
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // Oh well, bad things happen ...
        // Won't block a user from creating a report just because there's no location yet.
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.still_anim, R.anim.hide_to_bottom);

    }

    @Override
    public void onNoCategorySelected() {
        Toast toast = Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT);
        toast.show();
    }
}
