package Geometry;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void computeSquare() {
        Point CenterOfCoordinates = new Point(0,0);

        ArrayList<Rectangle> ListOfSquares= new ArrayList<Rectangle>(3);
        ListOfSquares.add(0, new Rectangle(CenterOfCoordinates,4, 10));
        ListOfSquares.add(1, new Rectangle(CenterOfCoordinates,5, 20));
        ListOfSquares.add(2, new Rectangle(CenterOfCoordinates,1, 12));

        ArrayList<Double> ExpectedSquares = new ArrayList<Double>(3);
        ExpectedSquares.add(0, (double) 40);
        ExpectedSquares.add(1, (double) 100);
        ExpectedSquares.add(2, (double) 12);

        for (int i = 0; i < 3; ++i) {
            double square = ListOfSquares.get(i).ComputeSquare();
            assertEquals(square, ExpectedSquares.get(i));
        }
    }
}