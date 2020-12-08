package com.lengochuy.dmt.appbandoanonl.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lengochuy.dmt.appbandoanonl.Adapter.OderDetailAdapter;
import com.lengochuy.dmt.appbandoanonl.Linkserver.Link;
import com.lengochuy.dmt.appbandoanonl.Object.FoodSale;
import com.lengochuy.dmt.appbandoanonl.Object.FoodSelling;
import com.lengochuy.dmt.appbandoanonl.Object.NewOrder;
import com.lengochuy.dmt.appbandoanonl.Object.OderDetails;
import com.lengochuy.dmt.appbandoanonl.Object.OtherFood;
import com.lengochuy.dmt.appbandoanonl.Object.ShowDish;
import com.lengochuy.dmt.appbandoanonl.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderActivity extends AppCompatActivity {
    AppCompatButton buttonPayOrder;
    TextView textViewNameFood;
    TextView textViewPrice;
    ImageButton imageButton;
    ImageView imageView;
    Toolbar toolbar;
    @SuppressLint("StaticFieldLeak")
    public static OderDetailAdapter oderDetailAdapter;
    public static List <OderDetails> oderDetailsList;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        init();
        actionBar();
        getDataHomeFragment();
        setEventOnclickButtonPayOrder();
    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
    }

    private void setEventOnclickButtonPayOrder() {
        buttonPayOrder.setOnClickListener(view -> startActivity(new Intent(this,PayOrderActivity.class)));
    }

    @SuppressLint("SetTextI18n")
    private void getDataHomeFragment() {
        FoodSale foodSale = (FoodSale) getIntent().getSerializableExtra("dataSaleFood");
        FoodSelling foodSelling = (FoodSelling) getIntent().getSerializableExtra("dataSellingFood");
        OtherFood otherFood = (OtherFood) getIntent().getSerializableExtra("dataOtherFood");
        ShowDish showDish   = (ShowDish) getIntent().getSerializableExtra("showdish");
        String resourceID;
        if (foodSale != null){
            Picasso.with(this)
                    .load(foodSale.getResourceID())
                    .placeholder(R.drawable.camera)
                    .error(R.drawable.error)
                    .into(imageView);
            resourceID = foodSale.getResourceID();
            textViewNameFood.setText(foodSale.getNameFood() + "*");
            textViewPrice.setText(foodSale.getPriceFood());
        }else if (foodSelling != null){
            Picasso.with(this)
                    .load(foodSelling.getResourceID())
                    .placeholder(R.drawable.camera)
                    .error(R.drawable.error)
                    .into(imageView);
            resourceID = foodSelling.getResourceID();
            textViewNameFood.setText(foodSelling.getNameRestaurant());
            textViewPrice.setText("Price: 10 USD");
        }else if (otherFood != null){
            Picasso.with(this)
                    .load(otherFood.getResourceID())
                    .placeholder(R.drawable.camera)
                    .error(R.drawable.error)
                    .into(imageView);
            resourceID = otherFood.getResourceID();
            textViewNameFood.setText(otherFood.getNameFood());
            textViewPrice.setText(otherFood.getPrice());
        }else{
            Picasso.with(this)
                    .load(showDish.getResourceID())
                    .placeholder(R.drawable.camera)
                    .error(R.drawable.error)
                    .into(imageView);
            resourceID = showDish.getResourceID();
            textViewNameFood.setText(showDish.getNameFood());
            textViewPrice.setText(showDish.getPrice());
        }

        String finalResourceID = resourceID;
        imageButton.setOnClickListener(
                view -> {MainActivity.newOrderArrayList
                .add(new NewOrder(finalResourceID,textViewNameFood.getText().toString()
                        ,textViewPrice.getText().toString(),1));
                    imageButton.setBackgroundResource(R.drawable.custom_button_cart_order_after);
                    imageButton.setEnabled(false);
                });



    }

    private void init() {
        toolbar         = findViewById(R.id.toolBarOrderDetails);
        imageView       = findViewById(R.id.image_orderDetails);
        textViewNameFood = findViewById(R.id.textViewNameFoodOrderDetails);
        textViewPrice  = findViewById(R.id.textViewPriceOrderDetails);
        imageButton    = findViewById(R.id.buttonOrderDetails);
        buttonPayOrder = findViewById(R.id.buttonPayOrder);
        oderDetailsList = new ArrayList<>();
        getDataOderDetails();
        oderDetailAdapter = new OderDetailAdapter(oderDetailsList,this);
        listView        = findViewById(R.id.listViewOtherFood);
        listView.setAdapter(oderDetailAdapter);

    }

    private void getDataOderDetails() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = Link.listFoodSale;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, response -> {
            String hinhanh;
            String tennhahang;
            String tenmonan;
            String gia;
            if (response != null){
                for (int i = 0 ; i < response.length() ; i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        hinhanh = jsonObject.getString("hinhanhloaisp");
                        tennhahang = jsonObject.getString("tennhahang");
                        tenmonan = jsonObject.getString("tensp");
                        gia     = jsonObject.getString("gia");
                        oderDetailsList.add(new OderDetails(hinhanh,tennhahang,tenmonan,gia));
                        oderDetailAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, error -> Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show());
        requestQueue.add(jsonArrayRequest);
    }

}