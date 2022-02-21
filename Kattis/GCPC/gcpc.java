
/**
 * Name: Law Wei Jie
 * Student Number: A0218249Y
 * Take Home Assignment 3B: Galactic Collegiate Programming Contest
 */

import java.util.*;
import java.io.*;

public class gcpc {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    String[] in = br.readLine().split(" ");
    int num_teams = Integer.parseInt(in[0]);
    int num_events = Integer.parseInt(in[1]);
    AVLTree tree = new AVLTree();
    StringBuilder sb = new StringBuilder();

    Team[] teams = new Team[num_teams];
    // adding the teams into our AVL tree
    for (int i = 0; i < num_teams; i++) {
      teams[i] = new Team(i, 0, 0);
      tree.insert(teams[i]);
    }
    for (int i = 0; i < num_events; i++) {
      String[] in_line = br.readLine().split(" ");
      // converting to 0 based indexing, easier to deal with
      int team_number = Integer.parseInt(in_line[0]) - 1;
      long penalty = Long.parseLong(in_line[1]);

      // then, delete that team
      tree.delete(teams[team_number]);
      // update the correct team
      teams[team_number].update(penalty);
      tree.insert(teams[team_number]);
      // rank returns the number of nodes smaller than it
      // rank is 1-based, so we need to +1 to offset it
      // append to a stringbuilder for better time(?)
      sb.append(num_teams + 1 - tree.rank(teams[0]));
      sb.append("\n");
    }
    pw.print(sb);
    pw.close();
  }
}