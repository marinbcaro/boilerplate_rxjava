package com.example.android.boilerplate.viewmodels;

import android.databinding.BaseObservable;

import com.example.android.boilerplate.ICallback;
import com.example.android.boilerplate.model.GitRepo;
import com.example.android.boilerplate.network.Repository;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by carolinamarin on 2/3/18.
 */

public class GitHubViewModel extends BaseObservable {


    private Repository repository;

    private ICallback callback;
    private Subscription subscription;

    public GitHubViewModel(ICallback callback, Repository repository) {
        this.callback = callback;
        this.repository = repository;
        ;
    }

    public void getData() {
        repository.getListItems("codepath")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<GitRepo>>() {
                    @Override
                    public void accept(List<GitRepo> gitRepos) throws Exception {
                        showResults(gitRepos);
                    }

                });

    }

    public void showResults(List data) {
        callback.showResults(data);
    }

    public void showError() {
        callback.showError();
    }
}
