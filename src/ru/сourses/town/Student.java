package ru.сourses.town;

import java.util.Arrays;

public class Student {
    private final String name;
    private int[] grades;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, int[] grades) {
        for (int grade : grades) {
            if (grade < 2 || grade > 5)
                throw new IllegalArgumentException("the rating should be in the range from 2 to 5, but the array contains " + grade);
        }
        this.name = name;
        this.grades = Arrays.copyOf(grades, grades.length);
    }

    @Override
    public String toString() {
        return name + ": " + Arrays.toString(grades);
    }

    public void setGrades(int[] grades) {
        for (int grade : grades) {
            if (grade < 2 || grade > 5)
                throw new IllegalArgumentException("the rating should be in the range from 2 to 5, but the array contains " + grade);
        }
        this.grades = new Student(this.name, this.grades).grades;
    }

    public int[] getGrades() {
        return new Student(this.name, this.grades).grades;
    }
}
