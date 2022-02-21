import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Name: Law Wei Jie
 * Student Num: A0218249Y
 * CS2040S AY21/22 S1
 * Kattis Take-Home Assignment: Best Relay Team
 */

public class Main {
  public static void main(String[] args) {
    // create the scanner
    Scanner sc = new Scanner(System.in);
    
    // initialising the list of runners
    List<String> runner_names = new ArrayList<>();
    List<String> final_runners = new ArrayList<>();

    // initialising the lists to store timings of the rounds
    List<Double> lap_one = new ArrayList<>();
    List<Double> lap_two = new ArrayList<>();
    
    // getting the number of runners
    int numRunners = sc.nextInt();
    
    // consume the "\n" from enter
    sc.nextLine();

    /* 
      This loop adds all the names and timings to the 3 different arrays.
      Total time complexity of the two loops is O(n*j), where n is the number of runners
      Since j is a constant (2), thus, time complexity is O(n).
    */
    for (int i = 0; i < numRunners; i++) {
      String currentRunner = sc.next();
      runner_names.add(currentRunner);
      // loop to add the values into the list
      for (int j = 0; j < 2; j++) {
        double currentTime = sc.nextDouble();
        if (j == 0) {
          // first lap
          lap_one.add(currentTime);
        } else {
          // second lap onwards
          lap_two.add(currentTime);
        }
      }
      
      // consume "\n" from enter
      sc.nextLine();
    }

    // initialise to largest double value
    double totalTime = Double.MAX_VALUE;

    /* This loop serves to bruteforce the entire thing and see which has the fastest time */
    for (int i = 0; i < numRunners; i++) {
      double currentTime = 0;
      List<String> temp_running = new ArrayList<>();
      List<Double> temp_one = new ArrayList<>();
      List<Double> temp_two = new ArrayList<>();
      List<String> temp = new ArrayList<>();

      temp_running.addAll(runner_names);
      temp_one.addAll(lap_one);
      temp_two.addAll(lap_two);

      // we must exclude the current chosen first runner
      temp.add(temp_running.remove(i));
      temp_two.remove(i);
      currentTime = temp_one.remove(i);

      // then we try the next best 3 runners for lap 2 
      for (int j = 0; j < 3; j++) {
        int index = temp_two.indexOf(Collections.min(temp_two));
        temp.add(temp_running.remove(index));
        currentTime = currentTime + temp_two.remove(index);
      }
      
      // compare the current best time, with the current time in this run
      if (currentTime < totalTime) {
        totalTime = currentTime;
        final_runners = temp;
      } 
    }
   
    // printing the total time
    System.out.println(totalTime);

    // printing each value (string) in the list
    final_runners.forEach(x -> System.out.println(x));
  }
}
