import java.util.*;
import java.io.*;

public class FizzBuzz {
  public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String inputLine = "";
    try {
      inputLine = br.readLine();
    } catch (IOException e) {
    }

    String[] inputs = inputLine.split(" ");
    int X = Integer.parseInt(inputs[0]); // divide by every X = print Fizz
    int Y = Integer.parseInt(inputs[1]); // divide by every Y = print Buzz
    int N = Integer.parseInt(inputs[2]); // number of lines
    for (int i = 1; i <= N; i++) {
      if ((i % X == 0) && (i % Y == 0)) {
        System.out.println("FizzBuzz");
      } else if (i % Y == 0) {
        System.out.println("Buzz");
      } else if (i % X == 0) {
        System.out.println("Fizz");
      } else {
        System.out.println(i);
      }
    }
  }
}