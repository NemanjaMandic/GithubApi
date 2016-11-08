package com.example.nemus.githubapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText inputUser;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.btn_login);
        inputUser = (EditText) findViewById(R.id.input_username);
    }

    public void getUser(View view){
        //from LoginActivity go to UserActivity
        i = new Intent(LoginActivity.this, UserActivity.class);
        //from LoginActivity pass inputUser param to UserActivity which i access with STRING_I_NEED
        i.putExtra("STRING_I_NEED", inputUser.getText().toString());
        startActivity(i);
    }
}
