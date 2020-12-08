package com.lengochuy.dmt.appbandoanonl.Fragment.ViewOrderFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lengochuy.dmt.appbandoanonl.Activity.LoginActivity;
import com.lengochuy.dmt.appbandoanonl.Adapter.OrderFragment.OrderHistoryAdapter;
import com.lengochuy.dmt.appbandoanonl.Linkserver.Link;
import com.lengochuy.dmt.appbandoanonl.Object.OrderHistory;
import com.lengochuy.dmt.appbandoanonl.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SecondFragment extends Fragment {
    RecyclerView recyclerViewHistory;
    List <OrderHistory> orderHistoryList;
    OrderHistoryAdapter historyAdapter;
    View view;
    ImageView imageView;
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_second, container, false);

        init();

        return view;
    }

    private void init() {
        textView            = view.findViewById(R.id.textViewBoxHistory);
        imageView           = view.findViewById(R.id.imageBoxHistory);
        recyclerViewHistory = view.findViewById(R.id.recyclerViewHistoryOrder);
        orderHistoryList    = new ArrayList<>();
        getDataListHistory();
        historyAdapter      = new OrderHistoryAdapter(orderHistoryList,view.getContext());
        recyclerViewHistory.setAdapter(historyAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerViewHistory.setHasFixedSize(true);
        recyclerViewHistory.setLayoutManager(linearLayoutManager);
//        if (orderHistoryList.size() == 0){
//            recyclerViewHistory.setVisibility(View.GONE);
//            imageView.setVisibility(View.VISIBLE);
//            textView.setVisibility(View.VISIBLE);
//        }
    }

    private void getDataListHistory() {
        String url = Link.listOrderHistory;
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, response -> {
            if (response != null){
                String hinhanh,tenmonan,gia,ngaygiao,tennguoidung;
                String diachi,sodienthoai;
                for (int i = 0 ; i < response.length() ; i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        tennguoidung    = jsonObject.getString("tennguoidung");
                        tennguoidung    = tennguoidung.trim();
                        String userName = LoginActivity.userNameMain.trim();
                        if (tennguoidung.equals(userName)) {
                            hinhanh = jsonObject.getString("hinhanhloaisp");
                            tenmonan = jsonObject.getString("tensp");
                            gia = jsonObject.getString("gia");
                            ngaygiao = jsonObject.getString("ngaygiaodoan");
                            diachi  = jsonObject.getString("diachi");
                            sodienthoai = jsonObject.getString("sodienthoai");
                            orderHistoryList.add(new OrderHistory(hinhanh, tenmonan, gia, ngaygiao,diachi,sodienthoai));
                            historyAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, error -> Toast.makeText(view.getContext(), error.toString(), Toast.LENGTH_SHORT).show());
        requestQueue.add(jsonArrayRequest);
    }
}