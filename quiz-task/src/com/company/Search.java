package com.company;

public class Search {

    public int binarySearchLoop(int val, int[] arr) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (val == arr[mid]) {
                return arr[mid];
            }
            if (val < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public int binarySearchRecursion(int val, int[] arr, int left, int right) {
        if (left <= right) {
            int mid = (left + right) / 2;
            if (val == arr[mid]) {
                return arr[mid];
            }
            if (val < arr[mid]) {
                return binarySearchRecursion(val, arr, left, mid - 1);
            } else {
                return binarySearchRecursion(val, arr, mid + 1, right);
            }
        }
        return -1;
    }

    public int binarySearchRecursionDesc(int val, int[] arr, int left, int right) {
        if (left <= right) {
            int mid = (left + right) / 2;
            if (val == arr[mid]) {
                return arr[mid];
            }
            if (val < arr[mid]) {
                return binarySearchRecursionDesc(val, arr, mid + 1, right);
            } else {
                return binarySearchRecursionDesc(val, arr, left, mid - 1);
            }
        }
        return -1;
    }

    public int bitonicSearch(int val, int[] arr, int left, int right) {
        if (left <= right) {
            int mid = (left + right) / 2;
            if (val == arr[mid]) {
                return arr[mid];
            }
            if (val > arr[mid]) {
                if (arr[mid] > arr[mid - 1]) {
                    return bitonicSearch(val, arr, mid + 1, right);
                } else {
                    return bitonicSearch(val, arr, left, mid - 1);
                }
            } else {
                int res = binarySearchRecursion(val, arr, left, mid - 1);
                return res == -1 ? binarySearchRecursionDesc(val, arr, mid + 1, right) : res;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Search search = new Search();
        System.out
                .println(search.binarySearchLoop(4, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 12, 15, 18}));
        System.out.println(search.binarySearchRecursion(
                4, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 12, 15, 18}, 0, 10));

        System.out.println(search.binarySearchRecursionDesc(
                4, new int[]{20, 18, 15, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2}, 0, 12));
        System.out
                .println(search.bitonicSearch(12, new int[]{2, 3, 5, 7, 9, 11, 13, 4, 1, 0}, 0, 9));
    }

}
