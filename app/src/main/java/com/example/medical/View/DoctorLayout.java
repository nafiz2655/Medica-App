package com.example.medical.View;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical.Adapter.DoctorAdapter;
import com.example.medical.Model.DoctorModel;
import com.example.medical.R;
import com.example.medical.Repository.DoctorRepository;
import com.example.medical.Retrofit.ApiService.Services;
import com.example.medical.Retrofit.ServiceHelper.Helper;
import com.example.medical.Room.RoomHelper.RoomHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class DoctorLayout extends AppCompatActivity {


    RecyclerView recyclerView;
    DoctorAdapter doctorAdapter;
    ArrayList<DoctorModel> arrayList = new ArrayList<>();
    ProgressBar progress;

    @Inject
    RoomHelper roomHelper;
    @Inject
    Call<List<DoctorModel>> getdoctordata;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctorlayout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        progress = findViewById(R.id.progress);


        // Initial data load
        loadData();


    }

    public void loadData() {


        DoctorRepository doctorRepository = new DoctorRepository(DoctorLayout.this,roomHelper,getdoctordata);
        doctorRepository.getDoctorData(new DoctorRepository.DataCallback() {
            @Override
            public void onSuccess(List<DoctorModel> doctorModelList) {

                arrayList.addAll(doctorModelList);
                if (doctorAdapter== null){
                    progress.setVisibility(View.GONE);
                    Toast.makeText(DoctorLayout.this, "jjjj"+arrayList.size(), Toast.LENGTH_SHORT).show();
                    doctorAdapter = new DoctorAdapter(arrayList,DoctorLayout.this);
                    recyclerView.setAdapter(doctorAdapter);
                    doctorAdapter.notifyDataSetChanged();
                }else {
                    progress.setVisibility(View.GONE);

                    Toast.makeText(DoctorLayout.this, "Data Null", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(DoctorLayout.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });



    }



}