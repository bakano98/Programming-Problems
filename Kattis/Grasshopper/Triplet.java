public class Triplet {
  // first will be the vertex number
  public Integer first;
  // second is the next vertex
  public Integer second;
  public Integer count;

  public Triplet(Integer first, Integer second, Integer count) {
    this.first = first;
    this.second = second;
    this.count = count;
  }

  public Integer first() {
    return this.first;
  }

  public Integer second() {
    return this.second;
  }

  public Integer getCount() {
    return this.count;
  }

}