package com.kp.ebana;

public class Record {

    private String nazwa_zdarzenia;
    private String miejsce_zdarzenia;
    private String data_zdarzenia;
    private String godzina_zdarzenia;
    private String opis_zdarzenia;
    private String typ_zdarzenia;

    public  Record()
    {

    }
    public Record(String name_of, String place_of, String date_of, String hour_of, String info_of, String type_of) {
        this.nazwa_zdarzenia = name_of;
        this.miejsce_zdarzenia = place_of;
        this.data_zdarzenia = date_of;
        this.godzina_zdarzenia = hour_of;
        this.opis_zdarzenia = info_of;
        this.typ_zdarzenia = type_of;
    }

    public String getTyp_zdarzenia() {
        return typ_zdarzenia;
    }

    public void setTyp_zdarzenia(String typ_zdarzenia) {
        this.typ_zdarzenia = typ_zdarzenia;
    }

    public String getNazwa_zdarzenia() {
        return nazwa_zdarzenia;
    }

    public void setNazwa_zdarzenia(String nazwa_zdarzenia) {
        this.nazwa_zdarzenia = nazwa_zdarzenia;
    }

    public String getMiejsce_zdarzenia() {
        return miejsce_zdarzenia;
    }

    public void setMiejsce_zdarzenia(String miejsce_zdarzenia) {
        this.miejsce_zdarzenia = miejsce_zdarzenia;
    }

    public String getData_zdarzenia() {
        return data_zdarzenia;
    }

    public void setData_zdarzenia(String data_zdarzenia) {
        this.data_zdarzenia = data_zdarzenia;
    }

    public String getGodzina_zdarzenia() {
        return godzina_zdarzenia;
    }

    public void setGodzina_zdarzenia(String godzina_zdarzenia) {
        this.godzina_zdarzenia = godzina_zdarzenia;
    }

    public String getOpis_zdarzenia() {
        return opis_zdarzenia;
    }

    public void setOpis_zdarzenia(String opis_zdarzenia) {
        this.opis_zdarzenia = opis_zdarzenia;
    }
}
