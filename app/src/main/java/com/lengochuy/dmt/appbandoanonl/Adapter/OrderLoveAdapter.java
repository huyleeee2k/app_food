package com.lengochuy.dmt.appbandoanonl.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lengochuy.dmt.appbandoanonl.Activity.MainActivity;
import com.lengochuy.dmt.appbandoanonl.Fragment.NotificationFragment;
import com.lengochuy.dmt.appbandoanonl.Object.OrderLove;
import com.lengochuy.dmt.appbandoanonl.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderLoveAdapter extends RecyclerView.Adapter<OrderLoveAdapter.OrderLoveViewHolder>{
    static List <OrderLove> orderLoveList;
    @SuppressLint("StaticFieldLeak")
    static Context mContext;

    public OrderLoveAdapter(List<OrderLove> orderLoveList,Context mContext) {
        OrderLoveAdapter.orderLoveList = orderLoveList;
        OrderLoveAdapter.mContext   = mContext;
    }

    @NonNull
    @Override
    public OrderLoveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(mContext).inflate(R.layout.layout_item_list_favorite,parent,false);
        return new OrderLoveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderLoveViewHolder holder, int position) {
        OrderLove orderLove = orderLoveList.get(position);
        Picasso.with(mContext)
                .load(orderLove.getResourceId())
                .placeholder(R.drawable.camera)
                .error(R.drawable.error)
                .into(holder.imageView);

        holder.textViewNameFood.setText(orderLove.getNameFood());
        holder.textViewPrice.setText(orderLove.getPrice());
    }

    @Override
    public int getItemCount() {
        return orderLoveList.size();
    }

    public static class OrderLoveViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewNameFood, textViewPrice, textViewDelete;
        public OrderLoveViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView           = itemView.findViewById(R.id.imageViewFavorite);
            textViewNameFood    = itemView.findViewById(R.id.textViewNameFoodFavorite);
            textViewPrice       = itemView.findViewById(R.id.textViewPriceFavorite);
            textViewDelete      = itemView.findViewById(R.id.textViewDeleteFavorite);

            textViewDelete.setOnClickListener(view -> {
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.create();

                alert.setMessage("Do you want to delete this dish?");
                alert.setPositiveButton("No", (dialogInterface, i) -> {});
                alert.setNegativeButton("Yes", (dialogInterface, i) -> {
                    MainActivity.database.QueryData("delete from OrderLove1 where id = '"+orderLoveList.get(getAdapterPosition()).getId()+"'");
                    NotificationFragment.getDataFromSQLite();
                });

                alert.show();
            });
        }
    }
}
