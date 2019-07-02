package com.mobileapps.week03day01celebrities.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileapps.week03day01celebrities.Activities.NewCelebrityActivity;
import com.mobileapps.week03day01celebrities.Models.Celebrity;
import com.mobileapps.week03day01celebrities.R;

import java.util.ArrayList;

public class CelebritiesAdapter extends RecyclerView.Adapter<CelebritiesAdapter.ViewHolder>
{
    ArrayList<Celebrity> celebrities = new ArrayList<>();
    Context context;

    public CelebritiesAdapter(ArrayList<Celebrity> celebrities, Context context) {
        this.celebrities = celebrities;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.celebrity_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        Celebrity celebrity = celebrities.get(i);

        viewHolder.tvName.setText(celebrity.getName());
        viewHolder.tvBorn.setText(celebrity.getBornCountry());
        viewHolder.tvCategory.setText(celebrity.getCategory());

        final String id = String.valueOf(celebrity.getId());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewCelebrityActivity.class);
                intent.putExtra("celebrity_id",id);
                context.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return celebrities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public  TextView tvName;
        TextView tvCategory;
        TextView tvBorn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName =  itemView.findViewById(R.id.tvName);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvBorn = itemView.findViewById(R.id.tvBornCountry);
        }
    }
}
