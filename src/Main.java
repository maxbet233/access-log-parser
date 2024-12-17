import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Point dot1 = new Point(1, 5);
        Point dot2 = new Point(2, 8);
        Point dot3 = new Point(5, 3);
        Point dot4 = new Point(8, 9);

        Point[] arrDots = {dot1, dot2, dot3, dot4};

        PolyLine arrLine = new PolyLine(arrDots);

        System.out.println("Длина ломаной: " + arrLine.getLength());
        System.out.println(Arrays.toString(arrLine.getLines()));

        double sum = 0;
        for (int i = 0; i < arrLine.getLines().length; i++) {
            sum += arrLine.getLines()[i].getLength();
        }

        System.out.println("Длина массива линий: " + sum);
        System.out.println(arrLine.getLength() == sum);

        dot2.coordinate_x = 12;
        System.out.println(dot2);
        System.out.println(arrLine);
        System.out.println(Arrays.toString(arrLine.getLines()));

    }
}
