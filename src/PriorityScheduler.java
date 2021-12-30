import java.util.ArrayList;

public class PriorityScheduler extends Scheduler {

    public PriorityScheduler(ArrayList<Process> processes, int contextSwitchTime) {
        super(processes, contextSwitchTime);
    }

    public void execute() {

        ArrayList<Process> tempPros = new ArrayList<Process>();
        for(Process process:processes)
            tempPros.add(new Process(process));
        ArrayList<Process> queue = new ArrayList<Process>();

        int smallest = 0;
        while (tempPros.size() > 0) {

            int s = queue.size();

            for (Process process : tempPros) {
                if (timer == process.arrivalTime) {
                    queue.add(process);
                }
                if (s == 0) {
                    smallest = getLeastPriority(queue);
                }
            }

            if (queue.size() == 0) {
                timer++;
                smallest = getLeastPriority(queue);
                GUI.receiveEvent(new Event(timer, 2));
                continue;
            }

            if (queue.get(smallest).pid != currentPId) {
                GUI.receiveEvent(new Event(timer, 1));
                timer += contextSwitch;
            }
            currentPId = queue.get(smallest).pid;

            executeProcess(queue, smallest);

            if (queue.get(smallest).burstTime == 0) {
                processes.get(getProcessByPId(processes,queue.get(smallest).pid)).turnAroundTime =
                        timer - processes.get(getProcessByPId(processes,queue.get(smallest).pid)).arrivalTime;
                tempPros.removeIf(process -> process.pid == currentPId);
                queue.remove(smallest);

                if (queue.size() == 0) continue;

                smallest = getLeastPriority(queue);
                int ind = getHighestPriority(queue);
                queue.get(ind).priority--;
            }

        }
    }

    void executeProcess(ArrayList<Process> pros, int smallest) {
        if (pros.get(smallest).burstTime <= 0) return;
        pros.get(smallest).burstTime--;
        GUI.receiveEvent(new Event(pros.get(smallest), timer++));
    }

    public int getLeastPriority(ArrayList<Process> pros) {
        int leastPriority_index = 0;
        for (int i = 0; i < pros.size(); i++) {
            if (pros.get(i).arrivalTime <= timer &&
                    pros.get(i).priority < pros.get(leastPriority_index).priority)
                leastPriority_index = i;
        }

        return leastPriority_index;
    }

    public int getHighestPriority(ArrayList<Process> pros) {
        int ind = 0;
        for (int i = 0; i < pros.size(); i++) {
            if (pros.get(i).arrivalTime <= timer &&
                    pros.get(i).priority > pros.get(ind).priority)
                ind = i;
        }

        return ind;
    }

}

//starving
//for(int i=0; i<processes.size();i++) {
//           if (GUI.timer - process.get(i).arrivalTime>1000sec &&process.priority<process.size)
//        }



