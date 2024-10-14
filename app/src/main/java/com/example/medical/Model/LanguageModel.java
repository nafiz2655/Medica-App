package com.example.medical.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LanguageModel {
    @PrimaryKey(autoGenerate = true)
    Integer id ;
    String sound;
    String translate;
    String item;

    public LanguageModel() {
    }

    public LanguageModel(String sound, String translate, String item) {
        this.sound = sound;
        this.translate = translate;
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
