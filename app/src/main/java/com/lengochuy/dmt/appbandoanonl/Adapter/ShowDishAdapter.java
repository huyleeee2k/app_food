package com.lengochuy.dmt.appbandoanonl.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lengochuy.dmt.appbandoanonl.Activity.LoginActivity;
import com.lengochuy.dmt.appbandoanonl.Activity.MainActivity;
import com.lengochuy.dmt.appbandoanonl.Activity.OrderActivity;
import com.lengochuy.dmt.appbandoanonl.Object.ShowDish;
import com.lengochuy.dmt.appbandoanonl.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShowDishAdapter extends RecyclerView.Adapter<ShowDishAdapter.ShowDishViewHolder> implements Filterable {
    public static List<ShowDish> dishList;
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;
    public static List <ShowDish> showDishList;

    public ShowDishAdapter(List<ShowDish> dishList, Context mContext) {
        ShowDishAdapter.dishList = dishList;
        ShowDishAdapter.mContext = mContext;
        ShowDishAdapter.showDishList = dishList;
    }

    @NonNull
    @Override
    public ShowDishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_showdish,parent,false);
        return new ShowDishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowDishViewHolder holder, int position) {
        ShowDish showDish = showDishList.get(position);
        Picasso.with(mContext)
                .load(showDish.getResourceID())
                .placeholder(R.drawable.camera)
                .error(R.drawable.error)
                .into(holder.imageView);
        holder.textViewNameRTR.setEllipsize(TextUtils.TruncateAt.END);
        holder.textViewNameRTR.setMaxLines(1);
        holder.textViewNameFood.setText(showDish.getNameRestaurant());
        holder.textViewNameFood.setEllipsize(TextUtils.TruncateAt.END);
        holder.textViewNameRTR.setMaxLines(1);
        holder.textViewNameFood.setText(showDish.getNameFood());
        holder.textViewPrice.setText(showDish.getPrice());
    }

    @Override
    public int getItemCount() {
        return showDishList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String character = charSequence.toString();
                if (character.isEmpty()){
                    showDishList    = dishList;
                }else{
                    List <ShowDish> list = new ArrayList<>();
                    for (ShowDish showDish : dishList){
                        if (showDish.getNameFood().toLowerCase().contains(character.toLowerCase())){
                            list.add(showDish);
                        }
                    }

                    showDishList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = showDishList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                showDishList = (ArrayList<ShowDish>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ShowDishViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewNameRTR, textViewNameFood, textViewPrice;
        ImageView imageViewLove;
        public ShowDishViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView   = itemView.findViewById(R.id.imageShowDish);
            textViewNameRTR = itemView.findViewById(R.id.textViewNameRestaurantShowDish);
            textViewNameFood = itemView.findViewById(R.id.textViewNameFoodShowDish);
            textViewPrice   = itemView.findViewById(R.id.textViewPriceShowDish);
            imageViewLove   = itemView.findViewById(R.id.imageViewLove);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(mContext,OrderActivity.class);
                intent.putExtra("showdish",showDishList.get(getLayoutPosition()));
                mContext.startActivity(intent);
            });

//            imageView.setOnClickListener(view -> {
//                Intent intent = new Intent(mContext, OrderActivity.class);
//                intent.putExtra("showdish",dishList.get(getLayoutPosition()));
//                itemView.getContext().startActivity(intent);
//            });

            final boolean[] check = {true};
            imageViewLove.setOnClickListener(view -> {
                if (!check[0]){
                    imageViewLove.setImageResource(R.drawable.heart);
                    check[0] = true;
                }else{
                    //Insert database OrderLove
                    MainActivity.database.QueryData("insert into OrderLove1 values(null,'"+ LoginActivity.userNameMain.trim() +"','"
                            +dishList.get(getAdapterPosition()).getResourceID()+"'," +
                            "'"+textViewNameFood.getText().toString().trim()+"'," +
                            "'"+ dishList.get(getAdapterPosition()).getPrice().trim()+"')");
                    Toast.makeText(itemView.getContext(), "Added the item you just selected " +
                            "to the list", Toast.LENGTH_SHORT).show();
                    imageViewLove.setImageResource(R.drawable.heart_1);
                    check[0] = false;
                }
            });
        }

    }
}
