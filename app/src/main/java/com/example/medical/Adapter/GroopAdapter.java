package com.example.medical.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medical.Model.GroopModel;
import com.example.medical.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroopAdapter extends RecyclerView.Adapter<GroopAdapter.ViewHolder> {

    ArrayList<GroopModel> arrayList;
    Context context;

    public GroopAdapter(ArrayList<GroopModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public GroopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.groop_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroopAdapter.ViewHolder holder, int position) {

        GroopModel groopModel = arrayList.get(position);
        holder.text.setText(groopModel.getName());
        String image = "https://maltinamax.quillncart.com/appsdta/test/medical/groop/"+groopModel.getImage();

        Glide.with(context)
                .load(image)
                .into(holder.profile_image);




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView profile_image;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profile_image = itemView.findViewById(R.id.profile_image);
            text = itemView.findViewById(R.id.text);
        }
    }
}
