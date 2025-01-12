package ru.сourses.town;

import ru.сourses.town.City;

public class Route {
    City town;
    int price;

    public Route(City town, int price) {
        this.town = town;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Пути: {" + town +
                ", стоимость=" + price +
                '}';
    }
}
