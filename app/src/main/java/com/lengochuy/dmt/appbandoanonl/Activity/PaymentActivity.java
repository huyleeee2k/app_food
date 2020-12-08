package com.lengochuy.dmt.appbandoanonl.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.lengochuy.dmt.appbandoanonl.Linkserver.Link;
import com.lengochuy.dmt.appbandoanonl.Object.NewOrder;
import com.lengochuy.dmt.appbandoanonl.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {
    TextView textViewTotalMoney, textViewPaymentInCash, textViewPaymentByCard;
    @SuppressLint("StaticFieldLeak")
    public static EditText editTextAddress, editTextNote, editTextPhone;
    Button buttonGetAddress, buttonPayment, buttonBackPayment;
    ProgressBar progressBarLoading;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        init();
        receiverIntentFromPayOrder();
        eventPressButtonBack();
        eventPressTextViewPaymentByCard();
        eventPressButtonGetAddress();
        eventPressButtonPay();
    }

    private void eventPressButtonPay() {
        buttonPayment.setOnClickListener(view -> {
            StringBuilder stringBuilder = new StringBuilder();
            String str = PayOrderActivity.txtMoneyFoodCost.getText().toString().trim();
            for (NewOrder x : MainActivity.newOrderArrayList) {
                stringBuilder.append(x.getNameFood()).append("\n");
            }
            String address = editTextAddress.getText().toString().trim();
            String sdt = editTextPhone.getText().toString().trim();
            Date date = new Date();
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String dateFormat = simpleDateFormat.format(date);
            if (address.length() > 0 && sdt.length() > 0) {
                final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Link.listOrder, response1 -> {
                    Log.d("kiemtra1",response1);
                    if (!response1.equals("")) {
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        StringRequest request = new StringRequest(Request.Method.POST, Link.listOrderDetails, response -> {
                            if (response.equals("1")) {
                                Log.d("kiemtra2",response);
                                MainActivity.newOrderArrayList.clear();
                                Toast.makeText(PaymentActivity.this, "Your order has entered the cart, " +
                                        "please continue to purchase.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(PaymentActivity.this, MainActivity.class));

                                //Toast.makeText(PaymentActivity.this, "Mời bạn tiếp tục mua hàng", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(PaymentActivity.this, "Add to cart failed, maybe our system is faulty", Toast.LENGTH_SHORT).show();
                            }
                        }, error -> {

                        }) {
                            @Override
                            protected Map<String, String> getParams() {
                                JSONArray jsonArray = new JSONArray();
                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("madonhang", response1);
                                    jsonObject.put("hinhanhdoan",
                                            MainActivity.newOrderArrayList.get(0).getResourceID());
                                    jsonObject.put("tendoan", stringBuilder.toString());
                                    jsonObject.put("giadoan", str);
                                    jsonObject.put("ngaygiaodoan",dateFormat);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                jsonArray.put(jsonObject);
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("json", jsonArray.toString());
                                return hashMap;
                            }
                        };
                        queue.add(request);
                    }
                }, error -> {

                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("tennguoidung",LoginActivity.userNameMain);
                        Log.d("tennguoidung",LoginActivity.userNameMain);
                        hashMap.put("diachi", address);
                        hashMap.put("sodienthoai", sdt);
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
            } else {
                Toast.makeText(PaymentActivity.this, "Hãy kiểm tra lại dữ liệu",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void receiverIntentFromPayOrder() {
        textViewTotalMoney.setText(PayOrderActivity.txtTotalMoney.getText().toString().trim());
    }

    private void eventPressButtonGetAddress() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        buttonGetAddress.setOnClickListener(view -> {
            progressBarLoading.setVisibility(View.VISIBLE);
            new Handler().postDelayed(() -> {
                if (ActivityCompat.checkSelfPermission(PaymentActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(PaymentActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
                progressBarLoading.setVisibility(View.INVISIBLE);
            }, 2000);

            new Handler().postDelayed(() -> {
                if (editTextAddress.getText().toString().length() == 0) {
                    Toast.makeText(PaymentActivity.this, "Please turn on GPS.",
                            Toast.LENGTH_SHORT).show();
                }
            }, 3000);


        });
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
            Location location = task.getResult();
            if (location != null) {
                try {
                    Geocoder geocoder = new Geocoder(PaymentActivity.this, Locale.getDefault());
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                            location.getLongitude(), 1);
                    editTextAddress.setText(String.valueOf(addresses.get(0).getAddressLine(0)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void eventPressTextViewPaymentByCard() {
        textViewPaymentByCard.setOnClickListener(view ->
                Toast.makeText(this, "Sorry, our system is currently upgrading this functionality!!!",
                        Toast.LENGTH_SHORT).show());
    }

    private void eventPressButtonBack() {
        buttonBackPayment.setOnClickListener(view -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.create();
            alert.setMessage("Do you want to go back to the previous screen?");
            alert.setNegativeButton("No", (dialogInterface, i) -> {
            });
            alert.setPositiveButton("Yes", (dialogInterface, i) -> finish());
            alert.show();
        });
    }

    private void init() {
        textViewTotalMoney = findViewById(R.id.textViewTotalMoney);
        textViewPaymentInCash = findViewById(R.id.textViewPaymentInCash);
        textViewPaymentByCard = findViewById(R.id.textViewPaymentByCard);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextNote = findViewById(R.id.editTextNote);
        buttonGetAddress = findViewById(R.id.buttonGetYourAddress);
        buttonPayment = findViewById(R.id.buttonPayment);
        buttonBackPayment = findViewById(R.id.buttonBackPayment);
        progressBarLoading = findViewById(R.id.progress_bar_loading_address);
        editTextPhone = findViewById(R.id.editTextPhone);
    }
}