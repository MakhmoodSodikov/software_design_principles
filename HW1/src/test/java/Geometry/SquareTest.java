package Geometry;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {
    @Test
    void computeSquare() {
        Point CenterOfCoordinates = new Point(0,0);

        ArrayList<Square> ListOfSquares= new ArrayList<Square>(3);
        ListOfSquares.add(0, new Square(CenterOfCoordinates,4));
        ListOfSquares.add(1, new Square(CenterOfCoordinates,5));
        ListOfSquares.add(2, new Square(CenterOfCoordinates,1));

        ArrayList<Double> ExpectedSquares = new ArrayList<Double>(3);
        ExpectedSquares.add(0, (double) 16);
        ExpectedSquares.add(1, (double) 25);
        ExpectedSquares.add(2, (double) 1);

        for (int i = 0; i < 3; ++i) {
            double square = ListOfSquares.get(i).ComputeSquare();
            assertEquals(square, ExpectedSquares.get(i));
        }
    }
}