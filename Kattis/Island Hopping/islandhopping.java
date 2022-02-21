import java.util.*;
import java.io.*;

public class islandhopping {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        Double test_cases = Double.parseDouble(br.readLine());

        for (int i = 0; i < test_cases; i++) {
            // getting number of islands in this test case
            int num_islands = Integer.parseInt(br.readLine());
            HashMap<Integer, DoublePair> hm = new HashMap<Integer, DoublePair>();
            for (int j = 0; j < num_islands; j++) {
                // Double pair containing the coordinates of island j
                String[] in = (br.readLine().split(" "));
                hm.put(j, new DoublePair(Double.parseDouble(in[0]), Double.parseDouble(in[1])));
            }
            // initialise D[i][j]
            double[][] distances = new double[num_islands][num_islands];
            for (int a = 0; a < num_islands; a++) {
                for (int b = 0; b < num_islands; b++) {
                    if (a == b) {
                        distances[a][b] = 0.0;
                    } else {
                        // use pythagoras, a^2 + b^2 = c^2
                        DoublePair first = hm.get(a);
                        DoublePair second = hm.get(b);
                        distances[a][b] = Math.hypot(first.getX() - second.getX(), first.getY() - second.getY());  // note this value is c^2 value
                    }                
                }
            }

            // to get the final distance
            double dist = 0;
            Integer[] visited = new Integer[num_islands];
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            // enqueue all edges to pq
            for (int a = 0; a < num_islands; a++) {
                if (distances[0][a] == 0 ) {
                    continue;
                }
                pq.add(new Edge(0, a, distances[0][a]));
            }
            visited[0] = 1;
            // add all edges to the pq
            while (!pq.isEmpty()) {
                if (!Arrays.asList(visited).contains(null)) {
                    break;
                    // cuz we are done
                }
                Edge e = pq.poll();
                int from = e.from();
                int to = e.to();
                Double weight = e.weight();
                if (visited[to] == null) {
                } else {
                    continue;
                }
                
                // mark as true
                visited[to] = 1;
                dist = dist + weight;
    
                // then enqueue all edges from to
                for (int a = 0; a < num_islands; a++) {
                    if (distances[to][a] == 0) {
                        continue;
                    }
                    pq.add(new Edge(to, a, distances[to][a]));
                }
            }
            pw.println(dist);
            pw.flush();
        }

        pw.close();
    }
}