package com.example.medical.Room.DataBases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.medical.Room.ModelRooom.Student;
import com.example.medical.Room.RoomHelper.HelperRo;

@Database(entities = {Student.class},version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract HelperRo roomHelper();

    public static MyDatabase getDatabase(Context context){
        return Room.databaseBuilder(context, MyDatabase.class,"studentInfo")
                .allowMainThreadQueries()
                .build();
    }

}
