import java.util.*;
import java.io.*;

// Idea: UFDS. Just join all the sets that they give, and output those non-connected ones
// If there is only 1 set, then all are connected.
public class where {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    String[] in = br.readLine().split(" ");
    int N = Integer.parseInt(in[0]); // number of houses
    int M = Integer.parseInt(in[1]); // number of cables
    UnionFind UFDS = new UnionFind(N);

    // only M lines
    for (int i = 0; i < M; i++) {
      String[] in_line = br.readLine().split(" ");
      // converting to 0-based indexing
      int from = Integer.parseInt(in_line[0]) - 1;
      int to = Integer.parseInt(in_line[1]) - 1;

      if (!UFDS.isSameSet(from, to)) {
        UFDS.unionSet(from, to);
      }
    }
    StringBuilder sb = new StringBuilder();
    if (UFDS.connected() == 1) {
      sb.append("Connected");
    } else {
      for (int i = 0; i < N; i++) {
        if (!UFDS.isSameSet(0, i)) {
          // convert to 1-based indexing
          sb.append(i + 1);
          sb.append('\n');
        }
      }
    }
    pw.print(sb);
    pw.close();
  }
}