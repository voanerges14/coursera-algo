import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private final List<LineSegment> lineSegmentList;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        validate(points);
        lineSegmentList = new ArrayList<>();

        int n = points.length;
        for (int i = 0; i < n; i++) {
            Point[] pointsCopy = Arrays.copyOfRange(points, i + 1, n);
            Arrays.sort(pointsCopy, points[i].slopeOrder());

            List<Point> segmentPoints = new ArrayList<>();

            for (int j = 0; j < pointsCopy.length - 1; j++) {
                if (points[i].slopeTo(pointsCopy[j]) == points[i].slopeTo(pointsCopy[j + 1])) {
                    segmentPoints.add(pointsCopy[j]);
                } else if (!segmentPoints.isEmpty()) {
                    segmentPoints.add(pointsCopy[j]);

                    if (segmentPoints.size() > 2) {
                        segmentPoints.add(points[i]);
                        lineSegmentList.add(makeSegment(segmentPoints));
                    }
                    segmentPoints.clear();
                }
            }

            if (!segmentPoints.isEmpty()) {
                segmentPoints.add(pointsCopy[pointsCopy.length - 1]);

                if (segmentPoints.size() > 2) {
                    segmentPoints.add(points[i]);
                    lineSegmentList.add(makeSegment(segmentPoints));
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegmentList.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegmentList.toArray(new LineSegment[0]);
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

    private LineSegment makeSegment(List<Point> points) {
        points.sort(Point::compareTo);
        return new LineSegment(points.get(0), points.get(points.size()-1));
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);

        StdOut.println("time: " + (System.currentTimeMillis() - time) + "ms");

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}