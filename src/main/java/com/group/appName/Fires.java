package com.group.appName;

public class Fires {
    private String id;
    private String date;
    private String message;
    private String addressObjectFireFeature;
    private String district;
    private String fireStation;


    public Fires() {
    }

    public Fires(String id, String date, String message, String addressObjectFireFeature, String district, String fireStation) {
        this.id = id;
        this.date = date;
        this.message = message;
        this.addressObjectFireFeature = addressObjectFireFeature;
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

    public String getAddressObjectFireFeature() {
        return addressObjectFireFeature;
    }

    public void setAddress_object_fireFeature(String address_object_fireFeature) {
        this.addressObjectFireFeature = addressObjectFireFeature;
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
        return "Fire [id=" + id + ", date=" + date + ", message=" + message + ", addressObjectFireFeature=" + addressObjectFireFeature + ", district=" + district + ", fireStation=" + fireStation + "]";
    }

}