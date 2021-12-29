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
    public int remainingTime;
    public int waitingTime;
    public int TurnAroundTime;

    public Process(String name, int pid, Color color, int arrivalTime, int burstTime, int priority, int quantum) {
        this.name = name;
        this.pid = pid;
        this.color = color;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.quantum = quantum;
    }

}
