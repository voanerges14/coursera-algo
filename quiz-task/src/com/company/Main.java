package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        int[] array = {-25, -10, -7, -3, 2, 4, 8, 10};
        System.out.println(main.threeSumCount(array));

        int [] array_1 = {2, 3, 5, 7, 9, 11, 13, 4, 1, 0};
        int value = 4;

        int left = 0;
        int right = array_1.length;

        // print "value found" is the desired value is in the bitonic array
        main.bitonicSearch(array, left, right, value);
    }

    public int threeSumCount(int[] array) {
        Arrays.sort(array);
        int n = array.length;
        int count = 0;
        for (int i = 0; i < n - 2; i++) {
            int a = array[i];
            int start = i + 1;
            int end = n - 1;
            while (start < end) {
                int b = array[start];
                int c = array[end];
                if (a + b + c == 0) {
                    count++;
                    start++;
                    end--;
                } else if (a + b + c > 0) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return count;
    }

    private void ascendingBinarySearch(int[] array, int left, int right, int value) {

        // empty interval
        if (left == right) {
            return;
        }

        // look at the middle of the interval
        int mid = (right + left) / 2;
        if (array[mid] == value) {
            System.out.println("value found");
            return;
        }

        // interval is not splittable
        if (left + 1 == right) {
            return;
        }

        if (value > array[mid]) {
            ascendingBinarySearch(array, mid + 1, right, value);
        } else {
            ascendingBinarySearch(array, left, mid, value);
        }
    }

    private void descendingBinarySearch(int[] array, int left, int right, int value) {
        // cout << "descending_binary_search: " << left << " " << right << endl;

        // empty interval
        if (left == right) {
            return;
        }

        // look at the middle of the interval
        int mid = (right + left) / 2;
        if (array[mid] == value) {
            System.out.println("value found");
            return;
        }

        // interval is not splittable
        if (left + 1 == right) {
            return;
        }

        if (value < array[mid]) {
            descendingBinarySearch(array, mid + 1, right, value);
        } else {
            descendingBinarySearch(array, left, mid, value);
        }
    }

    private void bitonicSearch(int[] array, int left, int right, int value) {
        // cout << "bitonic_search: " << left << " " << right << endl;
        // empty interval
        if (left == right) {
            return;
        }

        int mid = (right + left) / 2;
        if (array[mid] == value) {
            System.out.println("value found");
            return;
        }

        // not splittable interval
        if (left + 1 == right) {
            return;
        }

        if (array[mid] > array[mid - 1]) {
            if (value > array[mid]) {
                bitonicSearch(array, mid + 1, right, value);
                return;
            } else {
                ascendingBinarySearch(array, left, mid, value);
                descendingBinarySearch(array, mid + 1, right, value);
            }
        } else {
            if (value > array[mid]) {
                bitonicSearch(array, left, mid, value);
            } else {
                ascendingBinarySearch(array, left, mid, value);
                descendingBinarySearch(array, mid + 1, right, value);
            }
        }
    }
}
