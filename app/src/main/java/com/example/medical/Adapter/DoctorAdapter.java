package com.example.medical.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medical.Model.DoctorModel;
import com.example.medical.R;
import com.example.medical.Retrofit.ServiceHelper.Helper;
import com.example.medical.View.DoctorLayout;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    ArrayList<DoctorModel> arrayList;
    Context context;

    public DoctorAdapter(ArrayList<DoctorModel> arrayList, DoctorLayout doctorLayout, Helper provideHelper, DoctorLayout layout) {
    }


    public DoctorAdapter(ArrayList<DoctorModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.layout_doctor,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.ViewHolder holder, int position) {

        DoctorModel doctorModel = arrayList.get(position);

        holder.name.setText(doctorModel.getName());
        holder.tv_degree.setText(doctorModel.getDegree());



            holder.tv_degree.setText(doctorModel.getDegree());



            holder.tv_outhers.setText(doctorModel.getOuthers());



            holder.tv_specialist.setText(doctorModel.getSpecialist());


        if (doctorModel.getPhnOne() != null) {

            holder.tv_corentlocation.setText(doctorModel.getPhnOne());
        }else {
            holder.tv_corentlocation.setText("Dhaka Medical College Hospital, Bangladesh");
        }

        if (doctorModel.getDis() != null) {

            holder.address.setText(doctorModel.getDis());
        }else {
            holder.address.setText("Sohorardi  Medical College Hospital, Bangladesh");
        }


        Glide.with(context)
                .load("https://maltinamax.quillncart.com/appsdta/test/medical/image/"+doctorModel.getImage())
                .placeholder(R.drawable.doctorload)
                .into(holder.profile_image);


        holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:123456789"));
                context.startActivity(callIntent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,tv_degree,tv_outhers,tv_corentlocation,address,tv_specialist;

        ImageView profile_image,phone,message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            tv_degree = itemView.findViewById(R.id.tv_degree);
            tv_outhers = itemView.findViewById(R.id.tv_outhers);
            tv_specialist = itemView.findViewById(R.id.tv_specialist);
            tv_corentlocation = itemView.findViewById(R.id.tv_corentlocation);
            address = itemView.findViewById(R.id.address);
            profile_image = itemView.findViewById(R.id.profile_image);
            phone = itemView.findViewById(R.id.phone);

        }
    }
}
