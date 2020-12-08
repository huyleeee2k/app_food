package com.lengochuy.dmt.appbandoanonl.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.lengochuy.dmt.appbandoanonl.Adapter.ShowDishAdapter;
import com.lengochuy.dmt.appbandoanonl.Object.ShowDish;
import com.lengochuy.dmt.appbandoanonl.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ShowDishActivity extends AppCompatActivity {
    NestedScrollView nestedScrollView;
    ShowDishAdapter dishAdapter;
    List<ShowDish> showDishList = new ArrayList<>();
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Toolbar toolbar;
    int page = 1, limit = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dish);

        init();
        actionBar();
    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
    }

    private void init() {
        toolbar = findViewById(R.id.toolBarShowDish);
        progressBar = findViewById(R.id.progressBar_ShowDish);
        recyclerView = findViewById(R.id.listViewShowDish);
        nestedScrollView = findViewById(R.id.nestScrollView);

        dishAdapter = new ShowDishAdapter(showDishList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dishAdapter);

        getData(page, limit);
        nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){
                page++;
                progressBar.setVisibility(View.VISIBLE);
                getData(page,limit);
            }
        });
    }

    private void getData(int page, int limit) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://laptrinhandroidcunglehuy.000webhostapp.com/menu.php/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        MainInterface mainInterface = retrofit.create(MainInterface.class);

        Call<String> call = mainInterface.STRING_CALL(page, limit);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call,@NonNull Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    progressBar.setVisibility(View.GONE);
                    try {
                        JSONArray jsonArray = new JSONArray(response.body());
                        parseResult(jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call,@NonNull Throwable t) {

            }
        });
    }

    private void parseResult(JSONArray jsonArray) {
        Intent intent = getIntent();
        String nameFood = intent.getStringExtra("namefood");
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("loaidoan");
                if (nameFood.equals("Rice")){
                    if (name.equals("Rice")){
                        ShowDish showDish = new ShowDish();
                        showDish.setResourceID(jsonObject.getString("hinhanh"));
                        showDish.setNameRestaurant(jsonObject.getString("tennhahang"));
                        showDish.setNameFood(jsonObject.getString("tenmonan"));
                        showDish.setPrice(jsonObject.getString("gia"));
                        showDishList.add(showDish);
                    }
                }
                    if (nameFood.equals("Hamburger")){
                    if (name.equals("Hamburger")){
                        ShowDish showDish = new ShowDish();
                        showDish.setResourceID(jsonObject.getString("hinhanh"));
                        showDish.setNameRestaurant(jsonObject.getString("tennhahang"));
                        showDish.setNameFood(jsonObject.getString("tenmonan"));
                        showDish.setPrice(jsonObject.getString("gia"));
                        showDishList.add(showDish);
                    }
                }
                if (nameFood.equals("Hot pot")){
                    if (name.equals("Hot pot")){
                        ShowDish showDish = new ShowDish();
                        showDish.setResourceID(jsonObject.getString("hinhanh"));
                        showDish.setNameRestaurant(jsonObject.getString("tennhahang"));
                        showDish.setNameFood(jsonObject.getString("tenmonan"));
                        showDish.setPrice(jsonObject.getString("gia"));
                        showDishList.add(showDish);
                    }
                }
                if (nameFood.equals("Noodle")){
                    if (name.equals("Noodle")){
                        ShowDish showDish = new ShowDish();
                        showDish.setResourceID(jsonObject.getString("hinhanh"));
                        showDish.setNameRestaurant(jsonObject.getString("tennhahang"));
                        showDish.setNameFood(jsonObject.getString("tenmonan"));
                        showDish.setPrice(jsonObject.getString("gia"));
                        showDishList.add(showDish);
                    }
                }
                if (nameFood.equals("Fast food")){
                    if (name.equals("Fast food")){
                        ShowDish showDish = new ShowDish();
                        showDish.setResourceID(jsonObject.getString("hinhanh"));
                        showDish.setNameRestaurant(jsonObject.getString("tennhahang"));
                        showDish.setNameFood(jsonObject.getString("tenmonan"));
                        showDish.setPrice(jsonObject.getString("gia"));
                        showDishList.add(showDish);
                    }
                }
                if (nameFood.equals("Coffee")){
                    if (name.equals("Coffee")){
                        ShowDish showDish = new ShowDish();
                        showDish.setResourceID(jsonObject.getString("hinhanh"));
                        showDish.setNameRestaurant(jsonObject.getString("tennhahang"));
                        showDish.setNameFood(jsonObject.getString("tenmonan"));
                        showDish.setPrice(jsonObject.getString("gia"));
                        showDishList.add(showDish);
                    }
                }
                if (nameFood.equals("Fruit juice")){
                    if (name.equals("Fruit juice")){
                        ShowDish showDish = new ShowDish();
                        showDish.setResourceID(jsonObject.getString("hinhanh"));
                        showDish.setNameRestaurant(jsonObject.getString("tennhahang"));
                        showDish.setNameFood(jsonObject.getString("tenmonan"));
                        showDish.setPrice(jsonObject.getString("gia"));
                        showDishList.add(showDish);
                    }
                }
                if (nameFood.equals("Cake")){
                    if (name.equals("Cake")){
                        ShowDish showDish = new ShowDish();
                        showDish.setResourceID(jsonObject.getString("hinhanh"));
                        showDish.setNameRestaurant(jsonObject.getString("tennhahang"));
                        showDish.setNameFood(jsonObject.getString("tenmonan"));
                        showDish.setPrice(jsonObject.getString("gia"));
                        showDishList.add(showDish);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            dishAdapter = new ShowDishAdapter(showDishList, this);
            recyclerView.setAdapter(dishAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                dishAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                dishAdapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}

