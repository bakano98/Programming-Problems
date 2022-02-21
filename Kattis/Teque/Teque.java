import java.io.*;
import java.util.*;

public class Teque {
  public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    // using HashMaps because put() and get() operations are O(1)..
    HashMap<Integer, Integer> frontTeque = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> backTeque = new HashMap<Integer, Integer>();

    int ffKey = -1;
    int fbKey = 0;
    int bfKey = -1;
    int bbKey = 0;
    int N = 0;
    try {
      N = Integer.parseInt(br.readLine());
    } catch (IOException e) {
    }

    for (int i = 0; i < N; i++) {
      String tokens = "";
      try {
        tokens = br.readLine();
      } catch (IOException e) {
      }

      String[] command_tokens = tokens.split(" ");
      String command = command_tokens[0];
      int item = Integer.parseInt(command_tokens[1]);

      /* Ensure both trees are balanced */
      /*
       * if (frontTeque.size() > backTeque.size()) { ffKey++; backTeque.put(bbKey,
       * frontTeque.remove(ffKey)); bbKey++; } else { bbKey--; frontTeque.put(ffKey,
       * backTeque.remove(bbKey)); ffKey--; }
       */

      /* Dealing with all the pushing */
      if (command.equals("push_front")) {
        // then just push to front
        frontTeque.put(ffKey, item);
        ffKey--;

      } else if (command.equals("push_back")) {
        // then just push to back
        backTeque.put(bbKey, item);
        bbKey++;

      } else {
        // push to back of frontTeque
        frontTeque.put(ffKey, item);
        ffKey++;
      }

      if (frontTeque.size() < backTeque.size()) {
        bfKey++;
        ffKey++;
        frontTeque.put(ffKey, backTeque.remove(bfKey));
      } else if (frontTeque.size() - 1 > backTeque.size()) {
        bfKey--;
        fbKey--;
        backTeque.put(bfKey, frontTeque.remove(fbKey));
      }

      /* Dealing with get. Here, we assume that the teque is always balanced */
      if (command.equals("get")) {
        // here, item refers to the index
        if (item < frontTeque.size()) {
          // because we do negative "indexing" for ffKey, so add instead
          pw.println(frontTeque.get(item + ffKey + 1));
          pw.flush();
        } else {
          // need to offset by the number of items in frontTeque
          // and by the number of items in the front of backTeque
          pw.println(backTeque.get(item - frontTeque.size() + bfKey + 1));
          pw.flush();
        }
      }
    }
    pw.close();
  }
}