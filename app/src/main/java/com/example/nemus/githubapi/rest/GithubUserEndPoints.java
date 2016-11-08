package com.example.nemus.githubapi.rest;

import com.example.nemus.githubapi.model.GithubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by nemus on 11/8/2016.
 */
public interface GithubUserEndPoints {

    @GET("/users/{user}")
    Call<GithubUser> getUser(@Path("user") String user);
}
