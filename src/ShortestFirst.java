import java.util.ArrayList;

public class ShortestFirst extends Scheduler
{
    public ShortestFirst(ArrayList<Process> processes, int conSw)
    {
        super(processes, conSw);
    }

    public void execute()
    {
        ArrayList<Process> tempPros = new ArrayList<>();
        tempPros.addAll(processes);

        /*
        int idx = getSmallest(tempPros);
        for(int i=0; i<tempPros.get(idx).burstTime; i++){
            GUI.receiveEvent(new Event(tempPros.get()));

        }
*/



    }

    private int getSmallest(ArrayList<Process> pros)
    {
        int smallestIdx=0;
        for(int i=0; i<pros.size(); i++)
        {
            if (pros.get(i).arrivalTime<= timer &&
                    pros.get(i).burstTime< pros.get(smallestIdx).burstTime)
                smallestIdx = i;
        }
        return smallestIdx;
    }
}
