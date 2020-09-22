package com.example.gsmgosu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity  extends AppCompatActivity {
    private FirebaseAuth mAuth = null;
    public static String jsonResult = "";
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private Button login;
    private Context ct;
    public static String UserName, UserEmail, UserPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login_google);
        mAuth = FirebaseAuth.getInstance();
        ct = this;

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        mAuth = FirebaseAuth.getInstance();
//        if (mAuth.getCurrentUser() != null) {
//            Intent intent = new Intent(getApplication(),MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    // [START signin]
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("task", "updateUI: ");
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            UserName = acct.getDisplayName();
                            UserEmail = acct.getEmail();
                            Uri uri = acct.getPhotoUrl();
                            UserPhoto = uri.toString();

                            JSONTask jt = new JSONTask();
                            jt.execute("http://54.210.142.125:7777/user");

                            Handler hd = new Handler();
                            hd.postDelayed(new LoginActivity.splashhandler(jt), 2000);

                            Log.d("Name", "updateUI: "+UserName);
                            // Sign in success, update UI with the signed-in user's information
                            Snackbar.make(findViewById(R.id.layout_main), "Authentication Successed.", Snackbar.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Snackbar.make(findViewById(R.id.layout_main), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
    public class splashhandler implements Runnable {
        JSONTask jt;
        public splashhandler(JSONTask jt) {
            this.jt = jt;
        }

        public void run() {

            if (jt.jsonReturn().equals("")){
                jsonResult = "network_error";
                return;
            }
            try {
                JSONObject jo = new JSONObject(jt.jsonReturn());
                if (!jo.getString("access_token").isEmpty()){
                    SharedPreferences sharedPreferences = getSharedPreferences("access_token",MODE_PRIVATE);
                    //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("access_token", jo.getString("access_token")); // key, value를 이용하여 저장하는 형태
                    editor.commit();


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("login.java", "result: "+jt.jsonReturn());
            jsonResult = jt.jsonReturn();
        }
    }

    private void updateUI(FirebaseUser user) { //update ui code here
        if (user != null) {
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
