package com.example.walkinclinicv01;

public class Hours {
    String open;
    String closed;

    public Hours(String open, String closed){
        this.open=open;
        this.closed=closed;
    }
    public Hours(){}

    public String getOpen(){
        return open;
    }

    public String getClosed(){
        return closed;
    }

}