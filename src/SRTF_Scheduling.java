import java.util.ArrayList;

public class SRTF_Scheduling extends Scheduler
{

    public SRTF_Scheduling(ArrayList<Process> processes, int conSw)
    {
        super(processes, conSw);
    }

    public void execute()
    {
        ArrayList<Process> tempPros = new ArrayList<>();
        for(Process process:processes)
            tempPros.add(new Process(process));

        ArrayList<Process> queue = new ArrayList<>();

        while (tempPros.size()>0)
        {
            for(Process process: tempPros){
                if(process.arrivalTime == timer){
                    queue.add(process);
                }
            }

            if(queue.size() == 0){
                timer++;
                GUI.receiveEvent(new Event(timer, 2));
                continue;
            }

            int smallest = getSmallestRemainingTime(queue);
            if(currentPId != queue.get(smallest).pid && timer!=0) {
                GUI.receiveEvent(new Event(timer, 1));
                timer += contextSwitch;
            }
            currentPId = queue.get(smallest).pid;
            executeProcess(queue, smallest);
            if(queue.get(smallest).burstTime <= 0){
                processes.get(getProcessByPId(processes,queue.get(smallest).pid)).turnAroundTime =
                        timer - processes.get(getProcessByPId(processes,queue.get(smallest).pid)).arrivalTime;
                queue.remove(smallest);
                tempPros.removeIf(process -> process.pid == currentPId);
            }
        }
    }

    void executeProcess(ArrayList<Process> tempProcesses, int smallest) {
        if(tempProcesses.get(smallest).burstTime <= 0)return;
        tempProcesses.get(smallest).burstTime--;
        tempProcesses.get(smallest).priority=timer;
        GUI.receiveEvent(new Event(tempProcesses.get(smallest),timer++));
    }

    private int getSmallestRemainingTime(ArrayList<Process> processes)
    {
        int smallestIdx=0;
        int minBurstTime=Integer.MAX_VALUE;
        for(int i=0; i<processes.size(); i++)
        {
            if (processes.get(i).arrivalTime<= timer &&
                    processes.get(i).burstTime /*+ processes.get(i).priority*/< minBurstTime)
            {
                smallestIdx = i;
                minBurstTime = processes.get(i).burstTime /*+ processes.get(i).priority*/;
            }
        }
        return smallestIdx;
    }
}


