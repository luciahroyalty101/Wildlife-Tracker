package models;

import org.junit.Rule;

import static org.junit.Assert.*;

public class NormalAnimalTest {
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    private NormalAnimal newAnimal() {
        return new NormalAnimal("Goat","Healthy","Young");
    }

    private class DatabaseRule {
    }
}