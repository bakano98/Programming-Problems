
/**
 * Name: Law Wei Jie
 * Student Number: A0218249Y
 * Take Home Assignment 4A: Millionaire Madness
 */

// Idea: View this as a single-source shortest path problem
// Use Dijkstra's to "map" out that path
// Find the largest weight within that path
// Return that largest weight, and we are done

import java.util.*;
import java.io.*;

public class millionaire {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    // read first line
    String[] in = br.readLine().split(" ");
    // M = length = rows
    int rows = Integer.parseInt(in[0]);
    // N = width = cols
    int cols = Integer.parseInt(in[1]);

    int[][] vertexMapping = new int[rows][cols];
    int[][] weights = new int[rows][cols];
    // starts from 0
    int vertex = 0;
    // setup the vertexMapping and the corresponding weights
    for (int i = 0; i < rows; i++) {
      // read subsequent lines
      String[] in_line = br.readLine().split(" ");
      for (int j = 0; j < cols; j++) {
        vertexMapping[i][j] = vertex;
        vertex++;
        weights[i][j] = Integer.parseInt(in_line[j]);
      }
    }

    // next, set up adjList which tells us:
    // 1) the neighbours
    // 2) its corresponding weight (height) of ladder
    ArrayList<ArrayList<IntegerPair>> adjList = new ArrayList<>();
    // directions that we can move in are up, down, left, right
    int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    // fill the adjList
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        ArrayList<IntegerPair> temp = new ArrayList<>();
        for (int k = 0; k < 4; k++) {
          int nextRow = i + directions[k][0];
          int nextCol = j + directions[k][1];

          // invalid condition
          if (nextRow < 0 || nextRow >= rows) {
            continue;
          }

          // invalid condition
          if (nextCol < 0 || nextCol >= cols) {
            continue;
          }

          // if not invalid, then add to temp list as neighbours of this current vertex
          // where it is either 0 (means we are going to a lower height), or a positive
          // value
          int height = Math.max(0, weights[nextRow][nextCol] - weights[i][j]);
          temp.add(new IntegerPair(vertexMapping[nextRow][nextCol], height));
        }
        // add vertex[i][j]'s list of neighbours
        adjList.add(temp);
      }
    }

    // the visited array for each of the corresponding vertex
    // set to false by default
    boolean[] visited = new boolean[rows * cols];
    int min_ladder_height = 0; // 0 means no need ladder
    PriorityQueue<IntegerPair> pq = new PriorityQueue<>();
    // the first vertex
    int start = vertexMapping[0][0];
    // the very last vertex
    int end = vertexMapping[rows - 1][cols - 1];
    // add the first vertex to the PQ
    pq.add(new IntegerPair(start, 0));
    while (!pq.isEmpty()) {
      IntegerPair startVertex_pair = pq.poll();
      int startVertex_number = startVertex_pair.first();
      min_ladder_height = startVertex_pair.second();
      // call BFS on that current, valid vertex number
      BFS(startVertex_number, end, min_ladder_height, visited, adjList, pq);

      // if the last vertex has already been visited, that means we've already reached
      // the end
      if (visited[end]) {
        break;
      }
    }

    pw.println(min_ladder_height);
    pw.close();
  }

  // BFS needs: start, end, height, visitedArr, adjList, pq
  public static void BFS(int current, int end, int height, boolean[] visited, ArrayList<ArrayList<IntegerPair>> adjList,
      PriorityQueue<IntegerPair> pq) {
    // visiting current vertex, so set to true
    visited[current] = true;
    // BFS uses a queue
    Queue<Integer> queue = new LinkedList<>();
    // add the current node to the queue
    queue.add(current);
    while (!queue.isEmpty()) {
      Integer currentVertex = queue.poll();
      // then we are done
      if (currentVertex == end) {
        break;
      }

      ArrayList<IntegerPair> temp = adjList.get(currentVertex);
      for (int i = 0; i < temp.size(); i++) {
        IntegerPair curr = temp.get(i);
        int v = curr.first();
        int nextHeight = curr.second();
        // if not yet visited, and is of higher height, then add to visitation list,
        // which is the pq
        if (!visited[v] && nextHeight >= height) {
          pq.add(curr);
          continue;
        } else if (!visited[v]) {
          // then just set it to visited
          visited[v] = true;
          // and add it to the queue
          queue.add(v);
        }
      }
    }
  }
}