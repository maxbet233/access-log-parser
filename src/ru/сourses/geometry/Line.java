package ru.сourses.geometry;


import ru.сourses.main.Main;

public class Line implements Main.Measurable {
    Point point1, point2;

    public Line(Point start, Point end) {
        this.point1 = start;
        this.point2 = end;
    }

    public Line(int x1, int y1, int x2, int y2) {
        this.point1 = new Point(x1, y1);
        this.point2 = new Point(x2, y2);
    }

    public String toString() {
        return "Линия от {" + point1.coordinate_x + ";" + point1.coordinate_y + "} до {" + point2.coordinate_x + ";" + point2.coordinate_y + "}";
    }

    public double getLength() {
        double len = ((point2.coordinate_x - point1.coordinate_x) * (point2.coordinate_x - point1.coordinate_x)) + ((point2.coordinate_y - point1.coordinate_y) * (point2.coordinate_y - point1.coordinate_y));
        return Math.sqrt(len);
    }
}


