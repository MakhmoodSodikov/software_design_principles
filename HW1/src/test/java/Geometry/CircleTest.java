package Geometry;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @Test
    void computeSquare() {
        Point CenterOfCoordinates = new Point(0,0);

        ArrayList<Circle> ListOfSquares= new ArrayList<Circle>(3);
        ListOfSquares.add(0, new Circle(4, CenterOfCoordinates));
        ListOfSquares.add(1, new Circle(1, CenterOfCoordinates));
        ListOfSquares.add(2, new Circle(20, CenterOfCoordinates));

        ArrayList<Double> ExpectedSquares = new ArrayList<Double>(3);
        ExpectedSquares.add(0, Math.PI * 16);
        ExpectedSquares.add(1, Math.PI);
        ExpectedSquares.add(2, Math.PI * 400);

        for (int i = 0; i < 3; ++i) {
            double square = ListOfSquares.get(i).ComputeSquare();
            assertEquals(square, ExpectedSquares.get(i));
        }
    }
}