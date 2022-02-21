// check if one node is connected to another via some path
// works, but too slow. Find a faster way :)


import java.util.*;
import java.io.*;

public class ten {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();
    // first line gives us our matrix
    String[] read_mat = br.readLine().split(" ");
    int rows = Integer.parseInt(read_mat[0]);
    int cols = Integer.parseInt(read_mat[1]);
    int[][] mat = new int[rows][cols];
    for (int i = 0; i < rows; i++) {
      // read rows # of lines
      String row_line = br.readLine();
      for (int j = 0; j < cols; j++) {

        mat[i][j] = ((int) row_line.charAt(j)) - 48;
      }
    }

    // read num of queries
    int Q = Integer.parseInt(br.readLine());
    for (int i = 0; i < Q; i++) {
      String[] in_line = br.readLine().split(" ");
      // convert to 0-based indexing
      IntegerPair from = new IntegerPair(Integer.parseInt(in_line[0]) - 1, Integer.parseInt(in_line[1]) - 1);
      IntegerPair to = new IntegerPair(Integer.parseInt(in_line[2]) - 1, Integer.parseInt(in_line[3]) - 1);
      sb.append(BFS(from, to, rows, cols, mat));
      sb.append('\n');
    }

    pw.print(sb);
    pw.close();
  }

  public static String BFS(IntegerPair from, IntegerPair to, int rows, int cols, int[][] mat) {
    final int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // can move up, down, left or right only
    boolean[][] visited = new boolean[rows][cols];
    Queue<IntegerPair> queue = new LinkedList<>();
    int same_num = mat[from.getX()][from.getY()];
    queue.add(from);
    while (!queue.isEmpty()) {
      IntegerPair curr = queue.poll();
      int currentRow = curr.getX();
      int currentCol = curr.getY();
      // mark this current row/col as visited
      visited[currentRow][currentCol] = true;
      if (visited[to.getX()][to.getY()]) {
        break;
      }
      for (int[] dir : directions) {
        int nextRow = currentRow + dir[0];
        int nextCol = currentCol + dir[1];
        // bounds checking
        if (nextRow < 0 || nextRow >= rows) {
          continue;
        }

        if (nextCol < 0 || nextCol >= cols) {
          continue;
        }

        if (visited[nextRow][nextCol] || mat[nextRow][nextCol] != same_num) {
          continue;
        }

        // if all the above conditions are true, then add the next one to the queue
        queue.add(new IntegerPair(nextRow, nextCol));
      }
    }

    if (visited[to.getX()][to.getY()]) {
      return same_num == 1 ? "decimal" : "binary";
    } else {
      return "neither";
    }
  }
}