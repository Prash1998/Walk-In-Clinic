package com.example.walkinclinicv01;

public class WeeklySchedule {
    Hours mon, tue, wed, thur, fri, sat, sun;

    public WeeklySchedule (Hours mon, Hours tue, Hours wed, Hours thur, Hours fri, Hours sat, Hours sun){
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thur = thur;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
    }

    public WeeklySchedule (){ }


    public Hours getMon(){
        return mon;
    }

    public Hours getTue (){
        return tue;
    }

    public Hours getWed(){
        return wed;
    }

    public Hours getThur(){
        return thur;
    }

    public Hours getFri(){
        return fri;
    }

    public Hours getSat(){
        return sat;
    }

    public Hours getSun(){
        return sun;
    }

}
