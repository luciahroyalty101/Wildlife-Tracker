package models;

import org.junit.Rule;

import static org.junit.Assert.*;

public class EndangeredAnimalTest<DatabaseRule> {
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    private EndangeredAnimal newAnimal() {
        return new EndangeredAnimal("Goat","Healthy","Young");
    }

}