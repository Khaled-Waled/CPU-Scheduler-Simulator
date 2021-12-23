import java.util.ArrayList;

public abstract class Scheduler
{
    public ArrayList<Process> processes;
    public int numberOfProcesses;
    public int totalRunningTime;

    public Scheduler(ArrayList<Process> processes)
    {
        this.processes = processes;
        this.numberOfProcesses = processes.size();

        //calculate total time by adding the times of all processes
        totalRunningTime=0;
        for(Process process : processes)
            totalRunningTime+=process.burstTime;
    }
    public abstract void execute();
}
