import java.awt.*;

public class Process {
    public String name;
    public int pid;
    public Color color;
    public int arrivalTime;
    public int burstTime;
    public int priority;
    public int quantum;
    public int AGATFactor;
    public int waitingTime;
    public int turnAroundTime;

    public Process(String name, int pid, Color color, int arrivalTime, int burstTime, int priority, int quantum) {
        this.name = name;
        this.pid = pid;
        this.color = color;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.quantum = quantum;
        this.waitingTime = 0;
        this.turnAroundTime =0;
    }

    //Copy constructor
    public Process(Process process){
        this.name = process.name;
        this.pid = process.pid;
        this.color = process.color;
        this.arrivalTime = process.arrivalTime;
        this.burstTime = process.burstTime;
        this.priority = process.priority;
        this.quantum = process.quantum;
        this.waitingTime = process.waitingTime;
        this.turnAroundTime = process.turnAroundTime;
    }


}
