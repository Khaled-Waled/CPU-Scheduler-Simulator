import java.util.ArrayList;

public class AGAT_Scheduling extends Scheduler {


    public AGAT_Scheduling(ArrayList<Process> processes, int conSw) {
        super(processes, conSw);
    }

    public void execute() {
        ArrayList<Process> tempProcesses = new ArrayList<>();
        tempProcesses.addAll(processes);
        int quantumBeginTime = 0;
        currentPId = 1;

        while (true) {

            updateAGATFactor(tempProcesses);

            int ind = getProcessByPId(tempProcesses, currentPId);

            //non-preemptive phase
            if (tempProcesses.get(ind).quantum * .40 > (timer - quantumBeginTime)) {
                tempProcesses.get(ind).burstTime--;


                GUI.receiveEvent(new Event(tempProcesses.get(ind), timer++));
            } else { // preemptive phase
                tempProcesses.get(ind).burstTime--;
                int smallest = getSmallestAGATFactor(tempProcesses);

                //current process doesn't have smallest AGAT factor or its time is done
                if (currentPId != tempProcesses.get(smallest).pid || timer - quantumBeginTime == tempProcesses.get(ind).quantum) {
                    GUI.receiveEvent(new Event(timer += contextSwitch, 1));

                    // process finished its quantum
                    if (timer - quantumBeginTime == tempProcesses.get(ind).quantum)
                        tempProcesses.get(ind).quantum += 2;
                        //process didnt finish its quantum
                    else
                        tempProcesses.get(ind).quantum += timer - quantumBeginTime;

                    currentPId = tempProcesses.get(smallest).pid;
                    quantumBeginTime = timer;
                }
            }

            if (tempProcesses.get(ind).burstTime == 0) {
                tempProcesses.remove(ind);
                if (tempProcesses.size() < 1) break;
                int smallest = getSmallestAGATFactor(tempProcesses);
                currentPId = tempProcesses.get(smallest).pid;
            }
            if (tempProcesses.size() < 1) break;
        }
    }

    void updateAGATFactor(ArrayList<Process> processes) {
        int v1, v2;
        int lastArrivalTime = 0, maxRemBurstTime = 0;
        for (Process process : processes) {
            lastArrivalTime = Math.max(lastArrivalTime, process.arrivalTime);
            maxRemBurstTime = Math.max(maxRemBurstTime, process.burstTime);
        }

        if (lastArrivalTime > 10) v1 = lastArrivalTime / 10;
        else v1 = 1;
        if (maxRemBurstTime > 10) v2 = maxRemBurstTime / 10;
        else v2 = 1;

        for (Process process : processes) {
            process.AGATFactor = (10 - process.priority) + (process.arrivalTime + v1 - 1) / v1 +
                    (process.burstTime + v2 - 1) / v2;
        }
    }

    int getSmallestAGATFactor(ArrayList<Process> processes) {
        int smallestIdx = 0;
        int AGATFactor = Integer.MAX_VALUE;
        for (int i = 0; i < processes.size(); i++) {
            if (processes.get(i).arrivalTime <= timer &&
                    processes.get(i).AGATFactor < AGATFactor) {
                smallestIdx = i;
                AGATFactor = processes.get(i).AGATFactor;
            }
        }
        return smallestIdx;
    }

}
