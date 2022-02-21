
/**
 * Name: Law Wei Jie
 * Student Number: A0218249Y
 * Take Home Assignment 3C: Baby Names (Optional)
 */
import java.util.*;
import java.io.*;

public class babynames {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    Trie trie = new Trie();
    // for the output
    StringBuilder sb = new StringBuilder();
    // while (true) {
    // // break iff input is 0
    // String[] in = br.readLine().split(" ");
    // int cmd = Integer.parseInt(in[0]);
    // if (cmd == 0) {
    // break;
    // } else if (cmd == 1 || cmd == 2) {
    // // 1 = add
    // // 2 = remove
    // String name = in[1];
    // if (cmd == 1) {
    // int gender = Integer.parseInt(in[2]);
    // trie.add(name, gender);
    // } else {
    // trie.remove(name);
    // }
    // } else {
    // // query
    // char start = in[1].charAt(0);
    // char end = in[2].charAt(0);
    // int queryTerm = Integer.parseInt(in[3]);
    // int validNumber;
    // if (queryTerm == 0) {
    // // both
    // validNumber = trie.query(start, end);
    // } else if (queryTerm == 1) {
    // // males only
    // validNumber = trie.queryMale(start, end);
    // } else {
    // // females only
    // validNumber = trie.queryFemale(start, end);
    // }
    // sb.append(validNumber);
    // sb.append("\n");
    // }
    // }
    // pw.print(sb);
    // pw.close();
    // add, search and remove works!

    trie.add("ISAC", 1);
    trie.search("ISAC");
    trie.search("IS");

    trie.add("IS", 1);
    trie.search("IS");

    trie.remove("ISAC");
    trie.search("ISAC");
    trie.search("IS");
  }
}