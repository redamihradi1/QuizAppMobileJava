package com.example.quiiiizreda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    private EditText editextFullName , editTextAge , editTextEmail, editTextPassword ;
    private ProgressBar progressBar ;
    private TextView   registerUser ;
    private ImageView banner;

    private FirebaseAuth mAuth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editextFullName = (EditText) findViewById(R.id.FullName);
        editTextAge = (EditText) findViewById(R.id.Age);
        editTextEmail =(EditText) findViewById(R.id.Email);
        editTextPassword =(EditText) findViewById(R.id.Password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        banner = findViewById(R.id.logo);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.logo:
                startActivity(new Intent(this,MainActivity.class));
                break;

            case R.id.registerUser:
                registerUser();
                startActivity(new Intent(this,MainActivity.class));
                break;
        }

    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullName = editextFullName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        if(fullName.isEmpty()){
            editextFullName.setError("Full name is required");
            editextFullName.requestFocus();
            return;
        }
        if(age.isEmpty()){
            editTextAge.setError("Age is required");
            editTextAge.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Plzz provide a valid email");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editTextPassword.setError("Min password length should be 6 characters !! ");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful()){
                              User user = new User(fullName,age,email);
                              FirebaseDatabase.getInstance().getReference("Users")
                                      .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                      .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                  @Override
                                  public void onComplete(@NonNull Task<Void> task) {
                                      if(task.isSuccessful()){
                                          Toast.makeText(RegisterUser.this , "User has been registred successfully !! ",Toast.LENGTH_LONG).show();
                                          progressBar.setVisibility(View.GONE);

                                          // redirect  to login layout !!
                                      }else {
                                          Toast.makeText(RegisterUser.this , "Fail to register !! try again ",Toast.LENGTH_LONG).show();
                                          progressBar.setVisibility(View.GONE);
                                      }
                                  }
                              });

                          }else {
                              Toast.makeText(RegisterUser.this , "Fail to register !! ",Toast.LENGTH_LONG).show();
                              progressBar.setVisibility(View.GONE);
                          }
                    }
                });


    }
}