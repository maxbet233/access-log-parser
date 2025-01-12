package ru.сourses.town;

public class Departament {
    private final String department;
    private final String boss;

    public Departament(String department, String boss) {
        this.department = department;
        this.boss = boss;
    }

    public String getDepartment() {
        return this.department;
    }

    public String getBoss() {
        return this.boss;
    }
}
