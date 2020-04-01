package com.example.tp_baseline.soundiry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hide the action bar
        getSupportActionBar().hide();
    }


    //Go to create account page
    public void goToCreateAccount(View view) {
        startActivity(new Intent(MainActivity.this, CreateAccountActivity.class));
    }

    //Go to sign in page
    public void goToSignIn(View view) {
        startActivity(new Intent(MainActivity.this, SignInActivity.class));
    }

}
