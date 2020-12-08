package com.lengochuy.dmt.appbandoanonl.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lengochuy.dmt.appbandoanonl.Activity.OrderActivity;
import com.lengochuy.dmt.appbandoanonl.Object.OtherFood;
import com.lengochuy.dmt.appbandoanonl.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OtherFoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    int TYPE_OTHER_FOOD = 1;
    int TYPE_LOADING    = 2;
    private boolean isLoading;
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;
    public static List <OtherFood> otherFoodList;

    public OtherFoodAdapter(Context mContext, List<OtherFood> otherFoodList) {
        OtherFoodAdapter.mContext = mContext;
        OtherFoodAdapter.otherFoodList = otherFoodList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (otherFoodList != null && position != otherFoodList.size() - 1 && isLoading){
            return TYPE_LOADING;
        }
        return TYPE_OTHER_FOOD;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (TYPE_OTHER_FOOD == viewType){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout_otherfood, parent, false);
            return new OtherFoodViewHolder(view);
        }else{
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_loading_otherfood, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_OTHER_FOOD){
            OtherFood otherFood = otherFoodList.get(position);
            OtherFoodViewHolder viewHolder = (OtherFoodViewHolder) holder;
            Picasso.with(mContext)
                    .load(otherFood.getResourceID())
                    .placeholder(R.drawable.camera)
                    .error(R.drawable.error)
                    .into(viewHolder.imageViewFood);
            viewHolder.textViewNameRestaurant.setText(otherFood.getNameRestaurant());
            viewHolder.textViewNameFood.setText(otherFood.getNameFood());
            viewHolder.textViewPrice.setText(otherFood.getPrice());
        }
    }

    @Override
    public int getItemCount() {
        return otherFoodList.size();
    }

    public static class OtherFoodViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewFood;
        TextView textViewNameRestaurant;
        TextView textViewNameFood;
        TextView textViewPrice;

        public OtherFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewFood          = itemView.findViewById(R.id.imageOtherFood);
            textViewNameRestaurant = itemView.findViewById(R.id.textViewNameRestaurantOtherFood);
            textViewNameFood       = itemView.findViewById(R.id.textViewNameOtherFood);
            textViewPrice          = itemView.findViewById(R.id.textViewPriceOtherFood);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, OrderActivity.class);
                intent.putExtra("dataOtherFood",otherFoodList.get(getLayoutPosition()));
                mContext.startActivity(intent);
            });

        }
    }
    public static class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_loading_other_food);
        }
    }

    public void removeFooterLoading(){
        isLoading = false;
        int position = otherFoodList.size() - 1;
        OtherFood otherFood = otherFoodList.get(position);
        if (otherFood != null){
            otherFoodList.remove(position);
            notifyItemRemoved(position);
        }
    }
}
