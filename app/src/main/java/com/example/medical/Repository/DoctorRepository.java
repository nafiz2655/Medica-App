package com.example.medical.Repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.medical.Model.DoctorModel;
import com.example.medical.Retrofit.ModelRetrofit.GetDataModel;
import com.example.medical.Room.ModelRooom.Student;
import com.example.medical.Room.RoomHelper.RoomHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorRepository {

    public Context context;
    @Inject
    RoomHelper roomHelper;
    @Inject
    Call<List<DoctorModel>> getdoctordata;

    public DoctorRepository(Context context, RoomHelper roomHelper, Call<List<DoctorModel>> getdoctordata) {
        this.context = context;
        this.roomHelper = roomHelper;
        this.getdoctordata = getdoctordata;
    }

    public interface DataCallback {
        void onSuccess(List<DoctorModel> doctorModelList);
        void onFailure(String errorMessage);
    }


    public void getDoctorData(DataCallback dataCallback){
        try {
            if (checkInternet()){
                faceOnlineData(dataCallback);
            }else {
                List<DoctorModel> doctorlist = roomHelper.getDoctorRoom();
                if (doctorlist != null && !doctorlist.isEmpty()){
                    dataCallback.onSuccess(doctorlist);
                }else {
                    Toast.makeText(context, "No Data Found In Room", Toast.LENGTH_SHORT).show();
                    dataCallback.onFailure("Data Can not Found In Rooom");
                }

            }
        }catch (Exception e){
            Toast.makeText(context, "Something working is Error", Toast.LENGTH_SHORT).show();
        }

    }

    public void faceOnlineData(DataCallback dataCallback) {
        getdoctordata.enqueue(new Callback<List<DoctorModel>>() {
            @Override
            public void onResponse(Call<List<DoctorModel>> call, Response<List<DoctorModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DoctorModel> doctorModels = new ArrayList<>();
                    for (DoctorModel getdoctorlist : response.body()) {
                        DoctorModel doctorModel = new DoctorModel();
                        doctorModel.setName(getdoctorlist.getName());
                        doctorModel.setImage(getdoctorlist.getImage());
                        doctorModel.setDegree(getdoctorlist.getDegree());
                        doctorModel.setOuthers(getdoctorlist.getOuthers());
                        doctorModel.setSpecialist(getdoctorlist.getSpecialist());
                        doctorModel.setSesignation(getdoctorlist.getSesignation());
                        doctorModel.setOrganization(getdoctorlist.getOrganization());
                        doctorModel.setPhone(getdoctorlist.getPhone());
                        doctorModel.setDisTitle(getdoctorlist.getDisTitle());
                        doctorModel.setDis(getdoctorlist.getDis());
                        doctorModel.setPhnOne(getdoctorlist.getPhnOne());
                        doctorModel.setRoomOne(getdoctorlist.getRoomOne());
                        doctorModel.setTimeOne(getdoctorlist.getTimeOne());
                        doctorModel.setPhnTwo(getdoctorlist.getPhnTwo());
                        doctorModel.setRoomTwo(getdoctorlist.getRoomTwo());
                        doctorModel.setTimeTwo(getdoctorlist.getTimeTwo());
                        doctorModel.setPhnThree(getdoctorlist.getPhnThree());
                        doctorModel.setRoomThree(getdoctorlist.getRoomThree());
                        doctorModel.setTimeThree(getdoctorlist.getTimeThree());



                        roomHelper.insertDoctor(doctorModel);  // Save data locally
                        doctorModels.add(doctorModel);  // Add to list
                    }

                    dataCallback.onSuccess(doctorModels);
                } else {
                    Toast.makeText(context, "Data is empty", Toast.LENGTH_SHORT).show();
                    dataCallback.onFailure("Failed to retrieve data from server.");
                }
            }

            @Override
            public void onFailure(Call<List<DoctorModel>> call, Throwable throwable) {
                Toast.makeText(context, "Something went wrong. Please reload. Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                dataCallback.onFailure("Failed");
            }
        });
    }







    private boolean checkInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
