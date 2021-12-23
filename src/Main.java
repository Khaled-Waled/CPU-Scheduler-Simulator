import java.awt.*;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Process("P1",0,new Color(0,0,155),0,5,0,3));
        processes.add(new Process("P2",1,new Color(89,55,0),0,4,0,3));
        processes.add(new Process("P3",2,new Color(0,80,0) ,0,10,0,3));
        Scheduler scheduler = new ShortestFirst(processes); //Change the scheduler type to get different implementations
        GUI gui = GUI.getInstance(scheduler.numberOfProcesses,scheduler.totalRunningTime);
        scheduler.execute();
    }
}
