package models;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

public class Sighting {
    private String animalName;
    private int rangerid;
    private String location;
    private Timestamp timestamp;
    private int id;


    public Sighting(String animalName, String location, int rangerid, int id) {
        this.animalName = animalName;
        this.location = location.trim();
        this.id = id;
        this.timestamp = new Timestamp(new Date().getTime());
        this.rangerid = rangerid;
    }
    @Override
    public int hashCode() {
        return Objects.hash(animalName, location, rangerid);
    }
    public String getAnimalName() {
        return animalName;
    }

    public String getLocation() {
        return location;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getReadableTimestamp(){
        return DateFormat.getDateTimeInstance().format(getTimestamp());
    }

    public int getRangerid() {
        return rangerid;
    }

    public int getId() {
        return id;
    }

}

