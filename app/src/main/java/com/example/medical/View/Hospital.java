package com.example.medical.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical.Adapter.AmbulanceAdapter;
import com.example.medical.Adapter.HospitalAdapter;
import com.example.medical.Model.HospitalModel;
import com.example.medical.R;
import com.example.medical.Retrofit.ApiService.Services;
import com.example.medical.Retrofit.ServiceHelper.Helper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Hospital extends AppCompatActivity implements OnMapReadyCallback {

    ArrayList<HospitalModel> arrayList = new ArrayList<>();
    HospitalAdapter hospitalAdapter;
    RecyclerView recyclerView;
    ProgressBar progressbar;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hospital);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        progressbar = findViewById(R.id.progressbar);
        recyclerView = findViewById(R.id.recyclerView);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ViewData();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;


    }
    public void  ViewData() {

        Helper helper = Services.getAPI().create(Helper.class);
        Call<List<HospitalModel>> getData = helper.getHostitalData();

        getData.enqueue(new Callback<List<HospitalModel>>() {
            @Override
            public void onResponse(Call<List<HospitalModel>> call, Response<List<HospitalModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    arrayList.clear(); // Clear existing data
                    arrayList.addAll(response.body());
                    if (hospitalAdapter == null) {
                        hospitalAdapter = new HospitalAdapter(arrayList, Hospital.this);
                        recyclerView.setAdapter(hospitalAdapter);
                    } else {
                        hospitalAdapter.notifyDataSetChanged();
                    }

                    progressbar.setVisibility(View.GONE);
                    for (int i = 0; i < arrayList.size(); i++) {
                        HospitalModel hospitalModel = arrayList.get(i);
                        String sla = hospitalModel.getLatitude();  // Ensure this is a valid latitude value
                        String slo = hospitalModel.getLongitude(); // Ensure this is a valid longitude value, not a location name
                        String name = hospitalModel.getName();

                        try {
                            // Check if sla and slo are valid numbers before parsing
                            double la = Double.parseDouble(sla);
                            double lo = Double.parseDouble(slo);

                            LatLng location = new LatLng(la, lo);

                            mMap.addMarker(new MarkerOptions().position(location).title(name));

                            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                        } catch (NumberFormatException e) {
                            // Log or handle the exception if the latitude or longitude values are invalid
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<List<HospitalModel>> call, Throwable throwable) {

            }
        });

    }
}