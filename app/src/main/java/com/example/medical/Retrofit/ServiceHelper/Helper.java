package com.example.medical.Retrofit.ServiceHelper;


import com.example.medical.Model.AmbulanceModel;
import com.example.medical.Model.DoctorModel;
import com.example.medical.Model.NurseModel;
import com.example.medical.Retrofit.ModelRetrofit.DeleteDataModel;
import com.example.medical.Retrofit.ModelRetrofit.GetDataModel;
import com.example.medical.Retrofit.ModelRetrofit.InsertDataModel;
import com.example.medical.Retrofit.ModelRetrofit.UpdateDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Helper {

    //Insert Data
    @POST("insertimage.php")
    Call<Void> insertData(@Body InsertDataModel insertDataModel);

    //getData
    @GET("getdata.php")
    Call<List<GetDataModel>> getalldata();


    // if no image select
    @POST("updatedata.php")
    Call<Void> updatedata(@Body UpdateDataModel updateDataModel);

    // if new image select
    @POST("update2.php")
    Call<Void> updatedataimage(@Body UpdateDataModel updateDataModel);

    @POST("deletedata.php")
    Call<Void> deleteData(@Body DeleteDataModel deleteDataModel);

    @GET("getdoctordata.php")
    Call<List<DoctorModel>> getdoctor();

    @GET("getnursedata.php")
    Call<List<NurseModel>> getNurseData();

    @GET("getambulancedata.php")
    Call<List<AmbulanceModel>> getAmbulanceData();
}
