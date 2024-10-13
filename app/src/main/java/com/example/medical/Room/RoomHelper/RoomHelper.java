package com.example.medical.Room.RoomHelper;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.medical.Model.DoctorModel;
import com.example.medical.Model.NurseModel;
import com.example.medical.Room.DataBases.DoctorTable;

import java.util.List;

@Dao
public interface RoomHelper {
    @Insert
    void insertDoctor(DoctorModel doctorModel);

    @Query("SELECT * FROM doctormodel")
    List<DoctorModel> getDoctorRoom();

    // nurse data
    


}
