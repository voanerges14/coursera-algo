import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class DutchNationalFlag {

    // 333-111-222
    public int[] sortBy3Values(int[] array) {
        int n = array.length;
        int last = n - 1;
        int first = 0;
        int mid = 0;
        while (mid <= last) {
            if (color(mid, array) == 3) {
                if (color(first, array) != 3) {
                    swap(mid, first, array);
                }
                first++;
                mid++;
            } else if (color(mid, array) == 2) {
                if (color(last, array) != 2) {
                    swap(mid, last, array);
                }
                last--;
            } else {
                mid++;
            }
        }
        return array;
    }

    private void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private int color(int i, int [] array) {
        return array [i];
    }

    public static void main(String[] args) {

        DutchNationalFlag dnf = new DutchNationalFlag();
//        int [] array = {1, 2, 3, 1, 2, 3};
        int [] array = {2,
                3,
                2,
                3,
                1,
                1,
                1,
                2,
                3,
                2,
                2,
                2,
                3,
                2,
                3,
                1,
                3,
                3,
                3,
                1,
                2,
                1,
                1};
        array = dnf.sortBy3Values(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
