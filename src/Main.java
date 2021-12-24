import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int c = 0;
        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Process("P1", 1, new Color(0, 0, 155), 5, 53, 0, 20));
        processes.add(new Process("P2", 2, new Color(89, 55, 0), 2, 17, 0, 20));
        processes.add(new Process("P3", 3, new Color(0, 80, 0), 4, 68, 0, 20));
        processes.add(new Process("P4", 4, new Color(90, 1, 88), 0, 24, 0, 20));
        Scheduler scheduler = new AGAT_Scheduling(processes, c); //Change the scheduler type to get different implementations
        scheduler.execute();
        GUI.writeEvents();
    }
}
