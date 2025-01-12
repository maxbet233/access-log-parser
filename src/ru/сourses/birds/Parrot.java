package ru.сourses.birds;

import ru.сourses.birds.Bird;

import java.util.Random;
import java.util.Scanner;

public class Parrot extends Bird {
    @Override
    public void song() {

        Random random = new Random();
        int randomCount = random.nextInt(10);

        Scanner in = new Scanner(System.in);
        System.out.println("Введите текст песни: ");
        String input = in.next();
        System.out.println("Максимальное количество спетых символов: " + randomCount);

        if (randomCount == 0) {
            System.out.print(input.charAt(0));
        } else if (randomCount < input.length()) {
            for (int i = 0; i < randomCount; i++) {
                System.out.print(input.charAt(i));
            }
        } else System.out.println(input);
    }
}

