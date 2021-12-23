import java.util.ArrayList;

public abstract class Scheduler
{
    ArrayList<Process> processes;

    public Scheduler(ArrayList<Process> processes)
    {
        this.processes = processes;
    }
    public abstract void execute();
}
