public class IntegerPair implements Comparable<IntegerPair> {
  // first will be the vertex number
  public Integer first;
  // second will be it's corresponding weight
  public Integer second;

  public IntegerPair(Integer first, Integer second) {
    this.first = first;
    this.second = second;
  }

  public Integer first() {
    return this.first;
  }

  public Integer second() {
    return this.second;
  }

  @Override
  public int compareTo(IntegerPair b) {
    return this.second - b.second();
  }
}
