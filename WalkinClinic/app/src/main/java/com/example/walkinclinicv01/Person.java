package com.example.walkinclinicv01;

public class Person {

    private String role;
    private String firstName;
    private String lastName;
    private String userName;


    public Person(String role,String firstName, String lastName, String userName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.role = role;

    }
    public Person(){}

    public String getRole(){return role;}

    public String getFirstName(){ return firstName;}

    public String getLastName(){ return lastName;}

    public String getUserName(){ return userName;}

    public String toString(){
        String str = "FirstName:"+firstName+" Lastname:"+lastName+"Role: "+role;
        return str;
    }
}
