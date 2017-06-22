package com.automationrhapsody.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Employee {
    private String firstName;
    private String lastName;
    private Position position;
    private List<String> skills;
    private int salary;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Position position, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
    }

    public void setSkills(String... skills) {
        this.skills = Arrays.stream(skills).collect(Collectors.toList());
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
