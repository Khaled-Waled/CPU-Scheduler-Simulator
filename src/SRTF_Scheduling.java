import java.util.ArrayList;

public class SRTF_Scheduling extends Scheduler
{

    public SRTF_Scheduling(ArrayList<Process> processes, int conSw)
    {
        super(processes, conSw);
    }

    public void execute()
    {
        ArrayList<Process> tempProcesses = new ArrayList<>();
        tempProcesses.addAll(processes);
        while (true)
        {
            int smallest = getSmallestRemainingTime(tempProcesses);
            if(currentPId != tempProcesses.get(smallest).pid && timer!=0)
                GUI.receiveEvent(new Event(timer+=contextSwitch, 1));


            currentPId = tempProcesses.get(smallest).pid;
            tempProcesses.get(smallest).burstTime--;
            tempProcesses.get(smallest).priority=timer;
            GUI.receiveEvent(new Event(tempProcesses.get(smallest),timer++));
            if(tempProcesses.get(smallest).burstTime==0) tempProcesses.remove(smallest);

            if(tempProcesses.size()<1) break;
        }
    }
    private int getSmallestRemainingTime(ArrayList<Process> processes)
    {
        int smallestIdx=0;
        int minBurstTime=Integer.MAX_VALUE;
        for(int i=0; i<processes.size(); i++)
        {
            if (processes.get(i).arrivalTime<= timer &&
                    processes.get(i).burstTime + processes.get(i).priority< minBurstTime)
            {
                smallestIdx = i;
                minBurstTime = processes.get(i).burstTime;
            }
        }
        return smallestIdx;
    }
}


