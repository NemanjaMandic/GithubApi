package com.example.nemus.githubapi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;


import com.example.nemus.githubapi.model.GithubRepo;
import com.example.nemus.githubapi.rest.APIClient;
import com.example.nemus.githubapi.rest.GithubRepoEndPoint;
import com.example.nemus.githubapi.rest.ReposAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repositories extends AppCompatActivity {

    String receivedUserName;
    TextView userNameTV;
    RecyclerView mRecyclerView;
    List<GithubRepo> myDataSource = new ArrayList<>();
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        Bundle extras = getIntent().getExtras();
        receivedUserName = extras.getString("username");

        userNameTV = (TextView) findViewById(R.id.userNameTV);

        userNameTV.setText("User: " + receivedUserName);


        mRecyclerView = (RecyclerView) findViewById(R.id.repos_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new ReposAdapter(myDataSource, R.layout.list_item_repo, getApplicationContext());

        mRecyclerView.setAdapter(myAdapter);

        loadRepositories();
    }

    public void loadRepositories(){
        GithubRepoEndPoint apiservice = APIClient.getClient().create(GithubRepoEndPoint.class);

        Call<List<GithubRepo>> call = apiservice.getRepo(receivedUserName);
        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {

                myDataSource.clear();
                myDataSource.addAll(response.body());
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                Log.e("Repos", t.toString());
            }
        });
    }
}


























