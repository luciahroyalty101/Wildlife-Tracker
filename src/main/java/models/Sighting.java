package models;

import java.sql.Timestamp;
import java.util.Date;

public class Sighting {
    private String animalName;
    private int rangerid;
    private String location;
    private Timestamp timestamp;
    private int id;


    public Sighting(String animalName, String location, int rangerid) {
        this.animalName = animalName;
        this.location = location.trim();
        this.timestamp = new Timestamp(new Date().getTime());
        this.rangerid = rangerid;
    }
}
