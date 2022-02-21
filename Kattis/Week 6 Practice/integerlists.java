import java.util.*;
import java.io.*;

public class integerlists {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();
    // number of test cases
    int test_cases = Integer.parseInt(br.readLine());
    for (int i = 0; i < test_cases; i++) {
      String events = br.readLine(); // sequence of events
      int size = Integer.parseInt(br.readLine()); // size of input
      String list = br.readLine();
      int drop_amount = 0;
      for (int j = 0; j < events.length(); j++) {
        if (events.charAt(j) == 'D') {
          drop_amount++;
        }
      }

      if (drop_amount > size) {
        sb.append("error");
        sb.append('\n');
        continue;
      }

      list = list.substring(1, list.length() - 1);
      String[] list_items = list.split(",");
      Deque<Integer> deque = new LinkedList<Integer>();

      // populating linked list
      for (int j = 0; j < size; j++) {
        deque.add(Integer.parseInt(list_items[j]));
      }

      int reverses = 0;
      // counting the number of reverses before a drop
      for (int j = 0; j < events.length(); j++) {
        char current_event = events.charAt(j);
        if (current_event == 'R') {
          reverses++;
        } else if (current_event == 'D') {
          if (reverses % 2 == 1) {
            // then remove from back of deque
            deque.removeLast();
          } else {
            // remove front front of deque
            deque.removeFirst();
          }
        }
      }
      if (reverses % 2 == 1) {
        // append reversed
        LinkedList<Integer> rev = (LinkedList<Integer>) deque;
        Collections.reverse(rev);
        sb.append(rev.toString().replace(" ", ""));
      } else {
        sb.append(deque.toString().replace(" ", ""));
      }
      sb.append('\n');

    }
    pw.print(sb);
    pw.close();
  }
}