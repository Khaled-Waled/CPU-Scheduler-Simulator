import java.util.ArrayList;

public abstract class Scheduler {
    public ArrayList<Process> processes;
    static public int currentPId;
    public int numberOfProcesses;
    public static int timer = 0;
    protected int contextSwitch;

    public Scheduler(ArrayList<Process> processes, int conSw) {
        this.timer = 0;
        this.contextSwitch = conSw;
        this.processes = processes;
        this.numberOfProcesses = processes.size();
    }

    protected int getProcessByPId(ArrayList<Process> processes, int id) {
        for (int i = 0; i < processes.size(); i++) {
            if (
                    processes.get(i).pid == id) {
                return i;
            }
        }
        return -1;
    }

    void updateParameters(){
        for(Process process:processes){
            process.waitingTime = process.turnAroundTime - process.burstTime;
        }
    }

    public abstract void execute();
}
