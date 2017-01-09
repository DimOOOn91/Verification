package com.example.dima.firebaseauthtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyFirebase";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private EditText mEmail;
    private EditText mPassword;
    private Button mButtonLogIn;
    private Button mButtonLogOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                TextView result = (TextView) findViewById(R.id.result);

                if (user != null) {
                    // User is signed in
                    mButtonLogIn.setVisibility(View.GONE);
                    mButtonLogOut.setVisibility(View.VISIBLE);
                    result.setText(getString(R.string.logged_in));
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out

                    mButtonLogIn.setVisibility(View.VISIBLE);
                    mButtonLogOut.setVisibility(View.GONE);
                    result.setText(null);
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        mEmail = (EditText) findViewById(R.id.login);
        mPassword = (EditText) findViewById(R.id.password);

        mButtonLogIn = (Button) findViewById(R.id.button_login);
        mButtonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });
        mButtonLogOut = (Button) findViewById(R.id.button_logout);
        mButtonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });
        Button buttonCreateAccount = (Button) findViewById(R.id.button_createaccount);
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "create account:" + email);
        if (!validForm()) {
            return;
        }

        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "create account: onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, getString(R.string.auth_failed), Toast.LENGTH_SHORT).show();
                        } else {
                            mPassword.setText(null);
                            mEmail.setText(null);
                        }
                    }
                });
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "sign in:" + email);
        if (!validForm()) {
            return;
        }

        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "sign in: onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, getString(R.string.auth_failed), Toast.LENGTH_SHORT).show();
                        } else {
                            mPassword.setText(null);
                            mEmail.setText(null);
                        }
                    }
                });
    }

    private void signOut() {
        mFirebaseAuth.signOut();
    }

    private boolean validForm() {
        boolean valid = true;

        String email = mEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmail.setError(getString(R.string.required));
            valid = false;
        } else {
            mEmail.setError(null);
        }

        String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPassword.setError(getString(R.string.required));
            valid = false;
        } else if (password.length() < 6) {
            mPassword.setError(getString(R.string.too_short));
            valid = false;
        } else {
            mPassword.setError(null);
        }
        return valid;
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                signIn(mEmail.getText().toString(), mPassword.getText().toString());
                break;
            case R.id.button_logout:
                signOut();
                break;
            case R.id.button_createaccount:
                createAccount(mEmail.getText().toString(), mPassword.getText().toString());
                break;
            default:
                break;
        }

    }

}

