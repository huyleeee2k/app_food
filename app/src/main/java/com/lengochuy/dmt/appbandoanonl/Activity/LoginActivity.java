package com.lengochuy.dmt.appbandoanonl.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.lengochuy.dmt.appbandoanonl.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class LoginActivity extends AppCompatActivity {

//    View day_bg, night_bg;
//    ImageView img_Sun, day_land, night_land;
//    DayNightSwitch dayNightSwitch;
    TextInputEditText textInputEditTextUserName, textInputEditTextPassWord;
    Button buttonLogin;
    TextView textViewSignUp;
    ProgressBar progressBar;
    public static String userNameMain = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initUI();
        initMain();
        eventButtonLogin();
    }

    private void eventButtonLogin() {
        buttonLogin.setOnClickListener(view -> {
            String username = String.valueOf(textInputEditTextUserName.getText());
            String password = String.valueOf(textInputEditTextPassWord.getText());

            if (!username.equals("") && !password.equals("")) {
                progressBar.setVisibility(View.VISIBLE);
                //Start ProgressBar first (Set visibility VISIBLE)
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(() -> {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[2];
                    field[0] = "username";
                    field[1] = "password";
                    //Creating array for data
                    String[] data = new String[2];
                    data[0] = username;
                    data[1] = password;
                    PutData putData = new PutData("https://laptrinhandroidcunglehuy.000webhostapp.com/login.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE);
                            String result = putData.getResult();
                            if (result.equals("Login Success")){
                                userNameMain = data[0];
                                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(this,MainActivity.class));
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

    private void initMain() {
        textInputEditTextUserName   = findViewById(R.id.userNameLogin);
        textInputEditTextPassWord   = findViewById(R.id.passwordLogin);
        buttonLogin                 = findViewById(R.id.buttonLogin);
        textViewSignUp              = findViewById(R.id.textViewLogin);
        progressBar                 = findViewById(R.id.progressBarLogin);
        textViewSignUp.setOnClickListener(view ->
                startActivity(new Intent(this,SignUpActivity.class)));
    }

//    private void initUI() {
//        day_bg          = findViewById(R.id.day_bg);
//        night_bg        = findViewById(R.id.night_bg);
//        img_Sun         = findViewById(R.id.imgSun);
//        day_land        = findViewById(R.id.day_img);
//        night_land      = findViewById(R.id.night_img);
//        dayNightSwitch  = findViewById(R.id.day_night_switch);
//
//        dayNightSwitch.setListener(b -> {
//            if (b){
//                img_Sun.animate().translationY(197).setDuration(1000);
//                day_land.animate().alpha(0).setDuration(1300);
//                day_bg.animate().setDuration(1300).alpha(0);
//            }else{
//                img_Sun.animate().translationY(-110).setDuration(1000);
//                day_land.animate().alpha(1).setDuration(1300);
//                day_bg.animate().setDuration(1300).alpha(1);
//            }
//        });
//    }
}