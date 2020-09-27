package com.kp.ebana;

import java.util.Date;

public class Record {

    private String name_of;
    private String  surname_of;
    private String date_of;
    private String hour_of;
    private String info_of;

    public Record(String name_of, String surname_of, String date_of, String hour_of, String info_of) {
        this.name_of = name_of;
        this.surname_of = surname_of;
        this.date_of = date_of;
        this.hour_of = hour_of;
        this.info_of = info_of;
    }

    public String getName_of() {
        return name_of;
    }

    public void setName_of(String name_of) {
        this.name_of = name_of;
    }

    public String getSurname_of() {
        return surname_of;
    }

    public void setSurname_of(String surname_of) {
        this.surname_of = surname_of;
    }

    public String getDate_of() {
        return date_of;
    }

    public void setDate_of(String date_of) {
        this.date_of = date_of;
    }

    public String getHour_of() {
        return hour_of;
    }

    public void setHour_of(String hour_of) {
        this.hour_of = hour_of;
    }

    public String getInfo_of() {
        return info_of;
    }

    public void setInfo_of(String info_of) {
        this.info_of = info_of;
    }
}
