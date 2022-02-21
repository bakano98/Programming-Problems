
// Key idea: use BFS to try and find the shortest path to the coordinate
// Current implementation works, but TLE..
import java.util.*;
import java.io.*;

public class grasshopper {
  public static void main(String[] args) throws IOException {

    final int[][] directions = { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { 1, -2 }, { -1, 2 },
        { -1, -2 } };
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    String in = null;
    while ((in = br.readLine()) != null) {
      String[] input_line = in.split(" ");
      // the grid size
      int gridRow = Integer.parseInt(input_line[0]);
      int gridCol = Integer.parseInt(input_line[1]);
      // the current position greg is in
      int gregRow = Integer.parseInt(input_line[2]) - 1;
      int gregCol = Integer.parseInt(input_line[3]) - 1;
      Triplet gregCoordinates = new Triplet(gregRow, gregCol, 0);
      // the destination
      int delicacyRow = Integer.parseInt(input_line[4]) - 1;
      int delicacyCol = Integer.parseInt(input_line[5]) - 1;
      int current = 0;
      boolean[][] visited = new boolean[gridRow][gridCol];
      Queue<Triplet> queue = new LinkedList<>();
      queue.add(gregCoordinates);
      while (!queue.isEmpty()) {

        Triplet curr = queue.poll();
        int currRow = curr.first();
        int currCol = curr.second();
        current = curr.getCount();
        visited[currRow][currCol] = true;
        if (visited[delicacyRow][delicacyCol]) {
          break;
        }
        for (int[] v : directions) {
          int nextRow = currRow + v[0];
          int nextCol = currCol + v[1];
          if (nextRow < 0 || nextRow >= gridRow) {
            continue;
          }

          if (nextCol < 0 || nextCol >= gridCol) {
            continue;
          }

          if (visited[nextRow][nextCol]) {
            continue;
          }

          // otherwise just enqueue
          queue.add(new Triplet(nextRow, nextCol, current + 1));
        }
      }
      if (visited[delicacyRow][delicacyCol]) {
        pw.println(current);
      } else {
        pw.println("impossible");
      }
      pw.flush();
    }
    pw.close();
  }
}
