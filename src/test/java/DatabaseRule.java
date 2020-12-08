import models.DB;
import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;


public class DatabaseRule extends ExternalResource {

    private int name;
    private int id;
    private int age;
    private String health;
    private String type;

    protected void before() {
        DB.sql2o = new org.sql2o.Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test","moringa","lucy");
    }

    protected void after() {
        try(Connection con = DB.sql2o.open()) {
            String deleteAnimalQuery = "DELETE FROM animals;";
            String deleteSightingQuery = "DELETE FROM sightings;";
            String deleteRangerQuery = "DELETE FROM rangers;";
            con.createQuery(deleteAnimalQuery).executeUpdate();
            con.createQuery(deleteSightingQuery).executeUpdate();
            con.createQuery(deleteRangerQuery).executeUpdate();
        }
    }
    public void save(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals(name,health, age, type) values (:name,:health,:age,:type)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name", this.name)
                    .addParameter("health", this.health)
                    .addParameter("age",this.age)
                    .addParameter("type",this.type)
                    .executeUpdate()
                    .getKey();
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    public static List<String> allAnimalNames(){
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT name FROM animals")
                    .executeAndFetch(String.class);
        }
    }
}