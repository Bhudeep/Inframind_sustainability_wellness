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

public class Login extends AppCompatActivity {

    //*
    EditText email,password;
    Button signup,signin;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mFirebaseAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.inputUserName);
        password=findViewById(R.id.txtpassword);
        signup=findViewById(R.id.btnsignup);
        signin=findViewById(R.id.sign_in);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail= email.getText().toString();
                String pass=password.getText().toString();
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
                    Toast.makeText(Login.this,"Fiels are Empty",Toast.LENGTH_SHORT).show();
                }
                else if(!(mail.isEmpty()  &&  pass.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Login.this,"SignUp Unsuccessful, Please Try again",Toast.LENGTH_SHORT).show();

                            }
                            else{
                                startActivity(new Intent(Login.this,Information.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(Login.this,"Error 404...!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,MainActivity.class);
                startActivity(i);

            }
        });

    }
}