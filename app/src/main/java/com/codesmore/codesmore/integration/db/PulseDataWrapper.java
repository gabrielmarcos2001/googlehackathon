package com.codesmore.codesmore.integration.db;

import android.content.ContentResolver;

import com.codesmore.codesmore.integration.backend.WebService;
import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Account;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class PulseDataWrapper implements DataWrapper {

    private WebService mWebService;
    private LocalDataWrapper localDataWrapper;

    public PulseDataWrapper() {
        mWebService = new WebService();
    }

    /**
     * One-argument constructor.
     * @param contentResolver to place calls to.
     */
    public PulseDataWrapper(ContentResolver contentResolver){
        this(new LocalDataWrapper(contentResolver));
    }

    /**
     * Two-argument constructor.
     * @param localDataWrapper to place calls to local
     */
    public PulseDataWrapper(LocalDataWrapper localDataWrapper){
        this.localDataWrapper = localDataWrapper;
        this.mWebService = new WebService();
    }

    @Override
    public Observable<List<Category>> getCategories() {
        return Observable.create(new Observable.OnSubscribe<List<Category>>() {
            @Override
            public void call(Subscriber<? super List<Category>> subscriber) {
                subscriber.onNext(mWebService.getCategories());
            }
        });
    }

    @Override
    public Observable<List<Issue>> getResolvedIssues(final double lat, final double lon) {
        return Observable.create(new Observable.OnSubscribe<List<Issue>>() {
            @Override
            public void call(Subscriber<? super List<Issue>> subscriber) {
                subscriber.onNext(mWebService.getResolvedIssues(lat, lon));
            }
        });
    }

    @Override
    public Observable<List<Issue>> getUnresolvedIssues(final double lat, final double lon) {
        return Observable.create(new Observable.OnSubscribe<List<Issue>>() {
            @Override
            public void call(Subscriber<? super List<Issue>> subscriber) {
                subscriber.onNext(mWebService.getUnresolvedIssues(lat, lon));
            }
        });
    }

    @Override
    public void insertIssue(Issue issue) {
        localDataWrapper.insertIssue(issue);
    }

    @Override
    public void insertAccount(Account account) {
        localDataWrapper.insertAccount(account);
    }

    @Override
    public Issue getIssue(String parseId) {
        return localDataWrapper.getIssue(parseId);
    }

    @Override
    public Account getAccount(Long id) {
        return localDataWrapper.getAccount(id);
    }

    @Override
    public Category getCategory(Long id) {
        return localDataWrapper.getCategory(id);
    }

    @Override
    public void upVote(Issue issue, Account upvoter) {
        mWebService.upVote(issue, upvoter);
    }

    @Override
    public void downVote(Issue issue) {
        mWebService.downVote(issue);
    }

    @Override
    public Observable<List<Issue>> getCreatedOrUpvotedIssuesFor(final Account owner) {
        return Observable.create(new Observable.OnSubscribe<List<Issue>>() {
            @Override
            public void call(Subscriber<? super List<Issue>> subscriber) {
                subscriber.onNext(mWebService.getCreatedOrUpvotedIssuesFor(owner));
            }
        });
    }

    @Override
    public void resolveIssue(Issue issue, Account resolver) {
        mWebService.resolveIssue(issue, resolver);
    }

    @Override
    public void updateIssue(Issue issue) {
        localDataWrapper.updateIssue(issue);
    }

    @Override
    public void updateCategory(Category category) {
        localDataWrapper.updateCategory(category);
    }
}
