package com.example.medical.Model;

public class BloodModel {

    private Integer id;
    private String name;
    private String image;
    private String location;
    private String groop;
    private String last;
    private String active;

    public BloodModel() {
    }

    public BloodModel(Integer id, String name, String image, String location, String groop, String last, String active) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.location = location;
        this.groop = groop;
        this.last = last;
        this.active = active;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGroop() {
        return groop;
    }

    public void setGroop(String groop) {
        this.groop = groop;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

}