import java.util.ArrayList;
import java.sql.Time;
public class SRTF extends Scheduler{
public static int count = 0;
public int miniArrivalTime=0;
public static int p = 0;
public static int oldProcess;
public static int ContextSwitching;
public static int TotalProcesses = 0;


    public SRTF(ArrayList<Process> processes, int conSw) {
        super(processes, conSw);
    }

    @Override
    public void execute() {
        p = processes.size();
        int min = Integer.MAX_VALUE;

        System.out.println("Getting the SRTF");
        for (int i = 0; i < processes.size(); i++) {
            if (processes.get(i).arrivalTime <= timer)//if Process has arrived
            {
                if (processes.get(i).remainingTime < min && processes.get(i).remainingTime > 0) {
                    count++;
                    if (count == 1)
                        miniArrivalTime = processes.get(i).arrivalTime;

                    min = processes.get(i).remainingTime;
                    p = i;
                }
            }

        }
        if(p==processes.size()){
            timer++; }
        else{
            new Time(timer);
            processes.get(p).remainingTime--;
            for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).arrivalTime <= timer) {
                    if (processes.get(i).remainingTime != 0) {
                        processes.get(i).TurnAroundTime++;

                        if (p != oldProcess) // if it is not the same process
                        {
                            processes.get(i).TurnAroundTime += SRTF.ContextSwitching;
                            processes.get(i).waitingTime += SRTF.ContextSwitching;
                        }

                        if (i != p)
                            processes.get(i).waitingTime++;
                    } else if (i == p)
                        processes.get(i).TurnAroundTime++;
                }
            }
            timer++;
            new Time(timer);
            if ((timer - 1 - miniArrivalTime) != 0) {
                if ((p + 1) != )// If assigned to a different Process, then print the current value of time and the name of the new Process
                                // cannot solve!!!
                {
                    System.out.print("->" + (timer - 1) + "->P" + (p + 1));
                }
            } else// If the current time is 0, then print the name of the First selected Process
                System.out.print((timer - 1) + "->P" + (p + 1));

            int total_time = 0;
            for (int i = 0; i < p; i++) {
                total_time += processes.get(i).remainingTime;
            }

            if ((timer - 1 - miniArrivalTime) == total_time - 1)// All the process names have been printed now we have to print the time at which execution ends
            {
                System.out.print("->" + (timer));
            }

            for (int i = 0; i < p; i++) {
                if (processes.get(i).remainingTime <= 0)
                    TotalProcesses++;
            }

        }
        oldProcess = p;
    }
    public static void PrintAverage(ArrayList<Process> processes, int n) {
        float avgWt = 0, avgTa = 0;
        for (int i = 0; i < n; i++) {
            avgWt += processes.get(i).waitingTime;
            avgTa += processes.get(i).TurnAroundTime;
            System.out.println((i + 1) + processes.get(i).arrivalTime +processes.get(i).TurnAroundTime + processes.get(i).waitingTime);
        }
        System.out.println("\naverage TAt is " + (float) (avgTa / n));
        System.out.println("average WT is " + (float) (avgWt / n));
    }
}
