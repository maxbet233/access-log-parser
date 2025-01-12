package ru.сourses.geometry;

import ru.сourses.geometry.Line;
import ru.сourses.geometry.Point;
import ru.сourses.geometry.PolyLine;

public class ClosedPolyLine extends PolyLine {
    public ClosedPolyLine(Point[] arrPoint) {
        super(arrPoint);
    }

    @Override
    public double getLength() {
        int len = arrPoints.length;
        if (arrPoints.length > 2) {
            Line plusLine = new Line(arrPoints[len-1],arrPoints[0]);
            return super.getLength() + plusLine.getLength();
        }
        return super.getLength();
    }
}
