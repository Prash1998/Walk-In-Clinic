package com.example.walkinclinicv01;

public class Clinic {
    Info info;
    Hours day;
    Service service;


    public Clinic(Info info, Hours day, Service service){
        this.info = info;
        this.day = day;
        this.service = service;
    }
    public Clinic(){}

    public Info getInfo(){ return info;}
    public Hours getHours(){ return day;};
    public Service getService(){ return service; };
    //public String getUSERID(){return USERID;}

}
