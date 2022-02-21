
// Barebones Union-Find Disjoint Sets implementation, 
// using both path compression and union by rank heuristics
// code is from the lecture
import java.util.*;

class UnionFind {
  public int[] p;
  public int[] rank;
  public int[] available_drawers;

  public UnionFind(int N) {
    // think of each drawer as a disjoint set
    p = new int[N];
    rank = new int[N];
    available_drawers = new int[N];
    for (int i = 0; i < N; i++) {
      p[i] = i;
      rank[i] = 0;
      // all drawers are capacity of 1
      available_drawers[i] = 1;
    }
  }

  // function to check if there is an availability, if there is, then reduce and
  // return true --> insert
  // otherwise return false --> discard
  public boolean isAvailable(int setNumber) {
    if (available_drawers[setNumber] != 0) {
      available_drawers[setNumber]--;
      return true;
    }
    return false;
  }

  public int findSet(int i) {
    if (p[i] == i)
      return i;
    else {
      p[i] = findSet(p[i]);
      return p[i];
    }
  }

  public Boolean isSameSet(int i, int j) {
    return findSet(i) == findSet(j);
  }

  // joining two drawers together, so we need to increase the correct drawer's
  // availability
  public void unionSet(int i, int j) {
    if (!isSameSet(i, j)) {
      int x = findSet(i), y = findSet(j);
      // rank is used to keep the tree short
      if (rank[x] > rank[y]) {
        p[y] = x;
        // in this case, since we are making x the parent of y..
        available_drawers[x] += available_drawers[y];
      } else {
        p[x] = y;
        // other way round, making y the parent of x
        if (rank[x] == rank[y]) {
          rank[y] = rank[y] + 1;
          available_drawers[y] += available_drawers[x];
        }
      }
    }
  }
}