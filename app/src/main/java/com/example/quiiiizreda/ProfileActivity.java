package com.example.quiiiizreda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity  {

    private FirebaseUser user ;
    private DatabaseReference reference;

    private String userId;


    private Button logout,start ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logout = (Button) findViewById(R.id.signOut);
        start = (Button) findViewById(R.id.startQuizz);





        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(ProfileActivity.this , MainActivity.class));




                }



        });

        user = FirebaseAuth.getInstance().getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

        TextView fullNameTextView = (TextView) findViewById(R.id.fullName);
        TextView emailTextView = (TextView) findViewById(R.id.emailAdress);
        TextView ageTextView = (TextView) findViewById(R.id.age);

        emailTextView.setText(user.getEmail());

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String fullName = userProfile.fullName;
                    String email = userProfile.email;
                    String age = userProfile.age;

                    fullNameTextView.setText(fullName);
                    emailTextView.setText(email);
                    ageTextView.setText(age);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this,"Somthing wrong happend !! ",Toast.LENGTH_LONG).show();
            }
        });


    start.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(ProfileActivity.this, MainApp.class));

        }
    });


    }
}