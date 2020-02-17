package Geometry;

public class Point {
    double X = 0;
    double Y = 0;

    Point(double X_, double Y_) {
        X = X_;
        Y = Y_;
    }

    public double DistBetween(Point OtherPoint) {
        /* Distance between two 2D points:
         *  dist = sqrt((X1 - X2)^2 - (Y1 - Y2)^2)
         *
         *  retval -- double, the distance between the point, which is defined by this class' instance
         *  args   -- the other Geometry.Point instance
         * */

        return Math.sqrt( Math.pow( X - OtherPoint.X, 2 ) + Math.pow( Y - OtherPoint.Y, 2 ) );
    }
}


