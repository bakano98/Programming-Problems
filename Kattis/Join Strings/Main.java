
/**
 * Name: Law Wei Jie
 * Student Number: A0218249Y
 * Take Home Assignment 2: Join Strings
 */

import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    HashMap<Integer, Indexer> hm = new HashMap<>();

    // reading N, the number of strings
    int N = Integer.parseInt(br.readLine());

    String[] strings = new String[N];
    // reading the strings
    for (int i = 0; i < N; i++) {
      strings[i] = br.readLine();
      hm.put(i, new Indexer(i));
    }

    int first = 0;
    int second = 0;
    int last = 0;
    // reading the order, only N-1 inputs
    // main idea: store the indices to be printed in Indexers
    // then print according the the index in strings array
    for (int i = 0; i < N - 1; i++) {
      String[] tokens = br.readLine().split(" ");
      // convert to 0-based indexing
      first = Integer.parseInt(tokens[0]) - 1;
      second = Integer.parseInt(tokens[1]) - 1;
      last = first;
      hm.get(first).linkIndexer(hm.get(second));
    }

    // get the last Indexer, which is the first Indexer to start printing from
    Indexer lastIndexer = hm.get(last);
    while (lastIndexer.next != null) {
      pw.print(strings[lastIndexer.data]);
      // move to next
      lastIndexer = lastIndexer.next;
      // pw.flush();
    }
    // print the final one
    pw.print(strings[lastIndexer.data]);
    pw.close();
  }
}