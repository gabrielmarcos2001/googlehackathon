package com.codesmore.codesmore.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codesmore.codesmore.BaseActivity;
import com.codesmore.codesmore.R;
import com.codesmore.codesmore.ui.completed.CompletedActivity;
import com.codesmore.codesmore.ui.navdrawer.FragmentNavigationDrawer;

public class MainActivity extends BaseActivity implements FragmentNavigationDrawer.NavigationDrawerCallbacks, MainView{

    private static final String FRAGMENT_TAG = "MainFragment";

    private Toolbar mToolbar;
    private MainActivityPresenter mPresenter;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private FragmentNavigationDrawer mFragmentNavigationDrawer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mPresenter = new MainActivityPresenterImpl(this);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);

        Fragment fragmentMain = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);

        if (fragmentMain == null) {
            fragmentMain = MainFragment.newInstance();
        }

        mFragmentNavigationDrawer = (FragmentNavigationDrawer)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        if (mToolbar != null) {
            mToolbar.setTitle(""); // We are super cool and we don't show a title on the toolbar
            setSupportActionBar(mToolbar);
        }

        // Set up the drawer.
        mFragmentNavigationDrawer.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),
                mToolbar);

        // Updates the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragmentMain, FRAGMENT_TAG).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        //Toast toast = Toast.makeText(this, "Clicked: " + position, Toast.LENGTH_SHORT);
        //toast.show();
        mPresenter.onDrawerMenuItemSelected(position);

    }


    @Override
    public void openCompletedIssues() {
        //Toast toast = Toast.makeText(this, "Open Completed Issues", Toast.LENGTH_SHORT);
        //toast.show();
        Intent intent = new Intent(this, CompletedActivity.class);
        startActivity(intent);
    }

    @Override
    public void openUpvotedIssues() {
        Toast toast = Toast.makeText(this, "Open UpVoted Issues", Toast.LENGTH_SHORT);
        toast.show();
        //Intent intent = new Intent(this, )
    }

    @Override
    public void openUserAccount() {
        Toast toast = Toast.makeText(this, "Open User Account (not implemented)", Toast.LENGTH_SHORT);
        toast.show();
    }
}
