import java.util.ArrayList;

public class PiriorityScheduler extends Scheduler {

    public PiriorityScheduler(ArrayList<Process> processes) {
        super(processes);
    }

    @Override
    public void execute() {
        //starving
//for(int i=0; i<processes.size();i++) {
//           if (GUI.timer - process.get(i).arrivalTime>1000sec &&process.priority<process.size)
//        }
        for(int i=0; i<processes.size();i++) {
            Process next = getLeastPriority();
            GUI.receiveEvent(new Event(next, next.burstTime)); //because it is Preemptive
            processes.remove(next);
        }


    }

    public Process getLeastPriority() {
        int leastPriority_index = 0;
        for (int i = 0; i < processes.size(); i++) {
            if (processes.get(i).arrivalTime <= GUI.timer &&
                    processes.get(i).priority < processes.get(leastPriority_index).priority)
                leastPriority_index = i;
        }

        return processes.get(leastPriority_index);


    }

}





