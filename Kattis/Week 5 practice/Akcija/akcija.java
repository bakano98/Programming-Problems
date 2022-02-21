
/**
 * Name: Law Wei Jie
 * Kattis Practice : akcija
 */

import java.util.*;
import java.io.*;

public class akcija {
  public static void main(String[] args) throws IOException {
    // IO read/write
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    // number of books has to be between 1 and 3
    // every 3 books, cheapest 1 is free
    // N is the number of books
    // idea: put all inputs into list, sort in reverse order, then group in 3
    int price = 0;
    List<Integer> al = new ArrayList<>();
    for (int i = 1; i < N + 1; i++) {
      int currentBook = Integer.parseInt(br.readLine());
      al.add(currentBook);
    }
    Collections.sort(al);
    Collections.reverse(al);
    for (int i = 1; i < al.size() + 1; i++) {
      if (i % 3 == 0) {
        // do nothing
      } else {
        price = price + al.get(i - 1);
      }
    }
    pw.println(price);
    pw.flush();
  }
}