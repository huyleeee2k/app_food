package com.lengochuy.dmt.appbandoanonl.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lengochuy.dmt.appbandoanonl.Activity.OrderActivity;
import com.lengochuy.dmt.appbandoanonl.Fragment.OrderFragment;
import com.lengochuy.dmt.appbandoanonl.Object.FoodSale;
import com.lengochuy.dmt.appbandoanonl.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SaleFoodAdapter extends RecyclerView.Adapter<SaleFoodAdapter.SaleFoodViewHolder>{
    static List <FoodSale> foodSaleList;
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;

    public SaleFoodAdapter(List<FoodSale> foodSaleList, Context mContext) {
        SaleFoodAdapter.foodSaleList = foodSaleList;
        SaleFoodAdapter.mContext = mContext;
    }

    @NonNull
    @Override
    public SaleFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_sale, parent, false);
        return new SaleFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleFoodViewHolder holder, int position) {
        FoodSale foodSale = foodSaleList.get(position);
        Picasso.with(mContext)
                .load(foodSale.getResourceID())
                .placeholder(R.drawable.camera)
                .error(R.drawable.error)
                .into(holder.imageViewFood);
        holder.tvtNameRestaurant.setText(foodSale.getNameRestaurant());
        holder.tvtNameFood.setText(foodSale.getNameFood());
        holder.tvtPrice.setText(foodSale.getPriceFood());
        holder.tvtSale.setText(foodSale.getSale());
        holder.tvtFreeShip.setText(foodSale.getFreeShip());
    }

    @Override
    public int getItemCount() {
        return foodSaleList.size();
    }

    public static class SaleFoodViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewFood;
        TextView tvtNameRestaurant, tvtNameFood, tvtPrice, tvtSale, tvtFreeShip;
        public SaleFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewFood       = itemView.findViewById(R.id.imageSaleFood);
            tvtNameRestaurant   = itemView.findViewById(R.id.textViewNameRestaurantSale);
            tvtNameFood         = itemView.findViewById(R.id.textViewNameFoodSale);
            tvtPrice            = itemView.findViewById(R.id.textViewPriceSale);
            tvtSale             = itemView.findViewById(R.id.textViewSale);
            tvtFreeShip         = itemView.findViewById(R.id.textViewFreeShip);


            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(mContext,OrderActivity.class);
                intent.putExtra("dataSaleFood",foodSaleList.get(getLayoutPosition()));
                mContext.startActivity(intent);
            });
        }
    }
}
