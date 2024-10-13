package com.example.medical.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medical.Model.NurseModel;
import com.example.medical.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class NurseAdapter extends RecyclerView.Adapter<NurseAdapter.ViewHolder> {

    ArrayList<NurseModel> arrayList;
    Context context;



    public NurseAdapter(ArrayList<NurseModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView tv_name,tv_work,tv_expart,tv_expreance;
        CircleImageView profile_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_work = itemView.findViewById(R.id.tv_work);
            tv_expart = itemView.findViewById(R.id.tv_expart);
            tv_expreance = itemView.findViewById(R.id.tv_expreance);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }

    @NonNull
    @Override
    public NurseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.nurse_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NurseAdapter.ViewHolder holder, int position) {

        NurseModel nurseModel = arrayList.get(position);
        holder.tv_name.setText(nurseModel.getName());
        holder.tv_work.setText("Hospital Name :- \n"+nurseModel.getWork());
        holder.tv_expart.setText("Nurse Expart :- \n"+nurseModel.getExpart());
        holder.tv_expreance.setText("Nurse Expreance :- \n"+nurseModel.getExperience()+" Year");
        Glide.with(context)
                .load("https://maltinamax.quillncart.com/appsdta/test/medical/nurseimage/"+nurseModel.getImage())
                .placeholder(R.drawable.doctorload)
                .into(holder.profile_image);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
