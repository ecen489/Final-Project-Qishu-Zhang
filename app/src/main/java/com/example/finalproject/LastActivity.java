package com.example.finalproject;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.view.View.VISIBLE;
import static com.example.finalproject.GameActivity.REQUEST_GAME_ACTIVITY;

public class LastActivity extends AppCompatActivity implements View.OnClickListener,WordFragment.OnInputListener{

    //PICTURE FEATURE
    public static final int PICTURE_ACTIVITY_REQUEST_CODE = 1;
    String pic_txt_result, yikes;

    //UI
    Button button_signup, button_login, button_start, button_word, button_picture;
    EditText edit_email, edit_pwd;
    TextView exec_cred;
    ListView scoresList, scores;
    String[] scoresAccounts = {"QISHUZHANG@GMAIL.COM - 13", "HI@CO.UK - 4", "TEST@TAMU.EDU - 3", "T@TA.DA - 1", "Z@TAM.EDU - 1"};


    //LOGIN TO FIREBASE
    private FirebaseAuth mAuth;
    //is user logged in?
    FirebaseUser user = null;
    ListAdapter listAdapt;

    //WORD FRAGMENT
    public String key_word, key_hint; // stores word submitted from first fragment


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //convert alphabetList array to UI list format
        listAdapt = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, scoresAccounts);
        //display array in list UI
        scores = findViewById(R.id.score_list);
        scores.setAdapter(listAdapt);


        //FIREBASE
        //initialize firebase auth obj
        mAuth = FirebaseAuth.getInstance();
        //initialize view

        //UI
        //initialize views
        button_signup = findViewById(R.id.signup_button);
        button_login = findViewById(R.id.login_button);
        edit_email = findViewById(R.id.email_editTxt);
        edit_pwd = findViewById(R.id.password_editTxt);
        exec_cred = findViewById(R.id.txt_executioner_cred);
        button_start = findViewById(R.id.start_button);
        button_word = findViewById(R.id.type_button);
        button_picture = findViewById(R.id.picture_button);

        //attach listener to buttons
        button_signup.setOnClickListener(this);
        button_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        //calling register/login method on click
        switch(view.getId()){
            case R.id.signup_button:
                registerUser();
                break;

            case R.id.login_button:
                userLogin();
                break;

            default:
                break;
        }
    }

    private void registerUser() {

        //getting email and password from edit texts
        String email = edit_email.getText().toString().trim();
        String password = edit_pwd.getText().toString().trim();

        //checking if email and passwords are empty
        if(email.isEmpty()){
            edit_email.setError("Email is required");
            edit_email.requestFocus();
            return;
        } else if (!isEmailValid(email)){
            edit_email.setError("Invalid email format");
            edit_email.requestFocus();
            return;
        } else if (password.isEmpty()){
            edit_pwd.setError("Password is required");
            edit_pwd.requestFocus();
            return;
        }else if (password.length()<6){
            edit_pwd.setError("Password must be 6 characters or more");
            edit_pwd.requestFocus();
            return;
        }

        //if the email and password are not empty
        //creating a new user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Account has been successfully created", Toast.LENGTH_SHORT).show();
                            user = mAuth.getCurrentUser(); //The newly created user is already signed in
                        } else {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(), "Error. Account already exists.", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void userLogin(){
        final String email = edit_email.getText().toString().trim();
        final String password = edit_pwd.getText().toString().trim();

        if(email.isEmpty()){
            edit_email.setError("Email is required");
            edit_email.requestFocus();
            return;
        } else if (!isEmailValid(email)){
            edit_email.setError("Invalid email format");
            edit_email.requestFocus();
            return;
        } else if (password.isEmpty()){
            edit_pwd.setError("Password is required");
            edit_pwd.requestFocus();
            return;
        }else if (password.length()<6){
            edit_pwd.setError("Password must be 6 characters or more");
            edit_pwd.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            user = mAuth.getCurrentUser(); //The user is signed in
//                            Intent intent  = new Intent(MainActivity.this, PullActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(intent);
                            button_login.setEnabled(false);
                            button_signup.setEnabled(false);
                            edit_email.setEnabled(false);
                            edit_pwd.setEnabled(false);
                            button_word.setEnabled(true);
                            button_picture.setEnabled(true);

                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void sendInput(String input) {
        key_word = input;


    }

    @Override
    public void sendHint(String hint) {
        key_hint = hint;
    }

    public void StartClk(View view) {

        if(key_word == null){

            //test
            Toast.makeText(getApplicationContext(), pic_txt_result, Toast.LENGTH_SHORT).show();
        }
        else{
            //create intent & attach data
            Intent forwardIntent = new Intent(LastActivity.this,GameActivity.class);
            //bundle & send the two data points QuestionPage needs (topic & score)
            Bundle info = new Bundle();
            info.putString("word", key_word);
            info.putString("hint", key_hint);
            forwardIntent.putExtras(info);



            //start game activity
            startActivity(forwardIntent);
        }


    }

    //receives word from picture activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == PICTURE_ACTIVITY_REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK) {
                pic_txt_result = data.getStringExtra("word");
                // do something with the result
                Toast.makeText(getApplicationContext(), pic_txt_result , Toast.LENGTH_SHORT).show();

            } else if (resultCode == Activity.RESULT_CANCELED) {
                // some stuff that will happen if there's no result
                Toast.makeText(getApplicationContext(), "ERROR: NO WORD RECEIVED", Toast.LENGTH_SHORT).show();
            }
//            else if (resultCode == REQUEST_GAME_ACTIVITY){
//                Intent intent  = new Intent(LoginActivity.this, LastActivity.class);
////                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                            startActivity(intent);
//
//
//            }
        }
    }

    public void pickKeyboard(View view) {
        WordFragment dialog = new WordFragment();
        dialog.show(getSupportFragmentManager(), "MyCustomDialog");
        button_word.setEnabled(false);
        button_picture.setEnabled(false);
        button_start.setEnabled(true);
    }

    public void pickCamera(View view) {
        //create intent & attach data
        Intent pictureIntent = new Intent(LastActivity.this, PictureActivity.class);
        pictureIntent.putExtra("word", key_word);
        startActivityForResult(pictureIntent, PICTURE_ACTIVITY_REQUEST_CODE);
    }
}
