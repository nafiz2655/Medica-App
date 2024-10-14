package com.example.medical.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical.Model.BloodModel;
import com.example.medical.Model.HospitalModel;
import com.example.medical.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {


    ArrayList<HospitalModel> arrayList;
    Context context;

    public HospitalAdapter(ArrayList<HospitalModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name,location,dis;
        CircleImageView profile_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            location = itemView.findViewById(R.id.location);
            dis = itemView.findViewById(R.id.dis);
            profile_image = itemView.findViewById(R.id.profile_image);

        }
    }

    @NonNull
    @Override
    public HospitalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.hospital,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.ViewHolder holder, int position) {

        HospitalModel hospitalModel = arrayList.get(position);

        holder.tv_name.setText(hospitalModel.getName());
        holder.location.setText(hospitalModel.getLocation());
        holder.dis.setText(" This essay helps in teaching young kids the importance of hospitals and doctors. They understand the benefits of good health as they write about the hospital.");
        if (position==0){
            holder.profile_image.setImageResource(R.drawable.h1);
        }else if (position==1){
            holder.profile_image.setImageResource(R.drawable.h7);
        }else if (position==2){
            holder.profile_image.setImageResource(R.drawable.h3);
        }else if (position==3){
            holder.profile_image.setImageResource(R.drawable.h4);
        }else if (position==4){
            holder.profile_image.setImageResource(R.drawable.h5);
        }else if (position==5){
            holder.profile_image.setImageResource(R.drawable.h6);
        }else if (position==6){
            holder.profile_image.setImageResource(R.drawable.h7);
        }else if (position==7){
            holder.profile_image.setImageResource(R.drawable.ambulance3);
        }else if (position==8){
            holder.profile_image.setImageResource(R.drawable.ambulance3);
        }else if (position==9){
            holder.profile_image.setImageResource(R.drawable.ambulance3);
        }else{
            holder.profile_image.setImageResource(R.drawable.ambulance3);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
