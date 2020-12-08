package com.lengochuy.dmt.appbandoanonl.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lengochuy.dmt.appbandoanonl.Activity.ShowDishActivity;
import com.lengochuy.dmt.appbandoanonl.Object.MainFood;
import com.lengochuy.dmt.appbandoanonl.R;

import java.util.List;

public class MainFoodAdapter extends RecyclerView.Adapter<MainFoodAdapter.MainFoodViewHolder>{
    List <MainFood> mainFoodList;

    public MainFoodAdapter(List<MainFood> mainFoodList) {
        this.mainFoodList = mainFoodList;
    }

    @NonNull
    @Override
    public MainFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_mainfood, parent, false);
        return new MainFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainFoodViewHolder holder, int position) {
        MainFood mainFood = mainFoodList.get(position);
        holder.circleImageView.setImageResource(mainFood.getResourceID());
        holder.textViewMainFood.setText(mainFood.getTitleNameFood());
    }

    @Override
    public int getItemCount() {
        return mainFoodList.size();
    }

    public static class MainFoodViewHolder extends RecyclerView.ViewHolder{
        ImageView circleImageView;
        TextView textViewMainFood;
        public MainFoodViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView     = itemView.findViewById(R.id.imageMainFood);
            textViewMainFood    = itemView.findViewById(R.id.textViewMainFood);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(itemView.getContext(), ShowDishActivity.class);
                intent.putExtra("namefood",textViewMainFood.getText().toString());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
