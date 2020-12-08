package com.lengochuy.dmt.appbandoanonl.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.lengochuy.dmt.appbandoanonl.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText textInputEditTextFullName, textInputEditTextUserName,
            textInputEditTextPassword, textInputEditTextEmail;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initUI();
        eventButtonSignUp();
    }

    private void eventButtonSignUp() {
        buttonSignUp.setOnClickListener(view -> {
            String fullname = String.valueOf(textInputEditTextFullName.getText());
            String username = String.valueOf(textInputEditTextUserName.getText());
            String password = String.valueOf(textInputEditTextPassword.getText());
            String email    = String.valueOf(textInputEditTextEmail.getText());
            if (!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("")) {
                progressBar.setVisibility(View.VISIBLE);
                //Start ProgressBar first (Set visibility VISIBLE)
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(() -> {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[4];
                    field[0] = "fullname";
                    field[1] = "username";
                    field[2] = "password";
                    field[3] = "email";
                    //Creating array for data
                    String[] data = new String[4];
                    data[0] = fullname;
                    data[1] = username;
                    data[2] = password;
                    data[3] = email;
                    PutData putData = new PutData("https://laptrinhandroidcunglehuy.000webhostapp.com/signup.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE);
                            String result = putData.getResult();
                            if (result.equals("Sign Up Success")){
                                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(this,LoginActivity.class));
                                finish();
                            }else{
                                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    //End Write and Read data with URL
                });
            }else{
                Toast.makeText(this, "Please check information!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUI() {
        textInputEditTextFullName   = findViewById(R.id.fullname);
        textInputEditTextUserName   = findViewById(R.id.username);
        textInputEditTextPassword   = findViewById(R.id.password);
        textInputEditTextEmail      = findViewById(R.id.email);
        buttonSignUp                = findViewById(R.id.buttonSignUp);
        textViewLogin               = findViewById(R.id.textViewSignUp);
        progressBar                 = findViewById(R.id.progressBarSignUp);
        textViewLogin.setOnClickListener(view ->
                startActivity(new Intent(this,LoginActivity.class)));
    }
}