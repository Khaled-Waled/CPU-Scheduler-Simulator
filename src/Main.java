import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int c = 0;
        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Process("P1", 1, new Color(0, 0, 155), 0, 10, 3, 4));
        processes.add(new Process("P2", 2, new Color(89, 55, 0), 0, 1, 1, 3));
        processes.add(new Process("P3", 3, new Color(0, 80, 0), 0, 2, 4, 5));
        processes.add(new Process("P4", 4, new Color(90, 1, 88), 0, 1, 5, 2));
        processes.add(new Process("P5", 5, new Color(90, 1, 88), 0, 5, 2, 2));
        Scheduler scheduler = new PriorityScheduler(processes, c); //Change the scheduler type to get different implementations
        scheduler.execute();
        GUI.writeEvents();
    }
}
