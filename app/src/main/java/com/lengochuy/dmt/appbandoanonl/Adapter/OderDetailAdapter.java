package com.lengochuy.dmt.appbandoanonl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lengochuy.dmt.appbandoanonl.Activity.MainActivity;
import com.lengochuy.dmt.appbandoanonl.Object.NewOrder;
import com.lengochuy.dmt.appbandoanonl.Object.OderDetails;
import com.lengochuy.dmt.appbandoanonl.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OderDetailAdapter extends BaseAdapter {
    List <OderDetails> oderDetailsList;
    Context mContext;

    public OderDetailAdapter(List<OderDetails> oderDetailsList, Context mContext) {
        this.oderDetailsList = oderDetailsList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return oderDetailsList.size();
    }

    @Override
    public Object getItem(int i) {
        return oderDetailsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder{
        ImageView imageView;
        TextView textViewNameRestaurant, textViewNameFood, textViewPrice;
        ImageButton buttonAddOrder;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder  = new ViewHolder();
            view    = LayoutInflater.from(mContext).inflate(R.layout.item_layout_order_details, viewGroup, false);
            holder.imageView    = view.findViewById(R.id.imageViewOderDetails);
            holder.textViewNameRestaurant   = view.findViewById(R.id.textViewNameRestaurantOderDetails);
            holder.textViewNameFood = view.findViewById(R.id.textViewNameFoodOderDetails);
            holder.textViewPrice    = view.findViewById(R.id.textViewPriceOderDetails);
            holder.buttonAddOrder   = view.findViewById(R.id.buttonAddOderDetails);
            view.setTag(holder);
        }else{
            holder  = (ViewHolder) view.getTag();
        }

        OderDetails oderDetails = oderDetailsList.get(i);
        Picasso.with(mContext)
                .load(oderDetails.getId())
                .placeholder(R.drawable.camera)
                .error(R.drawable.error)
                .into(holder.imageView);
        holder.textViewNameRestaurant.setText(oderDetails.getNameRestaurant());
        holder.textViewNameFood.setText(oderDetails.getNameFood());
        holder.textViewPrice.setText(oderDetails.getPriceFood());

        holder.buttonAddOrder.setOnClickListener(view1 -> {
            MainActivity.newOrderArrayList.add(new NewOrder(oderDetails.getId(),oderDetails.getNameFood(),oderDetails.getPriceFood(),1));
                holder.buttonAddOrder.setBackgroundResource(R.drawable.custom_button_cart_order_after);
                holder.buttonAddOrder.setEnabled(false);
        });


        return view;
    }
}
