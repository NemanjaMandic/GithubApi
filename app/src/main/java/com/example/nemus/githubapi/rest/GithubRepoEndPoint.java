package com.example.nemus.githubapi.rest;

import com.example.nemus.githubapi.model.GithubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by nemus on 11/11/2016.
 */
public interface GithubRepoEndPoint {

    @GET("/users/{user}/repos")
    Call<List<GithubRepo>> getRepo(@Path("user") String name);
}
