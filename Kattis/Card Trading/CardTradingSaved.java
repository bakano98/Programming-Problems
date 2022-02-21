/*
Name: Law Wei Jie
Student Number: A0218249Y
Take-Home Assignment: Card Trading
*/

/* Finished attempt 1. Combination does not work, takes way too long since it scales exponentially.. */
import java.util.*;
import java.io.*;

public class CardTradingSaved {
    public static List<String> combination = new ArrayList<>();

    public static void main(String[] args) {
        /* BufferedReader for reading input */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /* PrintWriter for printing output */
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        /* Corresponding variables.. */
        int N, T, K;
        String firstLine = "";
        try {
            firstLine = br.readLine();
        } catch (IOException e) {
        }
        String[] firstLineInput = firstLine.split(" ");

        /* Number of cards */
        N = Integer.parseInt(firstLineInput[0]);
        /* Number of types of cards */
        T = Integer.parseInt(firstLineInput[1]);
        /* Number of combos we must have */
        K = Integer.parseInt(firstLineInput[2]);
        /* Read the next line, which is the cards info */
        String cardsInfo = "";
        try {
            cardsInfo = br.readLine();
        } catch (IOException e) {
        }
        String[] cardsInfoLine = cardsInfo.split(" ");
        /*
         * Initialise an array of size T, where each index represents the type of card,
         * and the value represents the number of cards. At the same time, also read the
         * subsequent T input lines.
         */
        int[] cards = new int[T];
        int[] sellPrice = new int[T];
        int[] buyPrice = new int[T];
        /*
         * Read the subsequent T lines and place the buy/sell values into its
         * corresponding position in the two arrays
         */
        for (int i = 0; i < T; i++) {
            cards[i] = 0;
            String readLine = "";
            try {
                readLine = br.readLine();
            } catch (IOException e) {
            }
            String[] buy_and_sell = readLine.split(" ");
            sellPrice[i] = Integer.parseInt(buy_and_sell[1]);
            buyPrice[i] = Integer.parseInt(buy_and_sell[0]);
        }

        /* Loop to get all the cards */
        for (int i = 0; i < N; i++) {
            int cardNumber = Integer.parseInt(cardsInfoLine[i]);
            /* minus 1 because we start from 1 */
            cards[cardNumber - 1]++;
        }
        /*
         * Create two arrays of size T, where the value tells us the type of card, and
         * whether it should be bought or not
         */
        String[] cardsType = new String[T];
        for (int i = 0; i < T; i++) {
            Integer x = i;
            cardsType[i] = x.toString();
        }

        /*
         * Gets us K-distinct combinations, and places it into an ArrayList defind
         * earlier.
         */
        for (int i = 0; i < cardsType.length - K + 1; i++) {
            StringBuilder current = new StringBuilder("");
            current = current.append(cardsType[i]); // feeds each i to Combination, until the 2nd last item
            String[] remainder = Arrays.copyOfRange(cardsType, i + 1, cardsType.length);
            Combination(remainder, current, "", K - 1, K);
        }

        int profit = Integer.MIN_VALUE;
        ArrayList<String> AL = new ArrayList<>(combination);
        Object[] ALarray = AL.toArray();
        /*
         * Buy every Combination, and sell the rest. Highest profit is the final output.
         * Number of times to loop is the length of the list
         */
        for (int i = 0; i < ALarray.length; i++) {
            /*
             * Typecasting to String, then splitting it into its tokens. Values in the
             * array, s, represent the index to buy from
             */
            boolean[] tempCardsBool = new boolean[T];
            int tempProfit = 0;
            // split each Combination into it's
            String current = (String) ALarray[i];
            String s[] = current.split(" ");
            for (int j = 0; j < s.length; j++) {
                int cardNumber = Integer.parseInt(s[j]);
                /*
                 * K - cards[cardNumber] is the number of cards we need to buy, in order to
                 * satisfy K combos.
                 */
                tempProfit = tempProfit - (2 - cards[cardNumber]) * buyPrice[cardNumber];
                tempCardsBool[cardNumber] = true;
            }
            // do the selling action here
            for (int j = 0; j < tempCardsBool.length; j++) {
                if (tempCardsBool[j]) {
                    // true means it has been bought
                } else {
                    tempProfit = tempProfit + (cards[j] * sellPrice[j]);
                }
            }

            if (tempProfit > profit) {
                profit = tempProfit;
            }
            // otherwise, move on to the next iteration
        }

        pw.println(profit);
        pw.close();
    }

    public static void Combination(String[] remainder, StringBuilder current, String prev, int k, int initial) {
        if (k == 0) {
            if (current.length() > current.length() + initial) {
                // do nothing
            } else {
                combination.add(current.toString());
            }
        } else {
            /*
             * We pass in the ar ray [0, 1, 2, 3] current = "" next = "" k = 2
             */
            // k is the number of times to concatenate to the empty string...
            // we want to end at 2nd last index, so array.length-1
            prev = current.toString();
            for (int i = 0; i < remainder.length; i++) {
                String[] remaining = Arrays.copyOfRange(remainder, i + 1, remainder.length);
                if (current.length() < initial) {
                    current = current.append(" ").append(remainder[i]);
                    // we want to call Combination for every single current..
                    Combination(remaining, current, prev, k - 1, initial);
                } else {
                    StringBuilder temp = new StringBuilder(prev);
                    current = temp.append(" ").append(remainder[i]);
                    Combination(remaining, current, prev, k - 1, initial);
                }
            }

        }
    }
}