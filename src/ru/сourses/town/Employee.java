package ru.сourses.town;

import ru.сourses.town.Departament;

public class Employee {
    private final String name;
    private Departament dept;

    public Employee(String name, Departament dept) {
        this.name = name;
        this.dept = dept;
    }

    public Employee(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        if (name == dept.getBoss()) {
            return name + " начальник отдела " + dept.getDepartment();
        } else
            return name + " работает в отделе " + dept.getDepartment() + ", начальник которого " + dept.getBoss();
    }
}
