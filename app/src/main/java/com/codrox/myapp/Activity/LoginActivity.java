package com.codrox.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codrox.myapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    LinearLayout signup_layout, login_layout;

    TextView txt_sign_up, txt_login;
    TextInputEditText edl_email, edl_password, edr_email, edr_name, edr_password;
    Button btn_signup, btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        setContentView(R.layout.activity_login);

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

    }

    private void registerUser(String name, String email, String password) {

    }

    private void signup(boolean value) {
        if (value) {
            login_layout.setVisibility(View.GONE);
            signup_layout.setVisibility(View.VISIBLE);
        } else {
            login_layout.setVisibility(View.VISIBLE);
            signup_layout.setVisibility(View.GONE);
        }
    }
}
