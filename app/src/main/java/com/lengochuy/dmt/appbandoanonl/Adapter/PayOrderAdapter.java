package com.lengochuy.dmt.appbandoanonl.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lengochuy.dmt.appbandoanonl.Activity.MainActivity;
import com.lengochuy.dmt.appbandoanonl.Activity.PayOrderActivity;
import com.lengochuy.dmt.appbandoanonl.Object.NewOrder;
import com.lengochuy.dmt.appbandoanonl.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PayOrderAdapter extends BaseAdapter {
    List <NewOrder> newOrderList;
    Context mContext;

    public PayOrderAdapter(List<NewOrder> newOrderList, Context mContext) {
        this.newOrderList = newOrderList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return newOrderList.size();
    }

    @Override
    public Object getItem(int i) {
        return newOrderList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder{
        ImageView imageView;
        TextView textViewNameFood;
        TextView textViewPrice;
        ImageButton buttonRemove;
        ImageButton buttonAdd;
        TextView textViewAmount;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder  = new ViewHolder();
            view    = LayoutInflater.from(mContext).inflate(R.layout.item_new_order, viewGroup, false);
            holder.imageView = view.findViewById(R.id.imageNewOrder);
            holder.textViewNameFood = view.findViewById(R.id.textViewNameFoodNewOrder);
            holder.textViewPrice    = view.findViewById(R.id.textViewPriceNewOrder);
            holder.buttonRemove     = view.findViewById(R.id.buttonRemove);
            holder.buttonAdd        = view.findViewById(R.id.buttonAdd);
            holder.textViewAmount   = view.findViewById(R.id.textViewAmount);
            view.setTag(holder);
        }else{
            holder  = (ViewHolder) view.getTag();
        }

        NewOrder newOrder = (NewOrder) getItem(i);
        Picasso.with(mContext)
                .load(newOrder.getResourceID())
                .placeholder(R.drawable.camera)
                .error(R.drawable.error)
                .into(holder.imageView);
        holder.textViewNameFood.setText(newOrder.getNameFood());
        holder.textViewPrice.setText(newOrder.getPrice());

        String []w = MainActivity.newOrderArrayList.get(i).getPrice().split(" ");
        int money  = Integer.parseInt(w[1]);
        String s = MainActivity.newOrderArrayList.get(i).getPrice();
        if (s.contains("(")){
            int amount22 = Integer.parseInt(s.substring(s.indexOf("(") + 1,s.indexOf("(") + 2));
            holder.textViewAmount.setText(String.valueOf(amount22));
        }else{
            holder.textViewAmount.setText(String.valueOf(1 ));
        }


        holder.buttonAdd.setOnClickListener(view1 -> {
            int amount = MainActivity.newOrderArrayList.get(i).getAmount() + 1;
            if(amount > 9){
                holder.buttonAdd.setEnabled(false);
                Toast.makeText(mContext, "You can only select up to 10 orders per product.",
                        Toast.LENGTH_SHORT).show();
            }else{
                holder.buttonAdd.setEnabled(true);
                MainActivity.newOrderArrayList.get(i).setAmount(amount);
                int total  = money * amount / (amount-1);
                MainActivity.newOrderArrayList.get(i).setPrice("Price: " + total + " USD(" + amount + ")");
                PayOrderActivity.orderEventHandling();
                String str = MainActivity.newOrderArrayList.get(i).getPrice();
                int amount2 = Integer.parseInt(str.substring(str.indexOf("(") + 1,str.indexOf("(") + 2));
                holder.textViewAmount.setText(String.valueOf(amount2));
                notifyDataSetChanged();
            }


        });


        holder.buttonRemove.setOnClickListener(view1 -> {
            int amount = MainActivity.newOrderArrayList.get(i).getAmount() - 1;
            if (amount == 0){
                holder.buttonRemove.setEnabled(false);
                MainActivity.newOrderArrayList.remove(i);
            }else{
                holder.buttonRemove.setEnabled(true);
                MainActivity.newOrderArrayList.get(i).setAmount(amount);
                //String []w = MainActivity.newOrderArrayList.get(i).getPrice().split(" ");
                //money  = Integer.parseInt(w[1]);
                int total  = money * amount/ (amount + 1);
                MainActivity.newOrderArrayList.get(i).setPrice("Price: " + total + " USD(" + amount + ")");
                PayOrderActivity.orderEventHandling();
                String str = MainActivity.newOrderArrayList.get(i).getPrice();
                int amount2 = Integer.parseInt(str.substring(str.indexOf("(") + 1,str.indexOf("(") + 2));
                holder.textViewAmount.setText(String.valueOf(amount2));
                notifyDataSetChanged();
            }

            PayOrderActivity.orderEventHandling();
            if (newOrderList.isEmpty()){
                PayOrderActivity.txtMoneyShippingFee.setText("0 USD");
                PayOrderActivity.txtTotalMoney.setText("0 USD");
            }
            notifyDataSetChanged();
        });

        return view;
    }
}
