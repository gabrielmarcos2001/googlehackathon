package com.codesmore.codesmore.ui.main;

import com.codesmore.codesmore.model.pojo.Issue;

/**
 * Created by gabrielmarcos on 11/10/15.
 */
public interface MainPresenter {

    void onIssueUpVoted(Issue issue);
    void onIssueDownVoted(Issue issue);
    void setView(MainView view);
}
