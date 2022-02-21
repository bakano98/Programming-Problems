import java.util.*;

public class People implements Comparable<People> {
  String name;
  ArrayList<Integer> ranking;
  String title;

  public People(String name, String title) {
    this.name = name;
    this.title = title;
    this.ranking = new ArrayList<>();
    String[] rank = title.split("-");
    // start from last word
    for (int i = rank.length - 1; i >= 0; i--) {
      if (rank[i].equals("upper")) {
        ranking.add(1);
      } else if (rank[i].equals("middle")) {
        ranking.add(2);
      } else {
        ranking.add(3);
      }
    }

    // to fill up the remaining slots, so we can compare
    for (int i = 0; i < 10 - rank.length; i++) {
      ranking.add(100);
    }
  }

  public int compareTo(People o) {
    // max 10 only
    for (int i = 0; i < 10; i++) {
      if (this.ranking.get(i) == o.ranking.get(i)) {
        continue;
      } else if (this.ranking.get(i) < o.ranking.get(i)) {
        return -1;
      } else {
        return 1;
      }
    }
    return this.name.compareTo(o.name);
  }

}
