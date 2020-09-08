import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Permutation {

    public boolean isEqualContentSetImpl(int [] a, int [] b) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            map.merge(a[i], 1, Integer::sum);
            map.merge(b[i], 1, Integer::sum);
        }
        return map.values().stream().mapToInt(Integer::intValue).sum() == 2 * n;
    }

    public boolean isEqualContentSortImpl(int [] a, int [] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

        public static void main(String[] args) {

        Permutation permutation = new Permutation();
        int [] a = {1, 4, 5, 8, 12};
        int [] b = {5, 12, 8, 4, 1};
        int [] c = {5, 12, 5, 4, 1};
            System.out.println("set a b " + permutation.isEqualContentSetImpl(a, b));
            System.out.println("set a c " + permutation.isEqualContentSetImpl(a, c));
            System.out.println("sort a b " + permutation.isEqualContentSortImpl(a, b));
            System.out.println("sort a c " + permutation.isEqualContentSortImpl(a, c));


        }
}
