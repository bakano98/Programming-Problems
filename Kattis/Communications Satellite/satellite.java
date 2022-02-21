import java.util.*;
import java.io.*;

public class satellite {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        List<Dish> dishes = new ArrayList<Dish>();
        // gets all the satellites
        for (int i = 0; i < N; i++) {
            String[] in = br.readLine().split(" ");
            int first = Integer.parseInt(in[0]);
            int second = Integer.parseInt(in[1]);
            int third = Integer.parseInt(in[2]);

            dishes.add(new Dish(first, second, third));
        }

        // simply get all distances
        double[][] distances = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    distances[i][j] = 0;
                } else {
                    distances[i][j] = dishes.get(i).distanceTo(dishes.get(j)); // O(1) because ArrayList
                }
            }
        }

        double beam_length = 0;
        Boolean[] visited = new Boolean[N];
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        // assume that there is an edge from each satellite to another
        for (int i = 0; i < N; i++) {
            if (i == 0)
                continue; // because this is the current edge
            // otherwise add a new edge
            pq.add(new Edge(0, i, distances[0][i]));
        }

        visited[0] = true;
        for (int i = 0; i < N; i++) {
            // while there are edges still left
            while (!pq.isEmpty()) {
                if (!Arrays.asList(visited).contains(null)) {
                    break;
                }
                Edge current = pq.poll();
                int from = current.from();
                int to = current.to();
                double distance = current.weight();
                if (visited[to] == null) { // to prevent NPE
                } else {
                    continue;
                }
                // otherwise, mark as visited
                visited[to] = true;
                beam_length = beam_length + distance;
                // then enqueue all other edges
                for (int a = 0; a < N; a++) {
                    if (to == a)
                        continue; // because this is the current edge -- we do not want to add the current edge
                    // back into the PQ
                    pq.add(new Edge(to, a, distances[to][a]));
                }
            }
        }
        pw.println(beam_length);
        pw.close();
    }
}