package com.example.medical.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical.Adapter.BloodAdapter;
import com.example.medical.Adapter.GroopAdapter;
import com.example.medical.Adapter.HospitalAdapter;
import com.example.medical.Model.BloodModel;
import com.example.medical.Model.GroopModel;
import com.example.medical.Model.HospitalModel;
import com.example.medical.R;
import com.example.medical.Retrofit.ApiService.Services;
import com.example.medical.Retrofit.ServiceHelper.Helper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BloodLayout extends AppCompatActivity {

    ArrayList<BloodModel> arrayList = new ArrayList<>();
    BloodAdapter bloodAdapter;
    RecyclerView recyclerView,groop_recyclerView;
    ProgressBar progressbar;
    GroopAdapter groopAdapter;
    ArrayList<GroopModel> arrayListGroop = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bloodlayout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        progressbar = findViewById(R.id.progressbar);
        recyclerView = findViewById(R.id.recyclerView);
        groop_recyclerView = findViewById(R.id.groop_recyclerView);
        ViewData();
        GroopData();

    }
    public void GroopData() {

        Helper helper = Services.getAPI().create(Helper.class);
        Call<List<GroopModel>> getDataGroop = helper.getGroopData();

        getDataGroop.enqueue(new Callback<List<GroopModel>>() {
            @Override
            public void onResponse(Call<List<GroopModel>> call, Response<List<GroopModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    arrayListGroop.clear(); // Clear existing data
                    arrayListGroop.addAll(response.body());
                    if (groopAdapter == null) {
                        groopAdapter = new GroopAdapter(arrayListGroop, BloodLayout.this);
                        groop_recyclerView.setAdapter(groopAdapter);
                    } else {
                        groopAdapter.notifyDataSetChanged();
                    }


                }
            }

            @Override
            public void onFailure(Call<List<GroopModel>> call, Throwable throwable) {

            }
        });

    }

    public void ViewData() {

        Helper helper = Services.getAPI().create(Helper.class);
        Call<List<BloodModel>> getData = helper.getBloodData();


        getData.enqueue(new Callback<List<BloodModel>>() {
            @Override
            public void onResponse(Call<List<BloodModel>> call, Response<List<BloodModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    arrayList.clear(); // Clear existing data
                    arrayList.addAll(response.body());
                    if (bloodAdapter == null) {
                        bloodAdapter = new BloodAdapter(arrayList, BloodLayout.this);
                        recyclerView.setAdapter(bloodAdapter);
                    } else {
                        bloodAdapter.notifyDataSetChanged();
                    }

                    progressbar.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<List<BloodModel>> call, Throwable throwable) {

            }
        });
    }
}