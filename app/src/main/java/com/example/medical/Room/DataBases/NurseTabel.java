package com.example.medical.Room.DataBases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.medical.Model.DoctorModel;
import com.example.medical.Model.NurseModel;
import com.example.medical.Room.RoomHelper.NurseHelper;
import com.example.medical.Room.RoomHelper.RoomHelper;
@Database(entities = {NurseModel.class},version = 2, exportSchema = false)
public abstract class NurseTabel extends RoomDatabase {

    public abstract NurseHelper nurseHelper();

    public static NurseTabel getNurseTable(Context context){
        return Room.databaseBuilder(context,NurseTabel.class,"nursetable")
                .allowMainThreadQueries()
                .build();
    }

}
