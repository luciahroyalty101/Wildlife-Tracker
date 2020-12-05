package models;

import java.util.Objects;

public class Animal {
    public int id;
    public String name;
    public String health;
    public String age;
    public String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return  Objects.equals(name, animal.name) &&
                Objects.equals(health, animal.health) &&
                Objects.equals(age, animal.age) &&
                Objects.equals(type, animal.type);
    }
}
