package com.codesmore.codesmore.ui.main;

/**
 * Created by demouser on 11/10/15.
 */
public class MainActivityPresenterImpl implements MainActivityPresenter{

    private MainActivityView mView;

    public MainActivityPresenterImpl(MainActivityView mainView) {
        mView = mainView;
    }

    @Override
    public void onDrawerMenuItemSelected(int id) {
        switch(id){
            case 1:
                //Account
                mView.openUserAccount();
                break;

            case 2:
                //My Upvoted Issues
                mView.openUpvotedIssues();
                break;

            case 3:
                //Completed issues
                mView.openCompletedIssues();
                break;

            default:
                //unhandled drawer item
        }

    }
}

