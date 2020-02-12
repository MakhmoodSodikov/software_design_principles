/*
 *             ______A______
 *    MainVtx *             * B
 *          B |             |
 *            |             | B
 *          D *______A_____* C
 *
 */

package Geometry;

public class Rectangle {
    private Point MainVertex;
    private double SideSizeA, SideSizeB;
    private Point B, C, D;

    public Rectangle(Point A, double SideA, double SideB) {
        MainVertex = A;
        SideSizeA = SideA;
        SideSizeB = SideB;
        GenerateEachVtxCoord();
    }

    private void GenerateEachVtxCoord() {
        B = new Point(MainVertex.X + SideSizeA, MainVertex.Y);
        C = new Point(B.X, B.Y - SideSizeB);
        D = new Point(C.X - SideSizeA, C.Y);
    }

    public double ComputeSquare() {
        return SideSizeA * SideSizeB;
    }
}