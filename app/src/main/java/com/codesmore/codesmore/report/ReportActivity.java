package com.codesmore.codesmore.report;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import com.codesmore.codesmore.BaseActivity;
import com.codesmore.codesmore.R;
import com.codesmore.codesmore.model.pojo.Category;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by demouser on 11/9/15.
 */
public class ReportActivity extends BaseActivity implements ReportView {

    @Bind(R.id.report_category)
    Spinner mCategories;


    @Bind(R.id.report_description)
    TextView mDescription;

    private ReportPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        mPresenter = new ReportPresenterImpl(this);
        mPresenter.requestCategoriesChoser();

    }

    @OnItemClick(R.id.report_category)
    public void onCategorySelected() {

    }

    @OnClick(R.id.report_save_btn)
    public void save() {
        throw new RuntimeException("Implement me!!!");
    }


    @OnClick(R.id.report_category)
    public void categoryClicked() {
        mPresenter.requestCategoriesChoser();
    }

    @Override
    public void showCategoriesChoser(List<Category> categories) {

    }
}
