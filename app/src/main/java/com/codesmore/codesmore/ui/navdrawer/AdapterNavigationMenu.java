package com.codesmore.codesmore.ui.navdrawer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.codesmore.codesmore.R;

import java.util.ArrayList;

/**
 * Created by gabrielmarcos on 12/2/14.
 */
public class AdapterNavigationMenu extends BaseAdapter {

    private Context mContext;
    private ArrayList<MenuItem> mItemsList;

    /**
     * Default constructor
     *
     * @param context
     */
    public AdapterNavigationMenu(Context context) {

        mContext = context;
        mItemsList = new ArrayList<>();

        MenuItem profileItem = new MenuItem(1, R.drawable.icon_account);
        profileItem.setmText(context.getString(R.string.menu_my_account));

        MenuItem upVotedIssues = new MenuItem(2,R.drawable.icon_address);
        upVotedIssues.setmText(context.getString(R.string.menu_upvoted_issues));

        MenuItem issuesItem = new MenuItem(3,R.drawable.icon_completed);
        issuesItem.setmText(context.getString(R.string.menu_completed_issues));

        MenuItem signOutItem = new MenuItem(4,R.drawable.icon_address);
        signOutItem.setmText(context.getString(R.string.menu_sign_out));

        mItemsList.add(profileItem);
        mItemsList.add(upVotedIssues);
        mItemsList.add(issuesItem);
        mItemsList.add(signOutItem);

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        ListNavMenuItem v = (ListNavMenuItem) convertView;

        if (v == null) {

            holder = new ViewHolder();

            v = new ListNavMenuItem(mContext);

            holder.mListItem = v;
            v.setTag(holder);


        } else {
            holder = (ViewHolder) v.getTag();
        }


        if (holder != null) {

            MenuItem item = mItemsList.get(position);

            if (item != null) {
                holder.mListItem.setItem(item);
            }

        }

        return v;

    }

    /**
     * ViewHolder pattern
     */
    private class ViewHolder {
        ListNavMenuItem mListItem;
    }
}
