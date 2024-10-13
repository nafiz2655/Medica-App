package com.example.medical.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NurseModel {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;
    private String image;
    private String work;
    private String expart;
    private String phone;
    private String experience;

    /**
     * No args constructor for use in serialization
     *
     */
    public NurseModel() {
    }

    public NurseModel(Integer id, String name, String image, String work, String expart, String phone, String experience) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.work = work;
        this.expart = expart;
        this.phone = phone;
        this.experience = experience;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getExpart() {
        return expart;
    }

    public void setExpart(String expart) {
        this.expart = expart;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

}