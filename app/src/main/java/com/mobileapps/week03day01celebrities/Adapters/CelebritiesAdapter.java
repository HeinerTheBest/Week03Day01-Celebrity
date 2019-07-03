package com.mobileapps.week03day01celebrities.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobileapps.week03day01celebrities.Activities.NewCelebrityActivity;
import com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseHelper;
import com.mobileapps.week03day01celebrities.Models.Celebrity;
import com.mobileapps.week03day01celebrities.R;

import java.io.ByteArrayInputStream;
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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i)
    {
        final Celebrity celebrity = celebrities.get(i);

        viewHolder.tvName.setText(celebrity.getFirstName()+" "+celebrity.getLastName());

        if (celebrity.isFavoriteBool())
        {
            viewHolder.fav.setImageResource(R.drawable.favorite_on);
        }
        else
        {
            viewHolder.fav.setImageResource(R.drawable.favorite_off);
        }

        Log.d("Heiner","Favorite for id "+celebrity.getId()+" have favorite = "+celebrity.isFavoriteBool());

        byte[] outImage= celebrity.getPicture();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        viewHolder.img.setImageBitmap(theImage);



        final String id = String.valueOf(celebrity.getId());

        viewHolder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Heiner","update");
            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Heiner","Delete");
            }
        });

        viewHolder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Heiner","Detail");
            }
        });

        viewHolder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(celebrity.isFavoriteBool())
                {
                    viewHolder.fav.setImageResource(R.drawable.favorite_off);
                    celebrity.setFavorite(false);
                    new CelebrityDataBaseHelper(context).updateCelebrityByID(celebrity);
                }
                else
                {
                    viewHolder.fav.setImageResource(R.drawable.favorite_on);
                    celebrity.setFavorite(true);
                    new CelebrityDataBaseHelper(context).updateCelebrityByID(celebrity);
                }
            }
        });



        /*viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewCelebrityActivity.class);
                intent.putExtra("celebrity_id",id);
                context.startActivity(intent);
            }
        });*/


    }


    @Override
    public int getItemCount() {
        return celebrities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName;
        Button btnDelete,btnUpdate, btnDetail;
        ImageView img,fav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName =  itemView.findViewById(R.id.tvAllTheName);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDetail = itemView.findViewById(R.id.btnDetail);
            img = itemView.findViewById(R.id.imgPictureDemo);
            fav = itemView.findViewById(R.id.btnFavorite);
        }
    }
}
