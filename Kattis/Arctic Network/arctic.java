import java.util.*;
import java.io.*;

public class arctic {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        Integer test_cases = Integer.parseInt(br.readLine());
        for (int tests = 0; tests < test_cases; tests++) {
            String[] in = br.readLine().split(" ");
            int S = Integer.parseInt(in[0]);
            int N = Integer.parseInt(in[1]);

            ArrayList<IntegerPair> coords = new ArrayList<IntegerPair>();

            for (int i = 0; i < N; i++) {
                String[] in_line = br.readLine().split(" ");
                int x = Integer.parseInt(in_line[0]);
                int y = Integer.parseInt(in_line[1]);
                // these are the coordinates
                coords.add(new IntegerPair(x, y));
            }

            // get distance from one vertex to all other vertices
            // distance from u to v = distance from point x to point y <-- use Math.hypot
            Double[][] dist = new Double[N][N];
            for (int i = 0; i < N; i++) {
                IntegerPair from = coords.get(i);
                for (int j = 0; j < N; j++) {
                    if (i == j) {
                        dist[i][j] = 0.0;
                        continue;
                    }
                    IntegerPair to = coords.get(j);
                    dist[i][j] = Math.hypot(to.getX() - from.getX(), to.getY() - from.getY());
                }
            }

        }
    }
}