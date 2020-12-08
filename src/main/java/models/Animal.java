package models;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class Animal {
    public int id;
    public String name;
    public String type;
    private final String DATABASE_TYPE = "animal";

    public Animal(String name) {
        this.name = name;
        this.setType(DATABASE_TYPE);
    }

    public void setType(String type){
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return  Objects.equals(name, animal.name);

    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void save(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals(name, type) values (:name,:type)";
            this.id = (int) con.createQuery(sql,true)
                    .throwOnMappingFailure(false)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
            setId(id);
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public static List<Animal> allAnimals(){
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM animals WHERE type ='animal'")
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animal.class);
        }
    }


}


