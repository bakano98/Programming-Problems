import java.util.*;
import java.util.Arrays;

class Combinations {
    public static List<String> combinationList = new ArrayList<>();

    public static void Combination(String[] remainder, String current, String prev, int k, int initial) {
        if (k == 0) {
            if (current.length() > initial) {
                // do nothing
            } else {
                combinationList.add(current);
            }
        } else {
            /*
             * We pass in the ar ray [0, 1, 2, 3] current = "" next = "" k = 2
             */
            // k is the number of times to concatenate to the empty string...
            // we want to end at 2nd last index, so array.length-1

            prev = current;
            for (int i = 0; i < remainder.length; i++) {
                String[] remaining = Arrays.copyOfRange(remainder, i + 1, remainder.length);
                if (current.length() < initial) {
                    current = current + remainder[i];
                    // we want to call Combination for every single current..
                    Combination(remaining, current, prev, k - 1, initial);
                } else {
                    current = prev + remainder[i];
                    Combination(remaining, current, prev, k - 1, initial);
                }
            }

        }
    }

    public static void main(String[] args) {
        String[] arr = new String[1000];
        for (int i = 0; i < arr.length; i++) {
            Integer x = i;
            arr[i] = x.toString();
        }
        // [0, 1, 2, 3]
        // 4 +1 - 2 = 3, so we'll stop at index 2..
        int k = 3;
        for (int i = 0; i < arr.length - k + 1; i++) {
            String current = arr[i]; // feeds each i to Combination, until the 2nd last item
            String[] remainder = Arrays.copyOfRange(arr, i + 1, arr.length);
            Combination(remainder, current, "", k - 1, k);
        }
        System.out.println(combinationList);
        // split the list, insert TRUE/FALSE values into an array
        // TRUE = buy, FALSE = sell
    }
}