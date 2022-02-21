import java.util.*;
import java.io.*;

public class ladice {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    String[] in = br.readLine().split(" ");
    int N = Integer.parseInt(in[0]);
    int L = Integer.parseInt(in[1]);

    // number of drawers = number of disjoint sets
    UnionFind ds = new UnionFind(L);
    // StringBuilder to concat the output
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) {
      String[] in_line = br.readLine().split(" ");
      // 0-based indexing
      int A = Integer.parseInt(in_line[0]) - 1;
      int B = Integer.parseInt(in_line[1]) - 1;

      // join the two drawers together first
      // if it is the same drawer, nothing will be done
      ds.unionSet(A, B);

      // find the set and check if there are availabilities
      // if so, then insert into any one drawer and reduce the availability
      if (ds.isAvailable(ds.findSet(A))) {
        sb.append("LADICA\n");
      } else {
        // throw
        sb.append("SMECE\n");
      }
    }

    pw.print(sb);
    pw.close();
  }
}