package com.codesmore.codesmore.completed;

import com.codesmore.codesmore.model.pojo.Report;
import java.util.List;

/**
 * Created by demouser on 11/9/15.
 */


public interface CompletedView {

    //Loads
    void onProblemsLoaded(List<Issue> issues);

}
