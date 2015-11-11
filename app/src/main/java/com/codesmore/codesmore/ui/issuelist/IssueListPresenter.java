package com.codesmore.codesmore.ui.issuelist;

import android.location.Location;

/**
 * Created by demouser on 11/9/15.
 */

public interface IssueListPresenter {

    void onResume();

    void onProblemSelected(String issueParseId);

    void onLocationAvailable(Location location, int issueType);

    void onNoCompleteItems();
}
