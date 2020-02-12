package Geometry;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    @org.junit.jupiter.api.Test
    void distBetween() {
        ArrayList<Point> ListOfPoints = new ArrayList<Point>(3);
        ListOfPoints.add(0, new Point(3,4));
        ListOfPoints.add(1, new Point(12,5));
        ListOfPoints.add(2, new Point(1,1));

        ArrayList<Double> ExpectedSquares = new ArrayList<Double>(3);
        ExpectedSquares.add(0, (double) 5);
        ExpectedSquares.add(1, (double) 13);
        ExpectedSquares.add(2, Math.sqrt(2));

        Point CenterOfCoordinates = new Point(0,0);

        for (int i = 0; i < 3; ++i) {
            double dist = CenterOfCoordinates.DistBetween(ListOfPoints.get(i));
            assertEquals(dist, ExpectedSquares.get(i));
        }
    }
}