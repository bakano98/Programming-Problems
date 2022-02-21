import java.util.*;

public class Trie {
  // reference of characters
  public final char[] characters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
      'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
  public TrieNode root;

  public Trie() {
    this.root = null;
  };

  // search the Trie and find the correct place to put it
  public void add(String name, int gender) {
    root = add(root, name, gender, 0);
  }

  public TrieNode add(TrieNode current, String name, int gender, int length) {
    if (length < name.length()) {
      char character = name.charAt(length);
      int index = (int) character - 65;
      if (current == null) {
        current = new TrieNode(character, index);
      } else {
        current.addContent(character, index);
      }
      current.next = add(current.next, name, gender, length + 1);
    }
    if (length == name.length() - 1) {
      System.out.println("Added " + name);
      current.mark(gender);
    }
    return current;
  }

  // search the Trie and remove it
  public void remove(String name) {
    remove(root, name, 0, 0);
  }

  // guaranteed to have been put in
  public void remove(TrieNode current, String name, int length, int flag) {
    if (length == name.length() - 1) {
      // means we are at the isWord marker
      System.out.println("Removed " + name);
      current.removeWord(length);
    } else {
      remove(current.next, name, length + 1, flag);
      current.content[length]--;
    }
  }

  // returns whether the word is in the Trie
  public void search(String name) {
    search(root, name, 0);
  }

  // sanity check...
  public void search(TrieNode current, String name, int length) {
    if (length < name.length()) {
      char currentChar = name.charAt(length);
      int index = (int) currentChar - 65;
      if (current.count[index] > 0) {
        search(current.next, name, length + 1);
      } else {

      }
    }
    if (length == name.length() - 1) {
      System.out.println(name + " returns " + current.isWord);
    }
  }

  // query must return an int, since we are querying the number of valid names
  // gender identifier == 0
  public int query(char start, char end) {
    return queryMale(start, end) + queryFemale(start, end);
  }

  public int queryMale(char start, char end) {
    // the range
    TrieNode temp = root;
    int count = 0;
    while (temp != null) {
      // if any of the characters in this current node is not within the range, break
      if ()
      if (temp.gender == 1) {
        count++;
      }
      temp = temp.next;
    }
    return count;
  }

  // the case where gender identifier == 2
  public int queryFemale(char start, char end) {
    return -1;
  }
}