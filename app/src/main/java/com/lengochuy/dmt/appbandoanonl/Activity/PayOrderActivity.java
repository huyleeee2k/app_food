package com.lengochuy.dmt.appbandoanonl.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.lengochuy.dmt.appbandoanonl.Adapter.PayOrderAdapter;
import com.lengochuy.dmt.appbandoanonl.R;

public class PayOrderActivity extends AppCompatActivity {
    ListView listViewPayOrder;
    @SuppressLint("StaticFieldLeak")
    static PayOrderAdapter payOrderAdapter;
    @SuppressLint("StaticFieldLeak")
    public static TextView textViewShowCart;
    Button buttonPay;
    Button buttonContinueShop;
    @SuppressLint("StaticFieldLeak")
    public static TextView txtMoneyFoodCost, txtMoneyShippingFee, txtTotalMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_order);

        init();
        orderEventHandling();
        checkCart();
        eventClickOnButtonPay();
    }

    private void eventClickOnButtonPay() {
        buttonPay.setOnClickListener(view -> {
            int amount = Integer.parseInt(PayOrderActivity.txtMoneyFoodCost.getText().toString().split(" ")[0]);
            if (amount == 0) {
                Toast.makeText(this, "The carts is empty", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(PayOrderActivity.this,IntentScreenPayActivity.class));
            }
        });

    }

    @SuppressLint("SetTextI18n")
    public void checkCart() {
        if (MainActivity.newOrderArrayList.isEmpty()) {
            textViewShowCart.setText("Cart is empty.");
            txtMoneyFoodCost.setText("0 USD");
            txtMoneyShippingFee.setText("0 USD");
            txtTotalMoney.setText("0 USD");
            payOrderAdapter.notifyDataSetChanged();
            listViewPayOrder.setVisibility(View.INVISIBLE);
        } else {
            payOrderAdapter.notifyDataSetChanged();
            textViewShowCart.setVisibility(View.GONE);
            listViewPayOrder.setVisibility(View.VISIBLE);
        }
    }

    private void init() {
        textViewShowCart = findViewById(R.id.textViewShowCart);
        listViewPayOrder = findViewById(R.id.listViewPayOrder);
        buttonPay = findViewById(R.id.buttonPay);
        payOrderAdapter = new PayOrderAdapter(MainActivity.newOrderArrayList, this);
        listViewPayOrder.setAdapter(payOrderAdapter);
        buttonContinueShop = findViewById(R.id.buttonContinueToShop);
        buttonContinueShop.setOnClickListener(view -> startActivity(new Intent(PayOrderActivity.this, MainActivity.class)));
        txtMoneyFoodCost = findViewById(R.id.moneyFoodCost);
        txtMoneyShippingFee = findViewById(R.id.moneyShippingFee);
        txtTotalMoney = findViewById(R.id.totalMoney);
    }

    @SuppressLint("SetTextI18n")
    public static void orderEventHandling() {
        int total = 0;
        boolean isTrue = false;
        for (int i = 0; i < MainActivity.newOrderArrayList.size(); i++) {
            if (MainActivity.newOrderArrayList.get(i).getNameFood().contains("*")) {
                isTrue = true;
            }
            String price = MainActivity.newOrderArrayList.get(i).getPrice();
            String[] numberPrice = price.split(" ");
            int money = Integer.parseInt(numberPrice[1]);
            total += money;
        }
        txtMoneyFoodCost.setText(total + " USD");
        //Set free ship with order have total > 30 and is a free ship.
        if (total > 30 && isTrue) {
            txtMoneyShippingFee.setText("0 USD");
        } else {
            txtMoneyShippingFee.setText("5 USD");
        }

        int totalFee = Integer.parseInt(txtMoneyShippingFee.getText().toString().substring(0, 1));
        total += totalFee;
        txtTotalMoney.setText(total + " USD");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete_all, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_delete_all) {
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
            //:( dell hieu sao no ko chay duoc :(
            //Bo qua phan nay vay :)
        }
        return super.onOptionsItemSelected(item);
    }
}