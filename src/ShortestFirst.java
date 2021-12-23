import java.util.ArrayList;

public class ShortestFirst extends Scheduler
{

    public ShortestFirst(ArrayList<Process> processes)
    {
        super(processes);
    }

    public void execute()
    {
        System.out.println("Getting the shortest one first");
        //GUI.receiveEvent(new Event(processes.get(0),5)); //use this to send events to the gui
        //TODO //Still have starvation
        for(int i=0; i<processes.size();i++)
        {
            Process smallest = getSmallest();
            GUI.receiveEvent(new Event(smallest,smallest.burstTime)); //because it is Preemptive
            processes.remove(smallest);
        }
    }

    private Process getSmallest()
    {
        int smallestIdx=0;
        for(int i=0; i<processes.size(); i++)
        {
            if (processes.get(i).arrivalTime<= GUI.timer &&
                    processes.get(i).burstTime< processes.get(smallestIdx).burstTime)
                smallestIdx = i;
        }
        return processes.get(smallestIdx);
    }
}
