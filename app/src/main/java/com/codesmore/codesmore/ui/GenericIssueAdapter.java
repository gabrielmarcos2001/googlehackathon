package com.codesmore.codesmore.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codesmore.codesmore.R;
import com.codesmore.codesmore.model.pojo.Issue;
import com.codesmore.codesmore.utils.ImageUtils;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by demouser on 11/9/15.
 */
public class GenericIssueAdapter extends RecyclerView.Adapter<GenericIssueAdapter.IssueHolder> {

    private static final int INITIAL_CAPACITY = 20;

    private IssueSelectedListener mListener;
    private List<Issue> mData = new ArrayList<>(INITIAL_CAPACITY);
    private int mIssueType;

    public GenericIssueAdapter(IssueSelectedListener listener, int issueType) {
        mListener = listener;
        mIssueType = issueType;
    }

    @Override
    public IssueHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mIssueType == 0){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_resolved_issue, parent, false);
            return new IssueHolder(view);
        }else if(mIssueType ==1){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_resolved_issue, parent, false);
            return new IssueHolder(view);
        }
        return null;
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
            holder.downVotes.setText(issue.getDownvotes()+"");
        } catch (Exception e) {
            holder.downVotes.setText("NULL");
            e.printStackTrace();
        }
        try {
            holder.upVotes.setText(issue.getUpvotes()+"");
        } catch (Exception e) {
            holder.upVotes.setText("NULL");
            e.printStackTrace();
        }
        try {
            holder.issueParseId = issue.getParseId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            holder.downVoteCount = issue.getDownvotes();
            holder.upVoteCount = issue.getUpvotes();
            holder.issue = issue;
        }catch (Exception e){

        }
        try {
            holder.issueIcon.setImageResource(ImageUtils.getDrawableId(issue.getCategory()));
        } catch (Exception e) {
            // holder.issueDescription.setText("NULL");
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

        Issue issue;
        String issueParseId;
        Boolean voteUsed = false;
        int upVoteCount;
        int downVoteCount;
        ImageView issueIcon;
        TextView issueTitle;
        TextView issueDescription;
        TextView issueResolvedDate;
        TextView upVotes;
        TextView downVotes;

        ImageView downVoteIcon;
        ImageView upVoteIcon;

        public IssueHolder(View view) {
            super(view);
            issueIcon = (ImageView) view.findViewById(R.id.item_issue_icon);
            issueDescription = (TextView) view.findViewById(R.id.item_issue_description);

            issueTitle = (TextView) view.findViewById(R.id.item_issue_title);
            upVotes = (TextView) view.findViewById(R.id.item_up_votes);
            downVotes = (TextView) view.findViewById(R.id.item_down_votes);
            view.setOnClickListener(this);

            if (mIssueType == 0){

                //issueResolvedDate = (TextView) view.findViewById(R.id.item_issue_resolved_date);

            }else if (mIssueType == 1){
                //upVoteIcon = (ImageView) view.findViewById(R.id.item_icon_up_votes);
                //upVoteIcon.setOnClickListener(this);
                //downVoteIcon = (ImageView) view.findViewById(R.id.item_icon_down_votes);
                //downVoteIcon.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {

            /*
            int finalVal;
            switch (v.getId()){
                case R.id.item_icon_down_votes:
                    if (!voteUsed) {
                        finalVal = downVoteCount + 1;
                        downVotes.setText(finalVal + "");
                        mListener.onIssueDownVoted(issue);
                        voteUsed = true;
                       break;
                    }

                case R.id.item_icon_up_votes:
                    if (!voteUsed){
                        finalVal = downVoteCount + 1;
                        upVotes.setText(finalVal + "");
                        mListener.onIssueUpVoted(issue);
                        voteUsed = true;
                        break;
                    }
                default:
                    mListener.onIssueSelected(issueParseId);
                    break;
            }

            */
            mListener.onIssueSelected(issueParseId);
            Log.v("RVA", "Clicked:" + v.toString());

        }


    }
}
