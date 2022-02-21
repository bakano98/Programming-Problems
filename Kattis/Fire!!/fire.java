// basically just shortest path to out wherever is out of bounds.

import java.util.*;
import java.io.*;

public class fire {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    // first line is the grid size
    String[] in = br.readLine().split(" ");
    final int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    int row = Integer.parseInt(in[0]);
    int col = Integer.parseInt(in[1]);
    int j_counter = 1; // always starts at 1
    int time = 0;
    boolean exit = false;

    char[][] grid = new char[row][col];
    for (int i = 0; i < row; i++) {
      String line = br.readLine();
      for (int j = 0; j < col; j++) {
        grid[i][j] = line.charAt(j);
      }
    }
    // grid successfully inserted into grid info
    // each move is 1min
    // if grid[i][j] == #, then cannot move
    // let J move first, then F move. both can move up, down, left and right
    // if J does not exist anymore, then impossible to move
    while (j_counter != 0 && exit == false) {

      time++;
    }

    if (!exit) {
      pw.println("IMPOSSIBLE");
    }

  }
}