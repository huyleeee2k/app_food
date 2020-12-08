package com.lengochuy.dmt.appbandoanonl.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lengochuy.dmt.appbandoanonl.Object.OrderHistory;
import com.lengochuy.dmt.appbandoanonl.R;

import java.util.Objects;

public class SeeDetailsActivity extends AppCompatActivity {
    TextView textViewAddress, textViewPhoneNumber,
             textViewNameFood, textViewPrice, textViewOrderDate;
    ProgressBar progressBar;
    Button buttonSendFeedback;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_details);

        initUI();
        actionBar();
        loadDataUI();
        setEventSendFeedBack();
    }

    private void setEventSendFeedBack() {
        buttonSendFeedback.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            new Handler().postDelayed(() -> {
                Toast.makeText(this, "Sent your feedback, thank you for the feedback", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            },2000);
        });

    }

    private void loadDataUI() {
        OrderHistory orderHistory = (OrderHistory) getIntent().getSerializableExtra("history");
        textViewAddress.setEllipsize(TextUtils.TruncateAt.END);
        textViewAddress.setMaxLines(2);
        textViewAddress.setText(orderHistory.getAddress());

        textViewPhoneNumber.setText(orderHistory.getPhoneNumber());

        textViewNameFood.setEllipsize(TextUtils.TruncateAt.END);
        textViewNameFood.setMaxLines(3);
        textViewNameFood.setText(orderHistory.getNameFood());

        textViewPrice.setText(orderHistory.getPrice());
        textViewPrice.setText(orderHistory.getDate());
    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
    }

    private void initUI() {
        textViewAddress     = findViewById(R.id.textViewAddressSD);
        textViewPhoneNumber = findViewById(R.id.textViewPhoneNumberSD);
        textViewNameFood    = findViewById(R.id.textViewNameFoodSD);
        textViewPrice       = findViewById(R.id.textViewPriceSD);
        textViewOrderDate   = findViewById(R.id.textViewOrderDateSD);
        progressBar         = findViewById(R.id.progressBarSD);
        buttonSendFeedback  = findViewById(R.id.buttonSendFeedBackSD);
        toolbar             = findViewById(R.id.toolBarSD);
    }
}