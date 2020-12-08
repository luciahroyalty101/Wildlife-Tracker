package models;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class EndangeredAnimal {
    private int id;
    private String name;
    private String health;
    private String age;

    private static final String DB_TYPE = "Endangered";
    private String type;

    public EndangeredAnimal(String name, String health, String age) {

        this.name = name;
        this.health = health;
        this.age = age;
        this.setType(DB_TYPE);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static String getDbType() {
        return DB_TYPE;
    }

    public void save(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals(animalname,health,age, type) values (:animalname,:health, :age,:type)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("animalname", this.name)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
            setId(id);
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    public static List<EndangeredAnimal> all() {
        String sql = "SELECT * FROM animals where type='endangered'";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(EndangeredAnimal.class);
        }catch (Sql2oException ex){
            System.out.println(ex);
            return null;
        }
    }

    public static EndangeredAnimal find(int searchId) {
        String sql = "SELECT * FROM animals where (id=:id AND type='endangered')";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", searchId)
                    .addParameter("type", DB_TYPE)
                    .executeAndFetchFirst(EndangeredAnimal.class);
        }
    }
}