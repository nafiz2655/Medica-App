package com.example.medical.Room.DataBases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.medical.Model.DoctorModel;
import com.example.medical.Room.ModelRooom.Student;
import com.example.medical.Room.RoomHelper.RoomHelper;

@Database(entities = {DoctorModel.class},version = 2, exportSchema = false)
public abstract class DoctorTable extends RoomDatabase {

    public abstract RoomHelper roomHelper();

    public static DoctorTable getDoctorTable(Context context){
        return Room.databaseBuilder(context, DoctorTable.class,"doctorInfo")
                .allowMainThreadQueries()
                .build();
    }
}
