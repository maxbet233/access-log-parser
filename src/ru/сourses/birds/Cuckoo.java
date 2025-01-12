package ru.сourses.birds;

import ru.сourses.birds.Bird;

import java.util.Random;

public class Cuckoo extends Bird {
    @Override
    public void song() {
        for (int i = 0; i <= Math.random() * 9; i++) {
            System.out.println("ку-ку");
        }
    }
}
