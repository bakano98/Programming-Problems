public class Edge {
  int from;
  int to;
  int weight;

  public Edge(int from, int to, int weight) {
    this.from = from;
    this.to = to;
    this.weight = weight;
  }

  public int getFrom() {
    return this.from;
  }

  public int getTo() {
    return this.to;
  }

  public int getWeight() {
    return this.weight;
  }
}
