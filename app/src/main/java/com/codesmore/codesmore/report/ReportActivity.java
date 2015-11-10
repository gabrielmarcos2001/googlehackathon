package com.codesmore.codesmore.report;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.codesmore.codesmore.BaseActivity;
import com.codesmore.codesmore.R;
import com.codesmore.codesmore.mock.MockDataWrapper;
import com.codesmore.codesmore.model.pojo.Category;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class ReportActivity extends BaseActivity implements ReportView {

    @Bind(R.id.report_category)
    Spinner mCategories;

    @Bind(R.id.report_description)
    EditText mDescription;

    private ReportPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

//        mCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Category category = (Category) parent.getItemAtPosition(position);
//                mPresenter.onCategoryClicked(category);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        mPresenter = new ReportPresenterImpl(this, new MockDataWrapper());
        mPresenter.requestCategoriesChooser();
    }

    @OnItemSelected(R.id.report_category)
    public void categorySelected(AdapterView<?> parent, View view, int position, long id) {
        Category category = (Category) parent.getItemAtPosition(position);
        mPresenter.onCategoryClicked(category);
    }

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
}
