package com.lengochuy.dmt.appbandoanonl.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lengochuy.dmt.appbandoanonl.Activity.OrderActivity;
import com.lengochuy.dmt.appbandoanonl.Object.FoodSelling;
import com.lengochuy.dmt.appbandoanonl.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SellingFoodAdapter extends RecyclerView.Adapter<SellingFoodAdapter.SellingFoodViewHolder>{
    public static List <FoodSelling> foodSelling;
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;

    public SellingFoodAdapter(List<FoodSelling> foodSelling, Context mContext) {
        SellingFoodAdapter.foodSelling = foodSelling;
        SellingFoodAdapter.mContext = mContext;
    }

    @NonNull
    @Override
    public SellingFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(mContext).inflate(R.layout.layout_item_sellingfood, parent, false);
        return new SellingFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SellingFoodViewHolder holder, int position) {
        FoodSelling foodSelling1 = foodSelling.get(position);
        if (foodSelling1 == null) return;
        Picasso.with(mContext).
                load(foodSelling1.getResourceID())
                .placeholder(R.drawable.camera)
                .error(R.drawable.error)
                .into(holder.imageView);

        holder.textViewName.setMaxLines(1);
        holder.textViewName.setEllipsize(TextUtils.TruncateAt.END);
        holder.textViewName.setText(foodSelling1.getNameRestaurant());

        holder.textViewAddress.setMaxLines(1);
        holder.textViewAddress.setEllipsize(TextUtils.TruncateAt.END);
        holder.textViewAddress.setText(foodSelling1.getAddress());
     }

    @Override
    public int getItemCount() {
        return foodSelling.size();
    }

    public static class SellingFoodViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewName, textViewAddress;
        public SellingFoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView           = itemView.findViewById(R.id.imageViewSellingFood);
            textViewName        = itemView.findViewById(R.id.textViewNameFoodSelling);
            textViewAddress     = itemView.findViewById(R.id.textViewAddressFoodSelling);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, OrderActivity.class);
                intent.putExtra("dataSellingFood",foodSelling.get(getLayoutPosition()));
                mContext.startActivity(intent);
            });
        }
    }
}
