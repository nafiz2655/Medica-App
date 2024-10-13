package com.example.medical.Hilt;

import android.content.Context;
import android.os.ParcelUuid;

import com.example.medical.Model.DoctorModel;
import com.example.medical.Model.NurseModel;
import com.example.medical.Retrofit.ApiService.Services;
import com.example.medical.Retrofit.ModelRetrofit.GetDataModel;
import com.example.medical.Retrofit.ServiceHelper.Helper;
import com.example.medical.Room.DataBases.DoctorTable;
import com.example.medical.Room.DataBases.MyDatabase;
import com.example.medical.Room.DataBases.NurseTabel;
import com.example.medical.Room.RoomHelper.HelperRo;
import com.example.medical.Room.RoomHelper.NurseHelper;
import com.example.medical.Room.RoomHelper.RoomHelper;
import com.example.medical.View.DoctorLayout;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Call;

@Module
@InstallIn(SingletonComponent.class)
public class SingletonModule {
    //Retrofit Requeat
    @Provides
    @Singleton
    public Helper provideHelper() {
        return Services.getAPI().create(Helper.class);
    }

    @Provides
    @Singleton
    public Call<List<GetDataModel>> provideGetData(Helper helper) {
        return helper.getalldata();
    }

    // ==========================================================Medical Singleton


    @Provides
    @Singleton
    public Call<List<DoctorModel>> getdoctordata(Helper helper){
        return helper.getdoctor();
    }
    @Provides
    @Singleton
    public DoctorTable doctorTable(@ApplicationContext Context context){
        return DoctorTable.getDoctorTable(context);
    }

    @Provides
    @Singleton
    public RoomHelper roomHelper(DoctorTable doctorTable){
        return doctorTable.roomHelper();
    }

    //nurse======================================================================
    //nurse room
    @Provides
    @Singleton
    public NurseTabel nurseTabel(@ApplicationContext Context context){
        return NurseTabel.getNurseTable(context);
    }
    @Provides
    @Singleton
    public NurseHelper nurseHelper(NurseTabel nurseTabel){
        return nurseTabel.nurseHelper();
    }

    @Provides
    @Singleton
    public Call<List<NurseModel>> getnursedatah(Helper helper){
        return helper.getNurseData();
    }

    //==========================================================Medical Singleton

    //Rooom Request
    @Provides
    @Singleton
    public MyDatabase myDatabase(@ApplicationContext Context context){
        return MyDatabase.getDatabase(context);
    }
    @Provides
    @Singleton
    public HelperRo helperRo(MyDatabase myDatabase){
        return myDatabase.roomHelper();
    }





}
