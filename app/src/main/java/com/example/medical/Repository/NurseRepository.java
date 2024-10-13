package com.example.medical.Repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.medical.Model.NurseModel;
import com.example.medical.Room.RoomHelper.NurseHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NurseRepository {

    public Context context;
    @Inject
    NurseHelper nurseHelper;
    @Inject
    Call<List<NurseModel>> getnursedata; // Update to handle List<NurseModel>

    public NurseRepository(Context context, NurseHelper nurseHelper, Call<List<NurseModel>> getnursedata) {
        this.context = context;
        this.nurseHelper = nurseHelper;
        this.getnursedata = getnursedata;
    }

    public interface DataCallbackNurse {
        void onSuccess(List<NurseModel> nurseModelList);
        void onFailed(String error);
    }

    public void getNurseData(DataCallbackNurse dataCallbackNurse) {
        try {
            if (checkInternet()) {
                fetchOnlineData(dataCallbackNurse);
            } else {
                List<NurseModel> nurseModelList = nurseHelper.getNurseRoom();
                if (nurseModelList != null && !nurseModelList.isEmpty()) {
                    dataCallbackNurse.onSuccess(nurseModelList);
                } else {
                    Toast.makeText(context, "No Data Found In Room", Toast.LENGTH_SHORT).show();
                    dataCallbackNurse.onFailed("Data Cannot Be Found In Room");
                }
            }
        } catch (Exception e) {
            Toast.makeText(context, "Data is empty", Toast.LENGTH_SHORT).show();
            dataCallbackNurse.onFailed("Failed to retrieve data.");
        }
    }

    public void fetchOnlineData(final DataCallbackNurse dataCallbackNurse) {
        getnursedata.enqueue(new Callback<List<NurseModel>>() { // Update callback to handle List<NurseModel>
            @Override
            public void onResponse(Call<List<NurseModel>> call, Response<List<NurseModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<NurseModel> nurseModelList = new ArrayList<>();
                    for (NurseModel singleNurseData : response.body()) {
                        NurseModel nurseModel = new NurseModel();
                        nurseModel.setImage(singleNurseData.getImage());
                        nurseModel.setWork(singleNurseData.getWork());
                        nurseModel.setExpart(singleNurseData.getExpart()); // Corrected field here
                        nurseModel.setPhone(singleNurseData.getPhone());
                        nurseModel.setExperience(singleNurseData.getExperience());

                        nurseHelper.insertNurse(nurseModel);
                        nurseModelList.add(nurseModel);
                    }
                    dataCallbackNurse.onSuccess(nurseModelList);
                } else {
                    Toast.makeText(context, "Data is empty", Toast.LENGTH_SHORT).show();
                    dataCallbackNurse.onFailed("Failed to retrieve data from server.");
                }
            }

            @Override
            public void onFailure(Call<List<NurseModel>> call, Throwable throwable) {
                Toast.makeText(context, "Failed to connect to server", Toast.LENGTH_SHORT).show();
                dataCallbackNurse.onFailed(throwable.getMessage());
            }
        });
    }

    private boolean checkInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
