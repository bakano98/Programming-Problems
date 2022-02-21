public class TrieNode {
  // all the available letters
  // public final char[] characters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
  // 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
  // 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
  // because each TrieNode can only have a maximum of 26 letters
  public char[] content;
  public TrieNode next;
  public int[] count;
  public int[] indexer;
  public int indexerCounter = 0;
  public boolean isWord;
  public int gender;

  public TrieNode() {
    this.content = new char[26];
    this.count = new int[26];
    this.indexer = new int[26];
  }

  public TrieNode(char content, int index) {
    // capped at size 26 per node
    this.content = new char[26];
    this.count = new int[26];
    this.indexer = new int[26];
    this.content[index] = content;
    this.count[index] = 1;
    this.indexer[indexerCounter] = index;
    indexerCounter++;
    this.next = null;
    // only mark this as true iff it's the last character in an input word
    this.isWord = false;
    this.gender = -1;
  }

  public void addContent(char content, int index) {
    this.content[index] = content;
    this.count[index]++;
  }
  
  public void removeWord(int index) {
    this.isWord = false;
    this.gender = -1;
    this.count[index]--;
  }

  public void mark(int gender) {
    this.gender = gender;
    this.isWord = true;
  }
}
