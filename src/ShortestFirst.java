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
        for(Process process:processes)
            tempPros.add(new Process(process));
        ArrayList<Process> queue = new ArrayList<Process>();

        int smallest = getSmallest(queue);

        while (tempPros.size()>0)
        {
            int s = queue.size();

            for (Process process : tempPros)
            {
                if (timer == process.arrivalTime)
                    queue.add(process);
                if (s == 0)
                    smallest = getSmallest(queue);
            }

            if (queue.size() == 0) //IDLE time
            {
                timer++;
                smallest = getSmallest(queue);
                GUI.receiveEvent(new Event(timer, 2));
                continue;
            }

            if (queue.get(smallest).pid != currentPId)  //Context Switch
            {
                GUI.receiveEvent(new Event(timer, 1));
                timer += contextSwitch;
            }

            currentPId = queue.get(smallest).pid;
            executeProcess(queue, smallest);


            if (queue.get(smallest).burstTime == 0)
            {
                processes.get(getProcessByPId(processes,queue.get(smallest).pid)).turnAroundTime =
                        timer - processes.get(getProcessByPId(processes,queue.get(smallest).pid)).arrivalTime;
                tempPros.removeIf(process -> process.pid == currentPId);
                queue.remove(smallest);
                smallest = getSmallest(queue);
            }
        }
    }

    void executeProcess(ArrayList<Process> pros, int smallest) {
        if (pros.get(smallest).burstTime <= 0) return;
        pros.get(smallest).burstTime--;
        pros.get(smallest).priority=timer;
        GUI.receiveEvent(new Event(pros.get(smallest), timer++));
    }

    private int getSmallest(ArrayList<Process> pros)
    {
        int smallestIdx=0;
        int minTime = Integer.MAX_VALUE;
        for(int i=0; i<pros.size(); i++)
        {
            if (pros.get(i).arrivalTime<= timer &&
                    pros.get(i).burstTime< minTime)
            {
                smallestIdx = i;
                //Starvation Fix
                minTime = pros.get(i).burstTime + pros.get(i).priority;
            }
        }
        return smallestIdx;
    }
}
