// Indexer with a tail pointer
class Indexer {
  int data;
  Indexer next;
  Indexer tail;

  public Indexer(int data) {
    this.data = data;
    this.next = null;
    this.tail = null;
  }

  public void linkIndexer(Indexer nextIndexer) {
    if (this.next != null) {
      // recursively call tail.linkIndexer to get to last Indexer, then link to the
      // new Indexer
      this.tail.linkIndexer(nextIndexer);
      this.tail = nextIndexer;
    } else {
      // if this is the last Indexer, then just add and set tail pointer accordingly
      this.next = nextIndexer;
      this.tail = nextIndexer;
    }
  }
}