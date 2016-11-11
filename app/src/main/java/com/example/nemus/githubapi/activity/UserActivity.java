package com.example.nemus.githubapi.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nemus.githubapi.R;
import com.example.nemus.githubapi.model.GithubUser;
import com.example.nemus.githubapi.rest.APIClient;
import com.example.nemus.githubapi.rest.GithubUserEndPoints;
import com.example.nemus.githubapi.rest.ImageDownloader;

import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    ImageView avatarImg;
    TextView userNameTV;
    TextView followersTV;
    TextView followingTV;
    TextView login;
    TextView email;
    Button ownedrepos;
    Bundle extras;
    String newString;

    Bitmap myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        avatarImg = (ImageView) findViewById(R.id.avatar);
        userNameTV = (TextView) findViewById(R.id.username);
        followersTV = (TextView) findViewById(R.id.followers);
        followingTV = (TextView) findViewById(R.id.following);
        login = (TextView) findViewById(R.id.login);
        email = (TextView) findViewById(R.id.email);
        ownedrepos = (Button) findViewById(R.id.ownedReposBtn);

        extras = getIntent().getExtras();
        newString = extras.getString("STRING_I_NEED");
        System.out.println(newString);

        loadData();
    }

    public void loadData(){
        final GithubUserEndPoints apiService = APIClient.getClient().create(GithubUserEndPoints.class);

        Call<GithubUser> call = apiService.getUser(newString);
        call.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {

                if(response.body().getName() == null){
                    userNameTV.setText("No username provided");
                }else{
                    userNameTV.setText("Username: " + response.body().getName());
                }

                followersTV.setText("Followers: " + response.body().getFollowers());
                followingTV.setText("Following: " + response.body().getFollowing());
                login.setText("LogIn: " + response.body().getLogin());

                if(response.body().getEmail() == null){
                    email.setText("Email not provided");
                }else{
                    email.setText("Email: " + response.body().getEmail());
                }

                ImageDownloader task = new ImageDownloader();

                try{
                    myImage = task.execute(response.body().getAvatar()).get();
                }catch(ExecutionException e){
                    e.printStackTrace();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                avatarImg.setImageBitmap(myImage);
                avatarImg.getLayoutParams().height=220;
                avatarImg.getLayoutParams().width=220;

            }
 ;;;;
            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {

            }
        });
    }

}

































