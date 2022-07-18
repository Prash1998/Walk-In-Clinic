package com.example.walkinclinicv01;

public class Info {

    String name;
    String address;
    String phoneNumber;
    String userID;

    public Info(String name,String address, String phoneNumber, String userID){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.userID = userID;
    }

    public Info(){}

    public String getAddress(){ return address; }
    public String getName(){ return name; }
    public String getPhoneNumber(){ return phoneNumber;}
    public String getUserID() {return userID;}

}
