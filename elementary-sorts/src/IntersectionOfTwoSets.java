import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class IntersectionOfTwoSets {


    private static class Point {
        private int x;
        private int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public int getEqualItemsCountBasedOnSet(Point [] a, Point [] b) {
        Set<Point> equalItemSet = new HashSet<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            equalItemSet.add(a[i]);
            equalItemSet.add(b[i]);
        }
        return 2 * n - equalItemSet.size();
    }

    public static void main(String[] args) {
        Point [] a = {new Point(1,1), new Point(1,2), new Point(2,2), new Point(5,2)};
        Point [] b = {new Point(1,2), new Point(1,5), new Point(5,2), new Point(5,3)};
        System.out.println(new IntersectionOfTwoSets().getEqualItemsCountBasedOnSet(a, b));
    }
}
