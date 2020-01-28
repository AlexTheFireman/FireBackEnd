package com.group.appName;

public class FiresALL {
    private String id;
    private String date;
    private String message;
    private String address_object_fireFeature;
    private String district;
    private String fireStation;


    public FiresALL() {
    }

    public FiresALL(String id, String date, String message, String address_object_fireFeature, String district, String fireStation) {
        this.id = id;
        this.date = date;
        this.message = message;
        this.address_object_fireFeature = address_object_fireFeature;
        this.district = district;
        this.fireStation = fireStation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddress_object_fireFeature() {
        return address_object_fireFeature;
    }

    public void setAddress_object_fireFeature(String address_object_fireFeature) {
        this.address_object_fireFeature = address_object_fireFeature;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFireStation() {
        return fireStation;
    }

    public void setFireStation(String fireStation) {
        this.fireStation = fireStation;
    }


    @Override
    public String toString() {
        return "Fire [id=" + id + ", date=" + date + ", message=" + message + ", address_object_fireFeature=" + address_object_fireFeature + ", district=" + district + ", fireStation=" + fireStation + "]";
    }

}