package com.example.android.boilerplate.network;

import com.example.android.boilerplate.model.GitRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by carolinamarin on 2/3/18.
 */

public class Repository {
    private GithubInterface githubInterface;

    @Inject
    public Repository(GithubInterface githubInterface){
        this.githubInterface = githubInterface;
    }

    public Observable<List<GitRepo>> getListItems(String username){
        return githubInterface.getRepository(username);
    }
}
