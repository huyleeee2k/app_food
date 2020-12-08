package com.lengochuy.dmt.appbandoanonl.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.lengochuy.dmt.appbandoanonl.R;

public class IntentScreenPayActivity extends AppCompatActivity {
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_screen_pay);

        init();
    }

    private void init() {
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(IntentScreenPayActivity.this, PaymentActivity.class);
            startActivity(intent);
            finish();
        },2000);
    }
}