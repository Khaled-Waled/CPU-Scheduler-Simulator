import java.util.ArrayList;

public class ShortestFirst extends Scheduler
{

    public ShortestFirst(ArrayList<Process> processes)
    {
        super(processes);
    }

    public void execute()
    {
        //TODO
        System.out.println("Getting the shortest one first");
        GUI.getEvent(new Event(processes.get(0),0,5));
    }
}
