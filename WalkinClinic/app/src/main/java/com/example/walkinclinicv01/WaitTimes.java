package com.example.walkinclinicv01;

public class WaitTimes {
    int numPeopleWaiting;
    int waitTimeTotal;
    int waitTimeHours;
    int waitTimeMins;

    public WaitTimes(int numPeopleWaiting, int waitTimeTotal, int waitTimeHours, int waitTimeMins){
        this.numPeopleWaiting = numPeopleWaiting;
        this.waitTimeTotal = waitTimeTotal;
        this.waitTimeHours = waitTimeHours;
        this.waitTimeMins = waitTimeMins;
    }

    public WaitTimes(){}

    public int getNumPeopleWaiting() {
        return numPeopleWaiting;
    }

    public int getWaitTimeTotal(){
        return waitTimeTotal;
    }

    public int getWaitTimeHours() {
        return waitTimeHours;
    }

    public int getWaitTimeMins(){
        return waitTimeMins;
    }

    public void increaseNumPeople(){
        numPeopleWaiting++;
        updateWaitTime();
        getHoursandMins();
    }

    public void updateWaitTime(){
        waitTimeTotal = waitTimeTotal + 15;
    }

    public void getHoursandMins(){
        waitTimeHours = waitTimeTotal/60;
        waitTimeMins = waitTimeTotal%60;
    }
}
