package models;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2oException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;
import java.util.Objects;

public class Sighting {
    private String animalName;
    private String rangerName;
    private String location;
//    public Timestamp timestamp;
    private int id;

//    public Timestamp getTimestamp() {
//        return timestamp;
//    }

    public Sighting(String animalName, String rangerName, String location) {
        this.animalName = animalName;
        this.rangerName = rangerName;
        this.location = location;
//        this.timestamp = timestamp;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getRangerName() {
        return rangerName;
    }

    public void setRangerName(String rangerName) {
        this.rangerName = rangerName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

//    public void setTimestamp(Timestamp timestamp) {
//        this.timestamp = new Timestamp(timestamp.getTime());
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return animalName.equals(sighting.animalName) &&
                rangerName == sighting.rangerName &&
                Objects.equals(location, sighting.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalName, location, rangerName);
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getLocation() {
        return location;
    }

//    public Timestamp getTimestamp() {
//        return timestamp;
//    }

//    public Timestamp getTime_sighted() {
//        return time_sighted;
//    }
//    public String getReadableTimestamp(){
//
//        return DateFormat.getDateTimeInstance().format(getTimestamp());
//    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public void save(){
        String sql = "INSERT INTO sightings(animalname,location,rangername) values (:animalname,:location,:rangername)";
        try(Connection con = DB.sql2o.open()){
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("animalname", this.animalName)
                    .addParameter("location",this.location)
                    .addParameter("rangername",this.rangerName)
                    .executeUpdate()
                    .getKey();
            setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    public static List<Sighting> getAllSightings(){
        String sql = "SELECT * FROM sightings;";
        try (Connection con = DB.sql2o.open()){
            Query query =con.createQuery(sql);
            System.out.println(query.executeAndFetch(Sighting.class));
            return query.executeAndFetch(Sighting.class);
        }
    }

    public static Sighting find(int searchId){
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM sightings WHERE id=:id")
                    .addParameter("id",searchId)
                    .executeAndFetchFirst(Sighting.class);
        }
    }

    public static List<String> getAllLocations(){
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT location FROM sightings")
                    .executeAndFetch(String.class);
        }
    }

    public static List<Sighting> getAllSightingsInLocation(String locationFilter){
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM sightings where location = :location")
                    .addParameter("location",locationFilter)
                    .executeAndFetch(Sighting.class);
        }
    }

}
