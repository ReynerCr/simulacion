
package src;

import java.util.Timer;
import java.util.TimerTask;

public class TimerExample {
    public static void main(String[] args) {
        Timer timer = new Timer(); // Creating a Timer object from the timer class

        TimerTask task1 = new TimerTask() {
            public void run() {
                System.out.println("Task 1 executed!");
            }
        };

        TimerTask task2 = new TimerTask() {
            public void run() {
                System.out.println("Task 2 executed!");
            }
        };

        TimerTask task3 = new TimerTask() {
            public void run() {
                System.out.println("Task 3 executed!");
            }
        };

        // Scheduling tasks to run after specified delays
        int time=1000;
        timer.schedule(task1,  time); // Using the schedule method of the timer class
        time=time+1000;
        timer.schedule(task2,  time); // Using the schedule method of the timer class
        time=time+1000;
        timer.schedule(task3,time); // Using the schedule method of the timer class
    
    }
}
