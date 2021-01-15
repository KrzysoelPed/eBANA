package com.kp.ebana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Sign extends AppCompatActivity {

    EditText mName, mLogin, mPass, mPhone, mEmail;
    FirebaseAuth fAuth;
    Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_sign);

        mName = findViewById (R.id.imie_nazwisko);
        mLogin = findViewById (R.id.login);
        mPass = findViewById (R.id.password);
        mPhone = findViewById (R.id.phone);
        mEmail = findViewById (R.id.email);
        mRegisterButton = findViewById (R.id.rejestracja_button);
        fAuth = FirebaseAuth.getInstance ();

        if(fAuth.getCurrentUser () != null)
        {
            startActivity (new Intent (getApplicationContext (), MainActivity.class));
            finish ();
        }

        mRegisterButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText ().toString ().trim();
                String password = mPass.getText ().toString ().trim();

                if(TextUtils.isEmpty (email))
                {
                    mEmail.setError ("Bad email");
                    return;
                }
                if(TextUtils.isEmpty (password))
                {
                    mPass.setError ("Bad password");
                    return;
                }

                fAuth.createUserWithEmailAndPassword (email, password).addOnCompleteListener (new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            startActivity (new Intent (getApplicationContext (), MainActivity.class));
                            finish ();
                            //Toast.makeText (this, "Welcome to aplication", Toast.LENGTH_SHORT).show ();


                        }
                    }
                });
            }
        });
    }
}
