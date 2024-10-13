package com.example.medical.Model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DoctorModel {

    @PrimaryKey(autoGenerate = true)
    int id;
    private String name;
    private String image;
    private String degree;
    private String outhers;
    private String specialist;
    private String sesignation;
    private String organization;
    private String phone;
    private String disTitle;
    private String dis;
    private String phnOne;
    private String roomOne;
    private String timeOne;
    private String phoneOne;
    private String phnTwo;
    private String roomTwo;
    private String timeTwo;
    private String phoneTwo;
    private String phnThree;
    private String roomThree;
    private String timeThree;
    private String phoneThree;

    public DoctorModel() {
    }

    public DoctorModel(int id, String name, String image, String degree, String outhers, String specialist, String sesignation, String organization, String phone, String disTitle, String dis, String phnOne, String roomOne, String timeOne, String phoneOne, String phnTwo, String roomTwo, String timeTwo, String phoneTwo, String phnThree, String roomThree, String timeThree, String phoneThree) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.degree = degree;
        this.outhers = outhers;
        this.specialist = specialist;
        this.sesignation = sesignation;
        this.organization = organization;
        this.phone = phone;
        this.disTitle = disTitle;
        this.dis = dis;
        this.phnOne = phnOne;
        this.roomOne = roomOne;
        this.timeOne = timeOne;
        this.phoneOne = phoneOne;
        this.phnTwo = phnTwo;
        this.roomTwo = roomTwo;
        this.timeTwo = timeTwo;
        this.phoneTwo = phoneTwo;
        this.phnThree = phnThree;
        this.roomThree = roomThree;
        this.timeThree = timeThree;
        this.phoneThree = phoneThree;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getOuthers() {
        return outhers;
    }

    public void setOuthers(String outhers) {
        this.outhers = outhers;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getSesignation() {
        return sesignation;
    }

    public void setSesignation(String sesignation) {
        this.sesignation = sesignation;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDisTitle() {
        return disTitle;
    }

    public void setDisTitle(String disTitle) {
        this.disTitle = disTitle;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getPhnOne() {
        return phnOne;
    }

    public void setPhnOne(String phnOne) {
        this.phnOne = phnOne;
    }

    public String getRoomOne() {
        return roomOne;
    }

    public void setRoomOne(String roomOne) {
        this.roomOne = roomOne;
    }

    public String getTimeOne() {
        return timeOne;
    }

    public void setTimeOne(String timeOne) {
        this.timeOne = timeOne;
    }

    public String getPhoneOne() {
        return phoneOne;
    }

    public void setPhoneOne(String phoneOne) {
        this.phoneOne = phoneOne;
    }

    public String getPhnTwo() {
        return phnTwo;
    }

    public void setPhnTwo(String phnTwo) {
        this.phnTwo = phnTwo;
    }

    public String getRoomTwo() {
        return roomTwo;
    }

    public void setRoomTwo(String roomTwo) {
        this.roomTwo = roomTwo;
    }

    public String getTimeTwo() {
        return timeTwo;
    }

    public void setTimeTwo(String timeTwo) {
        this.timeTwo = timeTwo;
    }

    public String getPhoneTwo() {
        return phoneTwo;
    }

    public void setPhoneTwo(String phoneTwo) {
        this.phoneTwo = phoneTwo;
    }

    public String getPhnThree() {
        return phnThree;
    }

    public void setPhnThree(String phnThree) {
        this.phnThree = phnThree;
    }

    public String getRoomThree() {
        return roomThree;
    }

    public void setRoomThree(String roomThree) {
        this.roomThree = roomThree;
    }

    public String getTimeThree() {
        return timeThree;
    }

    public void setTimeThree(String timeThree) {
        this.timeThree = timeThree;
    }

    public String getPhoneThree() {
        return phoneThree;
    }

    public void setPhoneThree(String phoneThree) {
        this.phoneThree = phoneThree;
    }

}