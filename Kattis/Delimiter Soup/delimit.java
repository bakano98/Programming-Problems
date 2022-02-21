
/**
 * Week 6 Practice Question from Kattis
 * Problem A: Delimiter Soup
 */

import java.util.*;
import java.io.*;

// Idea: Use a stack to store every opening. For every closing, it should match its previous one, otherwise it is an error

public class delimit {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    Stack<Character> stack = new Stack<Character>();
    int L = Integer.parseInt(br.readLine());
    // the delimiters to be read
    String S = br.readLine();
    // then reach by char
    for (int i = 0; i < L; i++) {
      // get the current char to add
      char currentAdd = S.charAt(i);
      char currentRead = stack.empty() ? 'a' : stack.peek();
      if (currentAdd == '(' || currentAdd == '{' || currentAdd == '[') {
        stack.push(currentAdd);
      } else if (currentAdd == ')') {
        if (currentRead == '(') {
          stack.pop();
        } else {
          pw.println(currentAdd + " " + i);
          pw.flush();
          return;
        }
      } else if (currentAdd == ']') {
        if (currentRead == '[') {
          stack.pop();
        } else {
          pw.println(currentAdd + " " + i);
          pw.flush();
          return;
        }
      } else if (currentAdd == '}') {
        if (currentRead == '{') {
          stack.pop();
        } else {
          pw.println(currentAdd + " " + i);
          pw.flush();
          return;
        }
      }
    }
    pw.println("ok so far");
    pw.close();
  }
}