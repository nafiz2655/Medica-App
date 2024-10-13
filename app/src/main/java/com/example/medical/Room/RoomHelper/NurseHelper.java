package com.example.medical.Room.RoomHelper;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.medical.Model.NurseModel;

import java.util.List;

@Dao
public interface NurseHelper {

    @Insert
    void insertNurse(NurseModel nurseModel);

    @Query("SELECT * FROM nursemodel")
    List<NurseModel> getNurseRoom();
}
