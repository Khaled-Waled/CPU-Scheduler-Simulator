import java.util.ArrayList;

public abstract class Scheduler
{
    public ArrayList<Process> processes;
    public int currentPId;
    public int numberOfProcesses;
    public int totalRunningTime;
    public static int timer=0;
    protected int contextSwitch;

    public Scheduler(ArrayList<Process> processes, int conSw)
    {
        this.timer=0;
        this.contextSwitch=conSw;
        this.processes = processes;
        this.numberOfProcesses = processes.size();

        //calculate total time by adding the times of all processes
        totalRunningTime=0;
        for(Process process : processes)
            totalRunningTime+=process.burstTime;
    }
    protected int getProcessByPId(ArrayList<Process> processes, int id)
    {
        for(int i=0; i<processes.size(); i++)
        {
            if (processes.get(i).arrivalTime<= timer &&
                    processes.get(i).pid==id)
            {
                return i;

            }
        }
        return -1;
    }
    public abstract void execute();
}
