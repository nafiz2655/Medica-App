package com.example.medical.Model;

public class AmbulanceModel {

    private Integer id;
    private String name;
    private String latitude;
    private String longitude;
    private String number;
    private String dis;
    private String retting;
    private String level;


    public AmbulanceModel() {
    }

    public AmbulanceModel(Integer id, String name, String latitude, String longitude, String number, String dis, String retting, String level) {
        super();
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.number = number;
        this.dis = dis;
        this.retting = retting;
        this.level = level;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getRetting() {
        return retting;
    }

    public void setRetting(String retting) {
        this.retting = retting;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
