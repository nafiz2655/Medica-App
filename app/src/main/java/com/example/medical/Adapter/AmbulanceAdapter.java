package com.example.medical.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical.Model.AmbulanceModel;
import com.example.medical.Model.HospitalModel;
import com.example.medical.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AmbulanceAdapter extends RecyclerView.Adapter<AmbulanceAdapter.ViewHolder> {

    ArrayList<AmbulanceModel> arrayList;
    Context context;

    public AmbulanceAdapter(ArrayList<AmbulanceModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name,tv_dis;
        RatingBar ratingBar;
        CircleImageView profile_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_dis = itemView.findViewById(R.id.tv_dis);
            profile_image = itemView.findViewById(R.id.profile_image);


        }
    }

    @NonNull
    @Override
    public AmbulanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.ambulance_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AmbulanceAdapter.ViewHolder holder, int position) {
        AmbulanceModel ambulanceModel = arrayList.get(position);

        holder.tv_name.setText(ambulanceModel.getName());
        holder.tv_dis.setText(ambulanceModel.getDis());
        String level = ambulanceModel.getLevel();

        if (level.contains("1")){

            holder.profile_image.setImageResource(R.drawable.ambulance1);
        }else if (level.contains("2")){
            holder.profile_image.setImageResource(R.drawable.ambulance2);


        }else if (level.contains("3")){
            holder.profile_image.setImageResource(R.drawable.ambulance3);


        }else {
            holder.profile_image.setImageResource(R.drawable.ambulance4);

        }


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
