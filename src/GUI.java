import java.util.ArrayList;

public class GUI
{
    public int numberOfProcess=1;
    public int totalRunningTime=1;
    public static int timer=0;
    public static GUI instance = null;
    public static GUI getInstance(int n, int totalTime)
    {
        if (instance == null)
            instance = new GUI(n,totalTime);

        return instance;
    }
    private GUI(int n,int totalTime)
    {
        this.numberOfProcess = n;
        this.totalRunningTime= totalTime;
        this.timer=0;
        //TODO //Show the main window
    }
    static void receiveEvent(Event event)
    {
        timer+=event.duration;
        //TODO //draw new elements on the table
    }
}
