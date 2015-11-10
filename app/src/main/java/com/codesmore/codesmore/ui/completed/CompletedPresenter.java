package com.codesmore.codesmore.ui.completed;

import android.location.Location;

/**
 * Created by demouser on 11/9/15.
 */

public interface CompletedPresenter {

    void onResume();

    void onProblemSelected(long issueId);

    void onLocationAvailable(Location location);

    void onNoCompleteItems();
}
