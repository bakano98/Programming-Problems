
// Barebones Union-Find Disjoint Sets implementation, 
// using both path compression and union by rank heuristics
// code is from the lecture
import java.util.*;

class UnionFind {
  public int[] p;
  public int[] rank;
  public int number_of_sets;

  public UnionFind(int N) {
    p = new int[N];
    rank = new int[N];
    number_of_sets = N;
    for (int i = 0; i < N; i++) {
      p[i] = i;
      rank[i] = 0;
    }
  }

  public int findSet(int i) {
    if (p[i] == i)
      return i;
    else {
      p[i] = findSet(p[i]);
      return p[i];
    }
  }

  public int connected() {
    return this.number_of_sets;
  }

  public Boolean isSameSet(int i, int j) {
    return findSet(i) == findSet(j);
  }

  public void unionSet(int i, int j) {
    if (!isSameSet(i, j)) {
      int x = findSet(i), y = findSet(j);
      // one less set each time we union
      number_of_sets--;
      // rank is used to keep the tree short
      if (rank[x] > rank[y]) {
        p[y] = x;
      } else {
        p[x] = y;
        if (rank[x] == rank[y]) {
          rank[y] = rank[y] + 1;
        }
      }
    }
  }
}