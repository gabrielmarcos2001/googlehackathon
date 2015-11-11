package com.codesmore.codesmore.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.codesmore.codesmore.R;

import com.codesmore.codesmore.model.pojo.Issue;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by demouser on 11/9/15.
 */
public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.IssueHolder> {

    private static final int INITIAL_CAPACITY = 20;

    private IssueSelectedListener mListener;
    private List<Issue> mData = new ArrayList<>(INITIAL_CAPACITY);

    public IssueAdapter (IssueSelectedListener listener) {
        mListener = listener;
    }

    @Override
    public IssueHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_resolved_issue, parent, false);
        return new IssueHolder(view);
    }
    @Override
    public void onBindViewHolder(IssueHolder holder, int position) {
        if (position < 0 || position > mData.size()) {
            throw new IllegalStateException("Illegal position passed in adapter!");
        }

        Issue issue = mData.get(position);

        //TODO: holder.issueIcon.setImageDrawable();

        try {
            holder.issueDescription.setText(issue.getDescription());
        } catch (Exception e) {
            holder.issueDescription.setText("NULL");
            e.printStackTrace();
        }
        //holder.issueResolvedDate.setText("Resolved: " + issue.getFixedDate().toString());
        try {
            holder.issueTitle.setText(issue.getTitle());
        } catch (Exception e) {
            holder.issueTitle.setText("NULL");
            e.printStackTrace();
        }
        try {
            holder.downVotes.setText(issue.getDownvotes());
        } catch (Exception e) {
            holder.downVotes.setText("NULL");
            e.printStackTrace();
        }
        try {
            holder.upVotes.setText(issue.getUpvotes());
        } catch (Exception e) {
            holder.upVotes.setText("NULL");
            e.printStackTrace();
        }
        try {
            holder.productId = issue.getId();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public void setData(@NonNull List<Issue> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    class IssueHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        long productId;
        ImageView issueIcon;
        TextView issueTitle;
        TextView issueDescription;
        TextView issueResolvedDate;
        TextView upVotes;
        TextView downVotes;

        public IssueHolder(View view) {
            super(view);
            issueIcon = (ImageView) view.findViewById(R.id.item_issue_icon);
            issueDescription = (TextView) view.findViewById(R.id.item_issue_description);
            issueResolvedDate = (TextView) view.findViewById(R.id.item_issue_resolved_date);
            issueTitle = (TextView) view.findViewById(R.id.item_issue_title);
            upVotes = (TextView) view.findViewById(R.id.item_up_votes);
            downVotes = (TextView) view.findViewById(R.id.item_down_votes);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onIssueSelected(productId);
        }

    }
}
