package ru.сourses.geometry;

import ru.сourses.geometry.Point;

public class Point3D extends Point {
    public int z;

    public Point3D(int coordinate_x, int coordinate_y, int z) {
        super(coordinate_x, coordinate_y);
        this.z = z;
    }
}
