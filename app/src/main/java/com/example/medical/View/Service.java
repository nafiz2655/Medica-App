package com.example.medical.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.medical.Model.ServiceModel;
import com.example.medical.R;

import java.util.ArrayList;

public class Service extends AppCompatActivity {

    GridView grid_view;

    ArrayList<ServiceModel> arrayList = new ArrayList<>();

    TextView emergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_service);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        emergency = findViewById(R.id.emergency);
        MakeTable();
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Service.this,Emergency.class));
            }
        });



        grid_view = findViewById(R.id.grid_view);

        MYAdapter myAdapter = new MYAdapter();
        grid_view.setAdapter(myAdapter);
    }

    public void MakeTable(){
        arrayList.add(new ServiceModel("Doctor",""+R.drawable.doctor));
        arrayList.add(new ServiceModel("Hospital",""+R.drawable.mhospital));
        arrayList.add(new ServiceModel("Nurse",""+R.drawable.nurse));
        arrayList.add(new ServiceModel("Blood",""+R.drawable.blood));
        arrayList.add(new ServiceModel("Ambulance",""+R.drawable.ambulance1));
        arrayList.add(new ServiceModel("Conversation",""+R.drawable.conver));
        arrayList.add(new ServiceModel("Medicine",""+R.drawable.medicine));
        arrayList.add(new ServiceModel("Prescription",""+R.drawable.prescription));
    }


    public class MYAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view1 = layoutInflater.inflate(R.layout.grid_layout,viewGroup,false);
            TextView textView = view1.findViewById(R.id.textView);
            ImageView imageView = view1.findViewById(R.id.img);
            CardView cardView = view1.findViewById(R.id.cardView);

            ServiceModel serviceModel = arrayList.get(i);
            textView.setText(serviceModel.getName());
            int image = Integer.parseInt(serviceModel.getImage());
            imageView.setImageResource(image);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (i==0){
                        startActivity(new Intent(Service.this,DoctorLayout.class));
                    }else if (i==1){
                        startActivity(new Intent(Service.this,Hospital.class));
                    }else if (i==2){
                        startActivity(new Intent(Service.this,NurseLayout.class));
                    }else if (i==3){
                        startActivity(new Intent(Service.this,BloodLayout.class));
                    }else if (i==4){
                        startActivity(new Intent(Service.this,AmbulanceLayout.class));
                    }else if (i==5){
                        startActivity(new Intent(Service.this,LanguageTranslate.class));
                    }
                }
            });



            return view1;
        }
    }

}