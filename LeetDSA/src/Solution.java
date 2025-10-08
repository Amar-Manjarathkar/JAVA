import java.util.Arrays; // Already included, which is great!

class Solution {
    // ... your successfulPairs method is correct and goes here ...
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] pairs = new int[n];
        Arrays.sort(potions);
        for (int i = 0; i < n; i++) {
            int spell = spells[i];
            long minPotionStrength = (success + spell - 1) / spell;
            int left = 0;
            int right = m;
            int firstValidIndex = m;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if ((long) potions[mid] >= minPotionStrength) {
                    firstValidIndex = mid;
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            pairs[i] = m - firstValidIndex;
        }
        return pairs;
    }

    public static void main(String[] args) {
        int[] spells = {5, 1, 3};
        int[] potions = {1, 2, 3, 4, 5};
        long success = 7;

        // Call Arrays.toString() on the result before printing
        System.out.println(Arrays.toString(successfulPairs(spells, potions, success)));
    }
}