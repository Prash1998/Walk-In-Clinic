package com.example.walkinclinicv01;

public class Service {
    private String serviceName;
    private String roleName;

    public Service(String serviceName,String roleName){
        this.serviceName = serviceName;
        this.roleName = roleName;
    }

    public Service(){}

    public String getServiceName(){return serviceName;}

    public String getRoleName(){return roleName;}

    public String toString(){
        String str = "Service Name: "+serviceName+", Role: "+roleName;
        return str;
    }

}
