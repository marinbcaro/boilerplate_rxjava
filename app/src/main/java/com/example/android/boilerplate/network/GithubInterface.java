package com.example.android.boilerplate.network;

import com.example.android.boilerplate.model.GitRepo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by carolinamarin on 2/3/18.
 */

public interface GithubInterface {
    @GET("/users/{user}/repos")
    Observable<List<GitRepo>> getRepository(@Path("user") String userName);
}
