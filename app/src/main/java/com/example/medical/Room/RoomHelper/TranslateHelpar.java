package com.example.medical.Room.RoomHelper;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.medical.Model.LanguageModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TranslateHelpar {

    @Insert
    void insertDoctor(LanguageModel languageModel);

    @Query("SELECT * FROM languagemodel")
    List<LanguageModel> getsentance();
}
