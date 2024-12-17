public class Point {
    int coordinate_x, coordinate_y;

    public Point(int coordinate_x, int coordinate_y) {
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
    }

    public String toString(){
        return "{"+coordinate_x+";"+coordinate_y+"}";
    }
}