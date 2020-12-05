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
    @Override
    public int hashCode() {
        return Objects.hash(name, health, age, type);
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

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

}
