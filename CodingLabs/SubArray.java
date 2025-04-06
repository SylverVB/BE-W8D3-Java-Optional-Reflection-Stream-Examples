package CodingLabs;

public class SubArray {
    /**
     * Returns a sub-array from the given array. For example, given the array {1, 2, 3, 4, 5},
     * a sub-array with start index 1 and end index 4 would return {2, 3, 4}.
     * The start index is inclusive, and the end index is exclusive.
     *
     * A new array is created with the size equal to (end - start), and values are copied from
     * the original array using a loop.
     *
     * @param nums the original array of integers.
     * @param start the starting index (inclusive).
     * @param end the ending index (exclusive).
     * @return a new sub-array containing elements from start to end - 1.
     * @throws IllegalArgumentException if indices are out of bounds or invalid.
     */
    public int[] sub(int[] nums, int start, int end){
        // Validate input range
        if (start < 0 || end > nums.length || start >= end) {
            throw new IllegalArgumentException("Invalid start or end indices.");
        }

        int[] subArray = new int[end - start];

        for (int i = start; i < end; i++) {
            subArray[i - start] = nums[i];
        }

        return subArray;
    }

    public static void main(String[] args) {
        SubArray sa = new SubArray();
        int[] nums = {1, 2, 3, 4, 5};

        // Test cases
        int[] result = sa.sub(nums, 1, 4);
        for (int num : result) {
            System.out.print(num + " "); // Output: 2 3 4
        }
    }
}