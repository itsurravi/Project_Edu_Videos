package com.codrox.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codrox.myapp.Database.DB_Handler;
import com.codrox.myapp.Database.PrefManger;
import com.codrox.myapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    LinearLayout signup_layout, login_layout;

    TextView txt_sign_up, txt_login;
    TextInputEditText edl_email, edl_password, edr_email, edr_name, edr_password;
    Button btn_signup, btn_login;

    private PrefManger prefManger;

    private DB_Handler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManger = new PrefManger(this);

        if(prefManger.isLoggedIn(PrefManger.USER_LOGIN))
        {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);

        db = new DB_Handler(this);

        txt_login = findViewById(R.id.txt_login);
        txt_sign_up = findViewById(R.id.txt_sign_up);
        signup_layout = findViewById(R.id.signup_layout);
        login_layout = findViewById(R.id.login_layout);
        edl_email = findViewById(R.id.edl_email);
        edl_password = findViewById(R.id.edl_password);
        edr_name = findViewById(R.id.edr_name);
        edr_email = findViewById(R.id.edr_email);
        edr_password = findViewById(R.id.edr_password);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);

        txt_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup(true);
            }
        });

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup(false);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edr_name.getText().toString().trim();
                String email = edr_email.getText().toString().trim();
                String password = edr_password.getText().toString();

                registerUser(name, email, password);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edl_email.getText().toString().trim();
                String password = edl_password.getText().toString().trim();

                loginUser(email, password);
            }
        });
    }

    private void loginUser(String email, String password) {
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches() && !password.isEmpty() && password.length()>8)
        {
            Cursor c = db.signInUserWithEmailAndPassword(email, password);
            if(c!=null && c.getCount()>0)
            {
                c.moveToFirst();
                String e = c.getString(c.getColumnIndex(DB_Handler.USER_EMAIL));
                String p = c.getString(c.getColumnIndex(DB_Handler.USER_PASSWORD));

                prefManger.setStringValues(DB_Handler.USER_EMAIL, e);
                prefManger.setStringValues(DB_Handler.USER_PASSWORD, p);
                prefManger.setLoggedIn(PrefManger.USER_LOGIN, true);

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
            else
            {
                edl_email.setText("");
                edl_password.setText("");
                Toast.makeText(this, "User Not Registered\nPlease Register First", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.isEmpty())
            {
                edl_email.setError("Please Enter Valid Email USER_ID");
                edl_email.requestFocus();
            }
            else if(password.isEmpty())
            {
                edl_password.setError("Please Fill Your Password");
                edl_password.requestFocus();
            }
            else if(password.length()<=8)
            {
                edl_password.setError("Password is Short");
                edl_password.requestFocus();
            }
        }
    }

    private void registerUser(String name, String email, String password) {
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches() && !(name.isEmpty() || password.isEmpty()) && password.length()>8)
        {
            db.registerUser(name, email, password);
            Toast.makeText(this, "User Registered\nNow Login", Toast.LENGTH_LONG).show();
            signup(false);
        }
        else
        {
            if(name.isEmpty())
            {
                edr_name.setError("Please Enter Your Name");
                edr_name.requestFocus();
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.isEmpty())
            {
                edr_email.setError("Please Enter Valid Email USER_ID");
                edr_email.requestFocus();
            }
            else if(password.isEmpty())
            {
                edr_password.setError("Please Fill Your Password");
                edr_password.requestFocus();
            }
            else if(password.length()<=8)
            {
                edr_password.setError("Password is Short");
                edr_password.requestFocus();
            }

        }
    }

    private void signup(boolean value) {
        if (value) {
            edl_email.setText("");
            edl_password.setText("");
            login_layout.setVisibility(View.GONE);
            signup_layout.setVisibility(View.VISIBLE);
            edr_name.requestFocus();
        } else {
            edr_email.setText("");
            edr_password.setText("");
            edr_name.setText("");
            login_layout.setVisibility(View.VISIBLE);
            signup_layout.setVisibility(View.GONE);
        }
    }
}
