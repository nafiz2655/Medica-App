package com.example.medical.View;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.medical.Model.AmbulanceModel;
import com.example.medical.Model.EmergercyModel;
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

public class Emergency extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    ArrayList<EmergercyModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_emergency);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        MakeTable();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;  // Initialize the GoogleMap instance here

        // Add markers once the map is ready
        for (int i = 0; i < arrayList.size(); i++) {
            EmergercyModel emergercyModel = arrayList.get(i);
            String sla = emergercyModel.getLatitude();  // Ensure this is a valid latitude value
            String slo = emergercyModel.getLongitude(); // Ensure this is a valid longitude value, not a location name
            String name  = emergercyModel.getName();
            try {
                // Check if sla and slo are valid numbers before parsing
                double la = Double.parseDouble(sla);
                double lo = Double.parseDouble(slo);

                LatLng location = new LatLng(la, lo);

                mMap.addMarker(new MarkerOptions().position(location).title(name)); // Use name of the place/ambulance as marker title
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12)); // Zoom level 12 to get a good view of all locations
            } catch (NumberFormatException e) {
                // Log or handle the exception if the latitude or longitude values are invalid
                e.printStackTrace();
            }
        }
    }

    public void MakeTable() {
        arrayList.add(new EmergercyModel("23.738320", "90.378895", "Popular Diagnostic Centre Ltd", "0125525552", "Hospital"));
        arrayList.add(new EmergercyModel("23.741625", "90.373338", "Ibn Sina Hospital", "0191256212", "Hospital"));
        arrayList.add(new EmergercyModel("23.741625", "90.374950", "LabAid Cardiac Hospital", "01632543552", "Hospital"));
        arrayList.add(new EmergercyModel("23.743310", "90.370859", "Bangladesh Medical College", "01326874569", "Hospital"));
        arrayList.add(new EmergercyModel("23.739515", "90.376280", "Anwar Khan Modern Hospital", "016235489", "Hospital"));
        arrayList.add(new EmergercyModel("23.739089", "90.376477", "Samarita Hospital Ltd", "0191258764", "Hospital"));
        arrayList.add(new EmergercyModel("23.738902", "90.384365", "Green Life Medical College and Hospital", "0145698751", "Hospital"));
        arrayList.add(new EmergercyModel("23.738908", "90.374365", "Abdur Rohman", "017265874631", "Ambulance"));
        arrayList.add(new EmergercyModel("23.738902", "90.354365", "Robin Ahmed", "01856987532", "Ambulance"));
        arrayList.add(new EmergercyModel("23.738302", "90.374365", "Ahasan Habib", "01632587465", "Ambulance"));
        arrayList.add(new EmergercyModel("23.735902", "90.384365", "Tanvir Hasan", "0163245585", "Ambulance"));
        arrayList.add(new EmergercyModel("23.738002", "90.324365", "Rohmot Ullah", "019544235652", "Ambulance"));
    }
}
