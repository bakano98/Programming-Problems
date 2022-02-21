
/**
 * Name: Law Wei Jie 
 * Student Number: A0218249Y 
 * Take-Home Assignment: Card Trading
 */

import java.util.*;
import java.io.*;

public class CardTrading {

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
        /**
         * Initialise an array of size T, where each index represents the type of card,
         * and the value represents the number of cards. At the same time, also read the
         * subsequent T input lines.
         */
        int[] cards = new int[T];
        Long[] sellPrice = new Long[T];
        Long[] buyPrice = new Long[T];

        /**
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
            buyPrice[i] = Long.parseLong(buy_and_sell[0]);
            sellPrice[i] = Long.parseLong(buy_and_sell[1]);
        }

        /* Loop to get all the cards */
        for (int i = 0; i < N; i++) {
            int cardNumber = Integer.parseInt(cardsInfoLine[i]);
            /* minus 1 because we start from 1 */
            cards[cardNumber - 1]++;
        }

        /**
         * Idea: Sell everything, then see how much it'll cost to buy every type until 2
         * cards
         */

        long profit = 0;
        long[] saleReversalLoss = new long[T];
        /**
         * This loop gets the profit from selling all the cards, and calculates how much
         * loss would be incurred if we were to reverse the sale.
         */
        for (int i = 0; i < T; i++) {
            /* Summing the profit of selling all cards */
            profit += cards[i] * sellPrice[i];
            /**
             * Then we add them together, and put them into an array. This array represents
             * how much we would lose, if we were to reverse the sale of the respective type
             * of cards
             */
            saleReversalLoss[i] = cards[i] * sellPrice[i] + (2 - cards[i]) * buyPrice[i];
        }

        /**
         * To maximise profits, we want to reduce the amount of losses -- so we simply
         * sort the array
         */

        Arrays.sort(saleReversalLoss);
        for (int i = 0; i < K; i++) {
            /* Then undo K amounts of sale. */
            profit -= saleReversalLoss[i];
        }
        pw.println(profit);
        pw.close();
    }
}