package ru.сourses.geometry;

import ru.сourses.geometry.Point;

public class Square {
    private Point squarePoint;
    private int sideLength;

    public Square(int x, int y, int sideLength) {
        if (sideLength <= 0) throw new IllegalArgumentException("The length of the side must be greater than zero.");
        this.squarePoint = new Point(x, y);
        this.sideLength = sideLength;
    }

    @Override
    public String toString() {
        return "Квадрат в точке " + squarePoint + " со стороной " + sideLength;
    }

    public void setSquarePoint(Point squarePoint) {
        this.squarePoint = squarePoint;
    }

    public void setSideLength(int sideLength) {
        if (sideLength <= 0) throw new IllegalArgumentException("The length of the side must be greater than zero.");
        this.sideLength = sideLength;
    }
}
