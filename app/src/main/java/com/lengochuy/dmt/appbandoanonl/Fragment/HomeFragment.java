package com.lengochuy.dmt.appbandoanonl.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lengochuy.dmt.appbandoanonl.Adapter.MainFoodAdapter;
import com.lengochuy.dmt.appbandoanonl.Adapter.OtherFoodAdapter;
import com.lengochuy.dmt.appbandoanonl.Adapter.SaleFoodAdapter;
import com.lengochuy.dmt.appbandoanonl.Adapter.SellingFoodAdapter;
import com.lengochuy.dmt.appbandoanonl.Linkserver.Link;
import com.lengochuy.dmt.appbandoanonl.Object.FoodSale;
import com.lengochuy.dmt.appbandoanonl.Object.FoodSelling;
import com.lengochuy.dmt.appbandoanonl.Object.MainFood;
import com.lengochuy.dmt.appbandoanonl.Object.OtherFood;
import com.lengochuy.dmt.appbandoanonl.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    View view;
    ViewFlipper viewFlipper;

    RecyclerView recyclerViewMainFood;
    MainFoodAdapter mainFoodAdapter;

    RecyclerView recyclerViewSaleFood;
    SaleFoodAdapter saleFoodAdapter;
    List <FoodSale> foodSaleList;

    RecyclerView recyclerViewFoodSelling;
    SellingFoodAdapter sellingFoodAdapter;
    List <FoodSelling> foodSellingList;

    RecyclerView recyclerViewOtherFood;
    OtherFoodAdapter otherFoodAdapter;
    List <OtherFood> otherFoodList;

//    private boolean isLoading;
//    private boolean isLastPage;
//    private final int totalPage = 5;
//    private final int currentPage = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initViewFlipper();
        initMainFood();
        initSaleFood();
        initFoodSelling();
        initOtherFood();
        return view;
    }

    private void initOtherFood() {
        recyclerViewOtherFood   = view.findViewById(R.id.recyclerViewOtherFood);
        otherFoodList           = new ArrayList<>();
        getDataOtherList();
        otherFoodAdapter        = new OtherFoodAdapter(view.getContext(),otherFoodList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerViewOtherFood.setLayoutManager(linearLayoutManager);
        recyclerViewOtherFood.setAdapter(otherFoodAdapter);
        recyclerViewOtherFood.setNestedScrollingEnabled(false);
        recyclerViewOtherFood.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        recyclerViewOtherFood.addItemDecoration(itemDecoration);
//        recyclerViewOtherFood.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
//            @Override
//            public void loadMoreItems() {
//                isLoading = true;
//                currentPage ++;
//                loadNextPage();
//            }
//
//            @Override
//            public boolean isLoading() {
//                return isLoading;
//            }
//
//            @Override
//            public boolean isLastPage() {
//                return isLastPage;
//            }
//        });
//
    }

    private void getDataOtherList() {
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        String url = Link.listFoodOther;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, response -> {
            int id;
            String tennhahang;
            String tenmonan;
            String hinhanh;
            String gia;
            if (response != null){
                for (int i = 0 ; i < response.length() ; i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        id = jsonObject.getInt("id");
                        hinhanh = jsonObject.getString("hinhanhloaisp");
                        tennhahang = jsonObject.getString("tennhahang");
                        tenmonan    = jsonObject.getString("tensp");
                        gia         = jsonObject.getString("gia");
                        otherFoodList.add(new OtherFood(id,hinhanh,tennhahang,tenmonan,gia));
                        otherFoodAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, error -> Toast.makeText(view.getContext(), error.toString(), Toast.LENGTH_SHORT).show());
        requestQueue.add(jsonArrayRequest);
    }
//
//    private void loadNextPage() {
//        new Handler().postDelayed(() -> {
//            List <OtherFood> list = getOtherFoodList();
//            otherFoodAdapter.removeFooterLoading();
//            otherFoodList.addAll(list);
//            otherFoodAdapter.notifyDataSetChanged();
//            isLoading = false;
//
//            if (currentPage < totalPage){
//                otherFoodAdapter.addFooterLoading();
//            }else{
//                isLastPage = true;
//            }
//        },2000);
//    }
//
//    private void setFirstData(){
//        otherFoodList = getOtherFoodList();
//        otherFoodAdapter.notifyDataSetChanged();
//        if (currentPage < totalPage){
//            otherFoodAdapter.addFooterLoading();
//        }else{
//            isLastPage = true;
//        }
//    }
//
//    private List<OtherFood> getOtherFoodList(){
//        List <OtherFood> list = new ArrayList<>();
//        for (int i = 1 ; i <= 10 ; i++){
//            list.add(new OtherFood("https://pbs.twimg.com/profile_images/758084549821730820/_HYHtD8F.jpg","","","Hello " + i));
//        }
//
//        return list;
//    }

    private void initFoodSelling() {
        recyclerViewFoodSelling = view.findViewById(R.id.recyclerViewSellingFood);
        foodSellingList = new ArrayList<>();
        getDataSellingData();
        sellingFoodAdapter      = new SellingFoodAdapter(foodSellingList, view.getContext());
        recyclerViewFoodSelling.setAdapter(sellingFoodAdapter);
        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewFoodSelling.setLayoutManager(linearLayoutManager);
        recyclerViewFoodSelling.setNestedScrollingEnabled(false);
        recyclerViewFoodSelling.setHasFixedSize(true);

    }

    private void getDataSellingData() {
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        String url = Link.listFoodSelling;
        JsonArrayRequest jsonArrayRequest   = new JsonArrayRequest(url, response -> {
            int id;
            String hinhanh;
            String tennhahang;
            String diachi;
            if (response != null){
                for (int i = 0 ; i < response.length() ; i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        id  = jsonObject.getInt("id");
                        hinhanh = jsonObject.getString("hinhanh");
                        tennhahang  = jsonObject.getString("tennhahang");
                        diachi      = jsonObject.getString("diachi");
                        foodSellingList.add(new FoodSelling(id,hinhanh,tennhahang,diachi));
                        sellingFoodAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, error -> Toast.makeText(view.getContext(), error.toString(), Toast.LENGTH_SHORT).show());
        requestQueue.add(jsonArrayRequest);
    }

    private void initSaleFood() {
        recyclerViewSaleFood    = view.findViewById(R.id.recyclerViewSale);
        foodSaleList            = new ArrayList<>();
        getDataSaleList();
        saleFoodAdapter         = new SaleFoodAdapter(foodSaleList,view.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        recyclerViewSaleFood.addItemDecoration(dividerItemDecoration);
        recyclerViewSaleFood.setNestedScrollingEnabled(false);
        recyclerViewSaleFood.setHasFixedSize(true);
        recyclerViewSaleFood.setAdapter(saleFoodAdapter);
        recyclerViewSaleFood.setLayoutManager(linearLayoutManager);


    }

    private void getDataSaleList(){
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        String url = Link.listFoodSale;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, response -> {
            int id;
            String hinhanh,tennhahang,tensp,gia,sale,freeship;
            if (response != null){
                for (int i = 0 ; i < response.length() ; i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        id  = jsonObject.getInt("id");
                        hinhanh = jsonObject.getString("hinhanhloaisp");
                        tennhahang = jsonObject.getString("tennhahang");
                        tensp   = jsonObject.getString("tensp");
                        gia     = jsonObject.getString("gia");
                        sale    = jsonObject.getString("sale");
                        freeship= jsonObject.getString("freeship");
                        foodSaleList.add(new FoodSale(id,hinhanh,tennhahang,tensp,gia,sale,freeship));
                        saleFoodAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, error -> Toast.makeText(view.getContext(),error.toString(),Toast.LENGTH_LONG).show());
        requestQueue.add(jsonArrayRequest);
    }

    private void initMainFood() {
        recyclerViewMainFood    = view.findViewById(R.id.recyclerViewMainFood);
        mainFoodAdapter         = new MainFoodAdapter(getListMainFood());
        recyclerViewMainFood.setAdapter(mainFoodAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 4);
        recyclerViewMainFood.setLayoutManager(gridLayoutManager);
        recyclerViewMainFood.setHasFixedSize(true);
        recyclerViewMainFood.setNestedScrollingEnabled(false);
    }

    private List<MainFood> getListMainFood() {
        List <MainFood> list = new ArrayList<>();
        list.add(new MainFood(R.drawable.rice,"Rice"));
        list.add(new MainFood(R.drawable.hamburger,"Hamburger"));
        list.add(new MainFood(R.drawable.hotpot,"Hot pot"));
        list.add(new MainFood(R.drawable.noodle,"Noodle"));
        list.add(new MainFood(R.drawable.fastfood,"Fast food"));
        list.add(new MainFood(R.drawable.cup,"Coffee"));
        list.add(new MainFood(R.drawable.drink,"Fruit juice"));
        list.add(new MainFood(R.drawable.cake,"Cake"));

        return list;
    }

    private void initViewFlipper() {
        viewFlipper = view.findViewById(R.id.viewFlipper);
        ArrayList <String> listViewFlipper = new ArrayList<>();
        listViewFlipper.add("https://cdn.pixabay.com/photo/2018/06/27/20/24/goulash-3502510_960_720.jpg");
        listViewFlipper.add("https://cdn.pixabay.com/photo/2015/06/30/22/51/steak-826961_960_720.jpg");
        listViewFlipper.add("https://cdn.pixabay.com/photo/2016/04/25/19/56/eat-1352880_960_720.jpg");
        listViewFlipper.add("https://cdn.pixabay.com/photo/2017/06/11/09/49/halloumi-2391787_960_720.jpg");
        listViewFlipper.add("https://cdn.pixabay.com/photo/2017/09/10/15/03/lobster-2735863_960_720.jpg");

        for (int i = 0; i < listViewFlipper.size(); i++) {
            ImageView imageView = new ImageView(view.getContext());
            Picasso.with(view.getContext())
                    .load(listViewFlipper.get(i))
                    .into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }



        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_out_rigth);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }
}