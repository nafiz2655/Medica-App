package com.example.medical.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical.Adapter.DoctorAdapter;
import com.example.medical.Adapter.NurseAdapter;
import com.example.medical.Model.DoctorModel;
import com.example.medical.Model.NurseModel;
import com.example.medical.R;
import com.example.medical.Repository.DoctorRepository;
import com.example.medical.Repository.NurseRepository;
import com.example.medical.Retrofit.ApiService.Services;
import com.example.medical.Retrofit.ModelRetrofit.GetDataModel;
import com.example.medical.Retrofit.ServiceHelper.Helper;
import com.example.medical.Room.RoomHelper.NurseHelper;
import com.example.medical.Room.RoomHelper.RoomHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NurseLayout extends AppCompatActivity {
    RecyclerView recyclerView;
    NurseAdapter nurseAdapter;
    ArrayList<NurseModel> arrayList = new ArrayList<>();
    ProgressBar progress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nurse_layout);
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

        Helper helper = Services.getAPI().create(Helper.class);
        Call<List<NurseModel>> getData = helper.getNurseData();
       getData.enqueue(new Callback<List<NurseModel>>() {
           @Override
           public void onResponse(Call<List<NurseModel>> call, Response<List<NurseModel>> response) {
               if (response.isSuccessful() && response.body() != null) {
                   arrayList.clear(); // Clear existing data
                   arrayList.addAll(response.body());

                   // Set adapter or notify existing one of the data change
                   if (nurseAdapter == null) {
                       nurseAdapter = new NurseAdapter(arrayList, NurseLayout.this);
                       recyclerView.setAdapter(nurseAdapter);
                   } else {
                       nurseAdapter.notifyDataSetChanged();
                   }

                   progress.setVisibility(View.GONE);

                   Toast.makeText(NurseLayout.this, "Data loaded: " + arrayList.size() + " items", Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(NurseLayout.this, "Failed to load data", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<List<NurseModel>> call, Throwable throwable) {

               Toast.makeText(NurseLayout.this, "Failed to load data", Toast.LENGTH_SHORT).show();


           }
       });


    }



}