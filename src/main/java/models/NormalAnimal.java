package models;
import org.sql2o.Connection;
import java.util.List;

public class NormalAnimal extends Animal {
    private static final String DB_TYPE = "Not Endangered";

    public NormalAnimal(String name, String health, String age) {
        this.name = name;
        this.health = health;
        this.age = age;
        this.type = DB_TYPE;
    }
    }