package ru.сourses.main;

import ru.сourses.birds.Cuckoo;
import ru.сourses.birds.Parrot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
//        ru.сourses.town.City a = new ru.сourses.town.City("A");
//        ru.сourses.town.City b = new ru.сourses.town.City("B");
//        ru.сourses.town.City c = new ru.сourses.town.City("C");
//        ru.сourses.town.City d = new ru.сourses.town.City("D");
//        ru.сourses.town.City e = new ru.сourses.town.City("E");
//        ru.сourses.town.City f = new ru.сourses.town.City("F");
//
//        ru.сourses.town.Route a1 = new ru.сourses.town.Route(b, 5);
//        ru.сourses.town.Route a2 = new ru.сourses.town.Route(d, 6);
//        ru.сourses.town.Route a3 = new ru.сourses.town.Route(f, 1);
//        ru.сourses.town.Route b1 = new ru.сourses.town.Route(a, 5);
//        ru.сourses.town.Route b2 = new ru.сourses.town.Route(c, 3);
//        ru.сourses.town.Route c1 = new ru.сourses.town.Route(b, 3);
//        ru.сourses.town.Route c2 = new ru.сourses.town.Route(d, 4);
//        ru.сourses.town.Route d1 = new ru.сourses.town.Route(c, 4);
//        ru.сourses.town.Route d2 = new ru.сourses.town.Route(e, 2);
//        ru.сourses.town.Route d3 = new ru.сourses.town.Route(a, 6);
//        ru.сourses.town.Route e1 = new ru.сourses.town.Route(f, 2);
//        ru.сourses.town.Route f1 = new ru.сourses.town.Route(e, 2);
//        ru.сourses.town.Route f2 = new ru.сourses.town.Route(b, 1);
//
//        LinkedList<ru.сourses.town.Route> routeListA = new LinkedList<>();
//        LinkedList<ru.сourses.town.Route> routeListB = new LinkedList<>();
//        LinkedList<ru.сourses.town.Route> routeListC = new LinkedList<>();
//        LinkedList<ru.сourses.town.Route> routeListD = new LinkedList<>();
//        LinkedList<ru.сourses.town.Route> routeListE = new LinkedList<>();
//        LinkedList<ru.сourses.town.Route> routeListF = new LinkedList<>();
//
//        routeListA.add(a1);
//        routeListA.add(a2);
//        routeListA.add(a3);
//        routeListB.add(b1);
//        routeListB.add(b2);
//        routeListC.add(c1);
//        routeListC.add(c2);
//        routeListD.add(d1);
//        routeListD.add(d2);
//        routeListD.add(d3);
//        routeListE.add(e1);
//        routeListF.add(f1);
//        routeListF.add(f2);
//
//        a.routes = routeListA;
//        b.routes = routeListB;
//        c.routes = routeListC;
//        d.routes = routeListD;
//        e.routes = routeListE;
//        f.routes = routeListF;
//
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(b.travelBy(3));

//        ru.сourses.geometry.Fraction f1 = new ru.сourses.geometry.Fraction(1,3);
//        ru.сourses.geometry.Fraction f2 = new ru.сourses.geometry.Fraction(2,5);
//        ru.сourses.geometry.Fraction f3 = new ru.сourses.geometry.Fraction(7,8);
//
//        System.out.println(f1.sum(f2).sum(f3).minus(5));

//        ru.сourses.geometry.Square sq = new ru.сourses.geometry.Square(33, 2, 1);
//        System.out.println(sq);
//        sq.setSideLength(2);
//        System.out.println(sq);
//        sq.setSquarePoint(new ru.сourses.geometry.Point(8,87));
//        System.out.println(sq);
//        sq.setSideLength(-12);
//        System.out.println(sq);

//        int[] marks = {2, 2, 2, 2, 2};
//        int[] marks2 = {3, 3, 3, 3, 3};
//        ru.сourses.town.Student student = new ru.сourses.town.Student("Maks", marks);
//        System.out.println(student);
//
//        student.setGrades(marks2);
//        System.out.println(student);
//
//        System.out.println(Arrays.toString(student.getGrades()));
//
//        System.out.println(student.getGrades()[0]);
//
//        marks[1] = 6;
//        student.setGrades(marks);
//        System.out.println(student);

//        ru.сourses.town.Departament dep = new ru.сourses.town.Departament("IT", "Валера");
//        ru.сourses.town.Departament dep2 = new ru.сourses.town.Departament("accounting", "Макс");
//        ru.сourses.town.Departament dep3 = new ru.сourses.town.Departament("economics", "Фёдор");
//        ru.сourses.town.Employee emp = new ru.сourses.town.Employee("Макс", dep2);
//        System.out.println(emp);

//        ru.сourses.geometry.Point3D p3 = new ru.сourses.geometry.Point3D(1, 2, 4);
//        System.out.println(p3);
//        System.out.println(p3.z);
//        p3.z = 12;
//        System.out.println(p3.z);

//        ru.сourses.geometry.Line line = new ru.сourses.geometry.Line(new ru.сourses.geometry.Point(2, 5), new ru.сourses.geometry.Point(7, 15));
//        ru.сourses.geometry.Point[] p = {new ru.сourses.geometry.Point(10, 10), new ru.сourses.geometry.Point(25, 25), new ru.сourses.geometry.Point(33, 35)};
//        ru.сourses.geometry.PolyLine polyLine = new ru.сourses.geometry.PolyLine(p);
//        ru.сourses.geometry.PolyLine closeP = new ru.сourses.geometry.ClosedPolyLine(p);
//
//        System.out.println(lenLine(line));
//        System.out.println(lenLine(polyLine));
//        System.out.println(lenLine(closeP));

//        ru.сourses.geometry.Fraction fraction = new ru.сourses.geometry.Fraction(10,3);
//
//        System.out.println(fraction.intValue());
//        System.out.println(fraction.longValue());
//        System.out.println(fraction.floatValue());
//        System.out.println(fraction.doubleValue());

//        System.out.println(sumAll(2, new ru.сourses.geometry.Fraction(3, 5), 2.3));
//        System.out.println(sumAll(3.6, new ru.сourses.geometry.Fraction(49, 12), 3, new ru.сourses.geometry.Fraction(3,2)));
//        System.out.println(sumAll( new ru.сourses.geometry.Fraction(1, 3), 1));


        songBird(new ru.сourses.birds.Sparrow(), new ru.сourses.birds.Cuckoo(), new ru.сourses.birds.Parrot(), new Cuckoo());

    }

//    static double lenLine(Measurable lenLine) {
//        return lenLine.getLength();
//    }

    public interface Measurable {
        double getLength();
    }

//    public static double sumAll(Number... nums) {
//        double res = 0;
//        for (Number n : nums) {
//            res += n.doubleValue();
//        }
//        return res;
//    }

    public interface Songable {
        void song();
    }

    public static void songBird(Songable... s) {
        for (Songable songable : s) songable.song();
    }

}
