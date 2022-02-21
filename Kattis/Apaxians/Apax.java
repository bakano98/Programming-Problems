import java.util.*;
import java.io.*;

public class Apax {
  public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String inputLine = "";
    String finalOutput = "";
    try {
      inputLine = br.readLine();
    } catch (IOException e) {
    }
    int i = 0;
    StringBuilder output = new StringBuilder();
    String[] inputArr = inputLine.split("(?!^)");
    /* Stop at second last item */
    for (i = 0; i < inputArr.length - 1; i++) {
      String current = inputArr[i];
      if (current.equals(inputArr[i + 1])) {
        // skip
      } else {
        output = output.append(inputArr[i]);
      }
    }
    output = output.append(inputArr[i]);
    System.out.println(output);
  }
}