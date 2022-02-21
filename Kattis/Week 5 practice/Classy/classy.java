import java.util.*;
import java.io.*;

public class classy {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();
    int num_test = Integer.parseInt(br.readLine());
    for (int i = 0; i < num_test; i++) {
      // number of people
      int num_people = Integer.parseInt(br.readLine());
      List<People> people = new ArrayList<>();

      for (int j = 0; j < num_people; j++) {
        String[] currentPerson = br.readLine().split(" ");
        String name = currentPerson[0].replace(":", "");
        String title = currentPerson[1];
        people.add(new People(name, title));
      }

      // sort it according to what we defined in compareTo
      Collections.sort(people);
      people.forEach(x -> {
        pw.println(x.name);
        pw.flush();
      });
      pw.println("==============================");
      pw.flush();
    }

    pw.close();
  }
}