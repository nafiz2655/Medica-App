package com.example.medical.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical.Model.AmbulanceModel;
import com.example.medical.Model.BloodModel;
import com.example.medical.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BloodAdapter  extends RecyclerView.Adapter<BloodAdapter.ViewHolder>{

    ArrayList<BloodModel> arrayList;
    Context context;

    public BloodAdapter(ArrayList<BloodModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public BloodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.bood_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BloodAdapter.ViewHolder holder, int position) {

        BloodModel bloodModel = arrayList.get(position);
        holder.tv_name.setText(bloodModel.getName());
        holder.location.setText(bloodModel.getLocation());
        holder.groop.setText(bloodModel.getGroop());
        holder.last.setText(bloodModel.getLast());

        if (position==0){
            holder.profile_image.setImageResource(R.drawable.b1);
        }else if (position==1){
            holder.profile_image.setImageResource(R.drawable.b2);
        }else if (position==2){
            holder.profile_image.setImageResource(R.drawable.b3);
        }else if (position==3){
            holder.profile_image.setImageResource(R.drawable.b4);
        }else if (position==4){
            holder.profile_image.setImageResource(R.drawable.b5);
        }else if (position==5){
            holder.profile_image.setImageResource(R.drawable.b7);
        }else if (position==6){
            holder.profile_image.setImageResource(R.drawable.b8);
        }else if (position==7){
            holder.profile_image.setImageResource(R.drawable.b9);
        }else if (position==8){
            holder.profile_image.setImageResource(R.drawable.b10);
        }else if (position==9){
            holder.profile_image.setImageResource(R.drawable.b5);
        }else{
            holder.profile_image.setImageResource(R.drawable.b8);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,location,groop,last;
        CircleImageView profile_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            location = itemView.findViewById(R.id.location);
            groop = itemView.findViewById(R.id.groop);
            last = itemView.findViewById(R.id.last);
            profile_image = itemView.findViewById(R.id.profile_image);



        }
    }
}
