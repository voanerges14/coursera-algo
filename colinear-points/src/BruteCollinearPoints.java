import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

    private final List<LineSegment> lineSegmentList;

    public BruteCollinearPoints(Point[] points) {  // finds all line segments containing 4 points
        validate(points);

        lineSegmentList = new ArrayList<>();

        int n = points.length;
        Point[] subPoints = new Point[4];

        if (n >= 4) {
            for (int i = 0; i < n - 3; i++) {
                subPoints[0] = points[i];
                for (int j = i + 1; j < n - 2; j++) {
                    subPoints[1] = points[j];
                    for (int k = j + 1; k < n - 1; k++) {
                        subPoints[2] = points[k];
                        for (int l = k + 1; l < n; l++) {
                            subPoints[3] = points[l];
                            if (isCollinear(subPoints)) {
                                lineSegmentList.add(makeSegment(subPoints));
                            }
                        }
                    }
                }
            }
        }
    }

    // the number of line
    public int numberOfSegments() {
        return lineSegmentList.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegmentList.toArray(new LineSegment[0]);
    }

    private boolean isCollinear(Point... points) {
        return points[0].slopeTo(points[1]) == points[1].slopeTo(points[2])
                && points[1].slopeTo(points[2]) == points[0].slopeTo(points[3]);
    }

    private LineSegment makeSegment(Point... points) {
        Arrays.sort(points);
        return new LineSegment(points[0], points[3]);
    }

    private void validate(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            for (int j = i + 1; j < points.length; j++) {
                if (points[i] == points[j]) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        in.close();
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        long time = System.currentTimeMillis();
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        StdOut.println("time: " + (System.currentTimeMillis() - time) + "ms");

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}