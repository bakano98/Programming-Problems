public class Edge implements Comparable<Edge>{
    public int from;
    public int to;
    public Double weight;

    public Edge(int from, int to, Double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int from() {
        return this.from;
    }
    public int to() { return this.to; }
    public Double weight() { return this.weight; }

    public int compareTo(Edge e) {
        return this.weight.compareTo(e.weight);
    }
}
