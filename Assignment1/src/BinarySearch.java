import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 6, 9, 5, 8};
        Arrays.sort(arr);
        int tar = binaryIterativeSearch(arr, 1);
        System.out.println(tar);

        int tar1 = binaryRecursiveSearch(arr, 8, 0, arr.length - 1);
        System.out.println(tar1);

    }

    public static int binaryIterativeSearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return arr[mid];
            }
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;

    }

    public static int binaryRecursiveSearch(int[] arr, int target, int low, int high) {

        if (high >= low) {
            int mid = low + (high - low) / 2;

            // If the element is present at the middle itself
            if (arr[mid] == target) {
                return arr[mid];
            }

            // If element is smaller than mid, then it can only be in the left subarray
            if (arr[mid] > target) {
                return binaryRecursiveSearch(arr,target, low, mid - 1);
            }

            // Else the element can only be in the right subarray
            return binaryRecursiveSearch(arr, target,mid + 1, high);
        }
        return -1;

    }

}
