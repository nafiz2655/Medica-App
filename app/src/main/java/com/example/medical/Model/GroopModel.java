package com.example.medical.Model;

public class GroopModel {

    private Integer id;
    private String name;
    private String image;
    private String roll;


    public GroopModel() {
    }

    public GroopModel(Integer id, String name, String image, String roll) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.roll = roll;
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

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

}
