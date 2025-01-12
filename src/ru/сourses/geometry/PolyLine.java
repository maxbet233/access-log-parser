package ru.сourses.geometry;

import ru.сourses.Main;
import ru.сourses.geometry.Line;
import ru.сourses.geometry.Point;

import java.util.Arrays;

public class PolyLine implements Main.Measurable {
    Point[] arrPoints;

    public PolyLine(Point[] arrPoint) {
        this.arrPoints = arrPoint;
    }

    public PolyLine() {
    }

    public String toString() {
        return "Линия " + Arrays.toString(arrPoints);
    }

    public Line[] getLines() {
        Line[] arrLines = new Line[this.arrPoints.length - 1];
        for (int i = 0; i < this.arrPoints.length - 1; i++) {
            arrLines[i] = new Line(this.arrPoints[i], this.arrPoints[i + 1]);
        }
        return arrLines;
    }

    public double getLength() {
        Line[] arrLines = new Line[this.arrPoints.length - 1];
        double len = 0;
        for (int i = 0; i < this.arrPoints.length - 1; i++) {
            arrLines[i] = new Line(this.arrPoints[i], this.arrPoints[i + 1]);
            len += arrLines[i].getLength();
        }
        return len;
    }
}