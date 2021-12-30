import java.util.ArrayList;

public class AGAT_Scheduling extends Scheduler {


    public AGAT_Scheduling(ArrayList<Process> processes, int conSw) {
        super(processes, conSw);
    }

    public void execute() {
        ArrayList<Process> allPros = new ArrayList<>();
        for (Process process : processes) {
            if (process.burstTime == 0) continue;
            allPros.add(new Process(process));
        }

        ArrayList<Process> queue = new ArrayList<>();


        updateAGATFactor(allPros);


        int sliceTimer = 0;
        while (allPros.size() > 0) {

            for (Process process : allPros) {
                if (process.arrivalTime == timer && !queue.contains(process)) {
                    queue.add(process);
                }
            }

            updateAGATFactor(queue);
            if (queue.size() == 0) {
                timer++;
                continue;
            }


            if (queue.get(0).burstTime == 0) {
                processes.get(getProcessByPId(processes,queue.get(0).pid)).turnAroundTime =
                        timer - processes.get(getProcessByPId(processes,queue.get(0).pid)).arrivalTime;
                sliceTimer = 0;
                executeContextSwitch(queue);
                allPros.remove(getProcessByPId(allPros, queue.get(0).pid));
                queue.remove(0);
                continue;
            }


            //Preemptive phase
            if (queue.get(0).quantum * 2 / 5 > sliceTimer) {
                executeProcess(queue);
                sliceTimer++;
                continue;
            }

            //Non Preemptive phase
            if (sliceTimer == queue.get(0).quantum) {
                queue.get(0).quantum += 2;
                System.out.println(timer + " : Process " + queue.get(0).pid + "'s quantum has changed to " + queue.get(0).quantum);
                sliceTimer = 0;
                executeContextSwitch(queue);
                queue.add(queue.get(0));
                queue.remove(0);
                continue;
            }
            if (queue.size() > 1) {
                int smallest = getSmallestAGATFactor(queue);

                if (smallest != queue.get(0).pid) {
                    queue.get(0).quantum += queue.get(0).quantum - sliceTimer;
                    System.out.println(timer + " : Process " + queue.get(0).pid + "'s quantum has changed to " + queue.get(0).quantum);
                    queue.add(queue.get(0));
                    queue.remove(0);

                    ArrayList<Process> newQueue = new ArrayList<>();
                    newQueue.add(allPros.get(getProcessByPId(allPros, smallest)));
                    for (int i = 0; i < queue.size(); i++) {
                        if (queue.get(i).pid == smallest) continue;
                        newQueue.add(queue.get(i));
                    }
                    queue = newQueue;

                    executeContextSwitch(queue);
                    sliceTimer = 0;
                    continue;
                }
            }

            executeProcess(queue);
            sliceTimer++;
        }

    }

    void executeProcess(ArrayList<Process> queue) {
        queue.get(0).burstTime--;
        GUI.receiveEvent(new Event(queue.get(0), timer++));
    }

    void executeContextSwitch(ArrayList<Process> queue) {
        for(int i=0; i<contextSwitch; i++) {
            GUI.receiveEvent(new Event(timer++, 1));
        }
    }

    void updateAGATFactor(ArrayList<Process> pros) {
        double v1, v2;
        int lastArrivalTime = 0, maxRemBurstTime = 0;
        for (Process process : pros) {
            lastArrivalTime = Math.max(lastArrivalTime, process.arrivalTime);
            maxRemBurstTime = Math.max(maxRemBurstTime, process.burstTime);
        }

        if (lastArrivalTime > 10) v1 = lastArrivalTime / 10.0;
        else v1 = 1.0;
        if (maxRemBurstTime > 10) v2 = maxRemBurstTime / 10.0;
        else v2 = 1.0;

        for (Process process : pros) {
            //if (process.arrivalTime > timer) continue;
            int oldFactor = process.AGATFactor;
            process.AGATFactor = (10 - process.priority) + (int) Math.ceil((double) process.arrivalTime / v1) +
                    (int) Math.ceil((double) process.burstTime / v2);
            if(oldFactor != process.AGATFactor)
                System.out.println(timer + " : Process " + process.pid + "'s AGAT factor has changed to " + process.AGATFactor);

        }
    }

    int getSmallestAGATFactor(ArrayList<Process> pros) {
        int smallestPID = 1;
        double AGATFactor = Integer.MAX_VALUE;
        for (int i = 0; i < pros.size(); i++) {
            if (pros.get(i).arrivalTime <= timer &&
                    pros.get(i).AGATFactor < AGATFactor) {
                smallestPID = pros.get(i).pid;
                AGATFactor = pros.get(i).AGATFactor;
            }
        }
        return smallestPID;
    }

    void displayAGATFactor(ArrayList<Process> pros) {
        for (int i = 0; i < pros.size(); i++) {
            System.out.print(pros.get(i).pid + " " + pros.get(i).AGATFactor + " ");

        }
        System.out.println();
    }

}
