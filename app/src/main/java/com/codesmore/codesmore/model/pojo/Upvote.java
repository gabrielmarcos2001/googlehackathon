package com.codesmore.codesmore.model.pojo;

/**
 * Created by Darryl Staflund on 11/10/2015.
 */
public class Upvote {
    private Long id;
    private Issue upvotedIssue;
    private Account upvoter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Issue getUpvotedIssue() {
        return upvotedIssue;
    }

    public void setUpvotedIssue(Issue upvotedIssue) {
        this.upvotedIssue = upvotedIssue;
    }

    public Account getUpvoter() {
        return upvoter;
    }

    public void setUpvoter(Account upvoter) {
        this.upvoter = upvoter;
    }
}
