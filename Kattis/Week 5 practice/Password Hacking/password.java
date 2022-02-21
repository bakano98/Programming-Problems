import java.util.*;
import java.io.*;

public class password {
  public static void main(String[] args) throws IOException {
    // IO stuff
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    // number of passwords in the list
    int N = Integer.parseInt(br.readLine());
    int attempts = 1;
    double output = 0;
    List<Double> ls = new ArrayList<Double>();
    for (int i = 0; i < N; i++) {
      String[] input = br.readLine().split(" ");
      double current_probability = Double.parseDouble(input[1]);
      ls.add(current_probability);
    }

    for (int i = 0; i < ls.size(); i++) {
      output = output + ls.get(i) * attempts;
      attempts++;
    }

    pw.println(output);
    pw.close();
  }
}