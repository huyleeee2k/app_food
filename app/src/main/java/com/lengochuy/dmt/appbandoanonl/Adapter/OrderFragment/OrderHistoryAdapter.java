package com.lengochuy.dmt.appbandoanonl.Adapter.OrderFragment;

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

import com.lengochuy.dmt.appbandoanonl.Activity.SeeDetailsActivity;
import com.lengochuy.dmt.appbandoanonl.Object.OrderHistory;
import com.lengochuy.dmt.appbandoanonl.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder>{
    static List <OrderHistory> orderHistoryList;
    Context mContext;

    public OrderHistoryAdapter(List<OrderHistory> orderHistoryList, Context mContext) {
        OrderHistoryAdapter.orderHistoryList = orderHistoryList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_history_order, parent, false);
        return new OrderHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position) {
        OrderHistory history = orderHistoryList.get(position);
        Picasso.with(mContext)
                .load(history.getResourceID())
                .error(R.drawable.error)
                .placeholder(R.drawable.camera)
                .into(holder.imageView);
        holder.textViewNameFood.setEllipsize(TextUtils.TruncateAt.END);
        holder.textViewNameFood.setMaxLines(3);
        holder.textViewNameFood.setText(history.getNameFood());

        holder.textViewPrice.setText(history.getPrice());
        holder.textViewDate.setText(history.getDate());
    }

    @Override
    public int getItemCount() {
        return orderHistoryList.size();
    }

    public static class OrderHistoryViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewNameFood,textViewPrice,textViewDate;
        TextView textViewSeeDetails;
        public OrderHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView           = itemView.findViewById(R.id.imageLayoutItemHistory);
            textViewNameFood    = itemView.findViewById(R.id.textViewNameFoodOrderHistory);
            textViewPrice       = itemView.findViewById(R.id.textViewPriceOrderHistory);
            textViewDate        = itemView.findViewById(R.id.textViewOrderDateOrderHistory);
            textViewSeeDetails  = itemView.findViewById(R.id.textViewSeeDetailsOrderHistory);

            textViewSeeDetails.setOnClickListener(view -> {
                Intent intent = new Intent(itemView.getContext(), SeeDetailsActivity.class);
                intent.putExtra("history", orderHistoryList.get(getLayoutPosition()));
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
