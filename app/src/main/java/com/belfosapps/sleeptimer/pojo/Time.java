package com.belfosapps.sleeptimer.pojo;

public class Time {
    private String time;
    private boolean alarm;

    public Time(String time, boolean alarm) {
        this.time = time;
        this.alarm = alarm;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isAlarm() {
        return alarm;
    }

    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }
}
