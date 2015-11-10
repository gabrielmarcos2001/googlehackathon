package com.codesmore.codesmore.ui.report;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.codesmore.codesmore.BaseActivityWithImageSaving;
import com.codesmore.codesmore.R;
import com.codesmore.codesmore.integration.db.PulseDataWrapper;
import com.codesmore.codesmore.model.pojo.Category;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class ReportActivity extends BaseActivityWithImageSaving implements ReportView {

    @Bind(R.id.report_category)
    Spinner mCategories;

    @Bind(R.id.report_description)
    EditText mDescription;

    @Bind(R.id.report_image)
    ImageView mImage;

    private ReportPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        mPresenter = new ReportPresenterImpl(this, new PulseDataWrapper(getContentResolver()));
        mPresenter.requestCategoriesChooser();
    }

    @SuppressWarnings("unused")
    @OnItemSelected(R.id.report_category)
    public void categorySelected(AdapterView<?> parent, int position) {
        Category category = (Category) parent.getItemAtPosition(position);
        mPresenter.onCategoryClicked(category);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.report_image)
    public void onImageClicked() {
        startImageChooser();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.report_save_btn)
    public void save() {
        mPresenter.saveData(mDescription.getText().toString());
    }

    @Override
    public void showCategoriesChooser(List<Category> categories) {
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        mCategories.setAdapter(adapter);
    }

    @Override
    public void onDataSaved() {
        finish();
    }

    @Override
    public void onImageCaptured(Bitmap image) {
        mImage.setImageBitmap(image);
        mPresenter.onImageCaptured(image);
    }
}
