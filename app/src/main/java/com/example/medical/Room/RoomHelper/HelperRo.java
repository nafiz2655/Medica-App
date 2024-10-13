package com.example.medical.Room.RoomHelper;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.medical.Retrofit.ModelRetrofit.GetDataModel;
import com.example.medical.Room.ModelRooom.Student;

import java.util.List;

@Dao
public interface HelperRo {

    @Insert
    void insertDataRoom(Student student);

    @Query("SELECT * FROM student ORDER BY id DESC")
    List<Student> readAllData();

}
