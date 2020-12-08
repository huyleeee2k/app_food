package com.lengochuy.dmt.appbandoanonl.Fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lengochuy.dmt.appbandoanonl.Activity.LoginActivity;
import com.lengochuy.dmt.appbandoanonl.Activity.MainActivity;
import com.lengochuy.dmt.appbandoanonl.Adapter.OrderLoveAdapter;
import com.lengochuy.dmt.appbandoanonl.Object.OrderLove;
import com.lengochuy.dmt.appbandoanonl.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {
    RecyclerView recyclerView;
    @SuppressLint("StaticFieldLeak")
    public static OrderLoveAdapter orderLoveAdapter;
    static List <OrderLove> orderLoveList;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  = inflater.inflate(R.layout.fragment_notification, container, false);

        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {
        recyclerView        = view.findViewById(R.id.recyclerViewFavorite);
        orderLoveList       = new ArrayList<>();
        orderLoveAdapter    = new OrderLoveAdapter(orderLoveList,view.getContext());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(orderLoveAdapter);
        getDataFromSQLite();
        orderLoveAdapter.notifyDataSetChanged();
    }

    public static void getDataFromSQLite() {
        orderLoveList.clear();
        Cursor cursor = MainActivity.database.GetData("select * from OrderLove1");
        assert cursor != null;
        while (cursor.moveToNext()){
            if (cursor.getString(1).equals(LoginActivity.userNameMain.trim())){
                int id = cursor.getInt(0);
                String username = cursor.getString(1);
                String photo = cursor.getString(2);
                String nameFood = cursor.getString(3);
                String price    = cursor.getColumnName(4);
                orderLoveList.add(new OrderLove(id,username,photo,nameFood,price));
            }
        }
        orderLoveAdapter.notifyDataSetChanged();
    }
}