package ru.сourses.town;

import java.util.LinkedList;

public class City {
    String city;
    LinkedList<Route> routes;

    public City(String city) {
        this.city = city;
    }

    public City(String city, LinkedList<Route> routes) {
        this.city = city;
        this.routes = routes;
    }

    //    @Override
//    public String toString() {
//        return "Точка отправления:'" + city + '\'' +
//                ", связанные города и стоимость:" + routes +
//                '}';
//    }
    public String travelBy(int n) {
        City finalCity = new City("");
        for (int i = 1; i <= n; i++) {
            finalCity = routes.getFirst().town;
            routes = finalCity.routes;
        }
        return "Пункт назначения " + finalCity;

    }

}
