
/**
 * Week 6 Practice Question from Kattis
 * Problem A: Ferry Loading IV
 */

import java.util.*;
import java.io.*;

// Idea: Use a stack to store every opening. For every closing, it should match its previous one, otherwise it is an error

public class ferry {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    int num_test = Integer.parseInt(br.readLine());
    for (int i = 0; i < num_test; i++) {
      String[] l_m = br.readLine().split(" ");
      int L = Integer.parseInt(l_m[0]);
      int M = Integer.parseInt(l_m[1]);
      // number of actions
      int current = 0;
      for (int j = 0; j < M; j++) {
        String[] dist_dir = br.readLine().split(" ");
        int dist = Integer.parseInt(dist_dir[0]);
        String dir = dist_dir[1];

      }
    }
  }
}