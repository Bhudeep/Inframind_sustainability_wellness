package com.example.helthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    Button signin;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.inputMail);
        password=findViewById(R.id.inputPassword);
        //signup=findViewById(R.id.btnsignup);
        signin=findViewById(R.id.btnRegister);

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser= mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Toast.makeText(MainActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity.this,Information.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //-----
                String name;
                String mail= email.getText().toString();
                String pass=password.getText().toString();
                String conpass;
                if(mail.isEmpty()){
                    email.setError("Please Enter the EMAIL ID");
                    email.requestFocus();
                }
                else if(pass.isEmpty()){
                    password.setError("Please Enter Ur PASSWORD");
                    password.requestFocus();
                }
                else if(mail.isEmpty()  &&  pass.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Fiels are Empty",Toast.LENGTH_SHORT).show();
                }
                else if(!(mail.isEmpty()  &&  pass.isEmpty()))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Login Error,Please login again", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intToHome = new Intent(MainActivity.this, Information.class);
                                startActivity(intToHome);

                            }
                        }
                    });

                }
                          /*  else{
                          startActivity(new Intent(Login.this,Information.class));

                            }
                        }
                    });
                }*/
                else{
                    Toast.makeText(MainActivity.this,"Error 404...!",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}