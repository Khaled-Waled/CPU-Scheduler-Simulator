import java.awt.*;

public class Process
{
    public String   name;
    public int      pid;
    public Color    color;
    public int      arrivalTime;
    public int      burstTime;
    public int      priority;
    public int      quantum;

    public Process(String name, int pid,Color color, int arrivalTime, int burstTime, int priority,int quantum)
    {
        this.name = name;
        this.pid = pid;
        this.color = color;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.quantum = quantum;
    }

    /*
    public Process(int v)       //Used only as a declaration of a context switch or Idle time
    {
        if(v<1)
        {
            this.name = "context switch";
            this.pid = -1;
            this.color = new Color(50,50,50);
        }
        else
        {
            this.name = "IDLE";
            this.pid = -2;
            this.burstTime=v;
            this.color = new Color(100,100,100);
        }
    }
    */
}
