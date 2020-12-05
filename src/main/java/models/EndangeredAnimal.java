package models;

import org.sql2o.Connection;
import java.util.List;

public class EndangeredAnimal {
    private static final String DB_TYPE = "Endangered";

    public EndangeredAnimal(String name, String health, String age) {
        this.name = name;
        this.health = health;
        this.age = age;
        this.type = DB_TYPE;
    }

}