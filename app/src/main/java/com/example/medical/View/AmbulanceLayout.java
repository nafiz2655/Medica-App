package com.example.medical.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical.Adapter.AmbulanceAdapter;
import com.example.medical.Adapter.NurseAdapter;
import com.example.medical.Model.AmbulanceModel;
import com.example.medical.Model.NurseModel;
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

public class AmbulanceLayout extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ProgressBar progressbar;
    ArrayList<AmbulanceModel> arrayList = new ArrayList<>();
    AmbulanceAdapter ambulanceAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ambulancelayout);
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
    public void  ViewData(){

        Helper helper = Services.getAPI().create(Helper.class);
        Call<List<AmbulanceModel>> getData = helper.getAmbulanceData();
        getData.enqueue(new Callback<List<AmbulanceModel>>() {
            @Override
            public void onResponse(Call<List<AmbulanceModel>> call, Response<List<AmbulanceModel>> response) {


                if (response.isSuccessful() && response.body() != null) {
                    arrayList.clear(); // Clear existing data
                    arrayList.addAll(response.body());

                    // Set adapter or notify existing one of the data change
                    if (ambulanceAdapter == null) {
                        ambulanceAdapter = new AmbulanceAdapter(arrayList, AmbulanceLayout.this);
                        recyclerView.setAdapter(ambulanceAdapter);
                    } else {
                        ambulanceAdapter.notifyDataSetChanged();
                    }

                    progressbar.setVisibility(View.GONE);


                    // Location
                    for (int i = 0; i < arrayList.size(); i++) {
                        AmbulanceModel getDataItem = arrayList.get(i);
                        String sla = getDataItem.getLatitude();
                        String slo = getDataItem.getLongitude();
                        String level = getDataItem.getLevel();
                        String name = getDataItem.getName();
                        String number = getDataItem.getNumber();

                        double la = Double.parseDouble(sla);
                        double lo = Double.parseDouble(slo);

                        LatLng location = new LatLng(la, lo);

                        mMap.addMarker(new MarkerOptions().position(location).title("Lever:- "+level+"  "+name+"   Mob:- "+number));

                        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                    }



                    Toast.makeText(AmbulanceLayout.this, "Data loaded: " + arrayList.size() + " items", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AmbulanceLayout.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<AmbulanceModel>> call, Throwable throwable) {

            }
        });
    }
}