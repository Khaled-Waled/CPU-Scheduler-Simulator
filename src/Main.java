import java.awt.*;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        int c =0;
        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Process("P4",4,new Color(0,0,155),5,4,0,3));
        processes.add(new Process("P2",2,new Color(89,55,0),2,4,0,3));
        processes.add(new Process("P3",3,new Color(0,80,0) ,4,1,0,3));
        processes.add(new Process("P1",1,new Color(90,1,88) ,0,7,0,3));
        Scheduler scheduler = new SRTF_Scheduling(processes,c); //Change the scheduler type to get different implementations
        scheduler.execute();
        GUI.writeEvents();
    }
}
