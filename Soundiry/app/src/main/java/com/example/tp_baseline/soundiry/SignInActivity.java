package com.example.tp_baseline.soundiry;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SignInActivity extends AppCompatActivity {

    private EditText userID;
    private EditText password;
    private Button signIn;
    private TextView attempt;
    private TextView attemptCount;
    private TextView forgetPassword;
    private ImageView back;

    int attempt_count = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setTitle("Sign In");
        getSupportActionBar().hide();

        SignIn();

    }

    public void SignIn() {

        userID = (EditText) findViewById(R.id.etUserID);
        password = (EditText) findViewById(R.id.etPassword);
        signIn = (Button) findViewById(R.id.btnSignIn);
        attempt = (TextView) findViewById(R.id.tvAttempt);
        attemptCount = (TextView) findViewById(R.id.tvAttemptCount);
        forgetPassword = (TextView) findViewById(R.id.tvForget);
        back = (ImageView) findViewById(R.id.ivBack);

        attemptCount.setText(Integer.toString(attempt_count));

        signIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (userID.getText().toString().equals("user123") && password.getText().toString().equals("password")) {
                            Toast.makeText(SignInActivity.this, "Welcome to soundiry!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignInActivity.this, HomePageActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(SignInActivity.this, "Username and password is NOT correct",
                                    Toast.LENGTH_SHORT).show();
                            attempt_count--;
                            attemptCount.setText(Integer.toString(attempt_count));
                            if (attempt_count == 0)
                                signIn.setEnabled(false);
                        }
                    }
                }
        );

        forgetPassword.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SignInActivity.this, CreateAccountActivity.class);
                        startActivity(intent);
                    }
                }
        );

        back.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

}
