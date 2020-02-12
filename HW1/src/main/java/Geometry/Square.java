/*
 *             _____________
 *    MainVtx *             * B
 *            |             |
 *            |             |
 *            |             |
 *          D *_____________* C
 *
 */


package Geometry;

public class Square {
    private Point MainVertex;
    private double SideSize;
    private Point B, C, D;

    public Square(Point A, double Side) {
        MainVertex = A;
        SideSize = Side;
        GenerateEachVtxCoord();
    }

    private void GenerateEachVtxCoord() {
        B = new Point(MainVertex.X + SideSize, MainVertex.Y);
        C = new Point(B.X, B.Y - SideSize);
        D = new Point(C.X - SideSize, C.Y);
    }

    public double ComputeSquare() {
        return SideSize * SideSize;
    }
}