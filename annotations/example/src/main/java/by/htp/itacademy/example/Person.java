package by.htp.itacademy.example;

import by.htp.itacademy.annotation.BuilderProperty;
import by.htp.itacademy.annotation.Time;

public class Person {

    private int age;

    private String name;

    public int getAge() {
        return age;
    }

    @BuilderProperty
    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @BuilderProperty
    public void setName(String name) {
        this.name = name;
    }
}
