import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
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
        for (int i = 0; i < n - 1; i++) {
            Map<Double, Set<Point>> segmentsMap = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                segmentsMap.merge(points[i].slopeTo(points[j]), new HashSet<>(Arrays.asList(points[i], points[j])),
                        (v1, v2) -> {
                            Set<Point> tempPoints = new HashSet<>();
                            tempPoints.addAll(v1);
                            tempPoints.addAll(v2);
                            return tempPoints;
                        });
            }
            lineSegmentList.addAll(segmentsMap.values().stream()
                    .filter(p -> p.size() >= 4)
                    .map(this::makeSegment)
                    .collect(Collectors.toList()));
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

    private LineSegment makeSegment(Set<Point> points) {
        Point[] p = points.toArray(new Point[0]);
        Arrays.sort(p);
        return new LineSegment(p[0], p[p.length-1]);
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