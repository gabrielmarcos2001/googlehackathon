package com.codesmore.codesmore.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codesmore.codesmore.R;

/**
 * Created by gabrielmarcos on 11/9/15.
 */
public class MainFragment extends Fragment {

    private ViewRipple mRipple;

    /**
     * Returns a new instance of the Fragment
     *
     * @return fragment instance
     */
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mRipple = (ViewRipple)rootView.findViewById(R.id.ripple_view);
        mRipple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRipple.showRipple();
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
