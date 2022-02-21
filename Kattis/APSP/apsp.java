import java.util.*;
import java.io.*;

public class apsp {
  private static Double INF = Double.POSITIVE_INFINITY;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();
    while (true) {
      String in = br.readLine();
      if (in.equals("0 0 0")) {
        break;
      }
      String[] sp = in.split(" ");
      int N = Integer.parseInt(sp[0]); // num of nodes
      int M = Integer.parseInt(sp[1]); // num of edges
      int Q = Integer.parseInt(sp[2]); // num of queries

      ArrayList<Edge> edgeList = new ArrayList<Edge>();
      // M lines follow
      for (int i = 0; i < M; i++) {
        String[] input = br.readLine().split(" ");
        int from = Integer.parseInt(input[0]);
        int to = Integer.parseInt(input[1]);
        int weight = Integer.parseInt(input[2]);
        edgeList.add(new Edge(from, to, weight));
      }

      double[][] apspInfo = new double[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (i == j) {
            apspInfo[i][j] = 0;
          } else {
            apspInfo[i][j] = INF;
          }
        }
      }
      // put edge weights into apspInfo
      for (int i = 0; i < M; i++) {
        Edge e = edgeList.get(i);
        int from = e.getFrom();
        int to = e.getTo();
        int weight = e.getWeight();
        apspInfo[from][to] = Math.min(apspInfo[from][to], weight);
      }

      // make APSP thingy
      for (int k = 0; k < N; k++) {
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++) {
            // edge relaxation
            if (apspInfo[i][k] < INF && apspInfo[k][j] < INF) {
              apspInfo[i][j] = Math.min(apspInfo[i][j], apspInfo[i][k] + apspInfo[k][j]);
            }
          }
        }
      }

      for (int k = 0; k < N; k++) {
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++) {
            if (apspInfo[i][k] != INF && apspInfo[k][j] != INF) {
              if (apspInfo[i][j] > apspInfo[i][k] + apspInfo[k][j]) {
                apspInfo[i][j] = -INF;
              }
            }
          }
        }
      }

      // Q queries follow
      for (int i = 0; i < Q; i++) {
        String[] input2 = br.readLine().split(" ");
        int from = Integer.parseInt(input2[0]);
        int to = Integer.parseInt(input2[1]);
        if (apspInfo[from][to] == -INF) {
          sb.append("-Infinity");
        } else if (apspInfo[from][to] == INF) {
          sb.append("Impossible");
        } else {
          sb.append((int) apspInfo[from][to]);
        }
        sb.append('\n');
      }
      sb.append('\n');
    }
    pw.print(sb);
    pw.close();
  }
}