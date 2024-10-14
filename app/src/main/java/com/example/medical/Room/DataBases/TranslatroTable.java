package com.example.medical.Room.DataBases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.medical.Model.LanguageModel;
import com.example.medical.Model.NurseModel;
import com.example.medical.Room.RoomHelper.TranslateHelpar;

@Database(entities = {LanguageModel.class},version = 2, exportSchema = false)

public abstract class TranslatroTable extends RoomDatabase {

    public abstract TranslateHelpar translateHelpar();

    public static TranslatroTable getTranslateTable(Context context){
        return Room.databaseBuilder(context, TranslatroTable.class, "mydatabase")
                .allowMainThreadQueries()
                .build();
    }
}
