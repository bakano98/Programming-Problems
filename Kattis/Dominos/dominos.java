
/**
 * Name: Law Wei Jie
 * Student Number: A0218249Y
 * Take Home Assignment 4B - Dominos
 */

/*
Key idea:
- Get toposort ordering
- DFS/BFS through that ordering
- The number of times DFS is executed = number of times we need to push
*/

import java.util.*;
import java.io.*;

public class dominos {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    int num_testcases = Integer.parseInt(br.readLine());
    // for printing later..
    StringBuilder sb = new StringBuilder();
    // do for number of test cases...
    for (int i = 0; i < num_testcases; i++) {
      String[] in_line = br.readLine().split(" ");
      int manual_pushes = 0;
      // n = number of dominos, from 1 to n
      int n = Integer.parseInt(in_line[0]);
      // m = number of subsequent lines
      int m = Integer.parseInt(in_line[1]);

      HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
      // for the actual toppling of dominos, visited means toppled over
      boolean[] visited = new boolean[n];
      // for toposorting
      ArrayList<Integer> topo_ordering = new ArrayList<>();
      boolean[] visited_toposort = new boolean[n];

      for (int j = 0; j < m; j++) {
        // read the dominos input, x and y
        // x, y determines the fall order
        // if x falls, y falls as well
        // x has an edge-out to y
        String[] testCase_in = br.readLine().split(" ");
        // changing to 0-based indexing
        int x = Integer.parseInt(testCase_in[0]) - 1;
        int y = Integer.parseInt(testCase_in[1]) - 1;

        // adding into adjList
        if (!adjList.containsKey(x)) {
          ArrayList<Integer> temp = new ArrayList<>();
          adjList.put(x, temp);
        }
        adjList.get(x).add(y);
      }

      // now we have our complete adjList for THIS testcase
      // make a toposort ordering by DFS
      for (int k = 0; k < n; k++) {
        if (!visited_toposort[k]) {
          // DFS for toposort order
          DFS(k, adjList, visited_toposort, topo_ordering);
        }
      }
      // reverse the toposort array to get the correct ordering
      Collections.reverse(topo_ordering);

      // now we have the toposort ordering, so simply perform DFS on every single
      // vertex (domino)
      for (int k = 0; k < n; k++) {
        int to_visit = topo_ordering.get(k);
        if (!visited[to_visit]) {
          manual_pushes++;
          DFS(to_visit, adjList, visited);
        }
      }

      // now we have the number of pushes required, just append to sb
      sb.append(manual_pushes);
      sb.append("\n");
    }

    pw.print(sb);
    pw.close();
  }

  // normal DFS
  public static void DFS(int current, HashMap<Integer, ArrayList<Integer>> adjList, boolean[] visited) {
    if (visited[current]) {
      return;
    } else {
      visited[current] = true;
      ArrayList<Integer> neighbours = adjList.get(current);
      if (neighbours != null) {
        for (Integer v : neighbours) {
          if (!visited[v]) {
            DFS(v, adjList, visited);
          }
        }
      }
    }
  }

  // DFS for getting toposort ordering (before reversing)
  public static void DFS(int current, HashMap<Integer, ArrayList<Integer>> adjList, boolean[] visited,
      ArrayList<Integer> topo_ordering) {
    if (visited[current]) {
      return;
    } else {
      visited[current] = true;
      ArrayList<Integer> neighbours = adjList.get(current);
      if (neighbours != null) {
        for (Integer v : neighbours) {
          if (!visited[v]) {
            DFS(v, adjList, visited, topo_ordering);
          }
        }
      }
      // add to the list containing the toposort ordering
      topo_ordering.add(current);
    }
  }
}