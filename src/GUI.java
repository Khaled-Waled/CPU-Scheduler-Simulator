import java.util.ArrayList;

public class GUI
{
    int numberOfProcesses;
    private static ArrayList<Event> events= new ArrayList<>();

    public GUI()
    {
        events = new ArrayList<>();
        firstScreen();
    }

    static void receiveEvent(Event event)
    {
        events.add(event);
    }
    public static void drawEventsScreen()
    {
        //TODO //GUI IMPLEMENTATION
    }
    public static void writeEvents()
    {
        for(Event event:events)
        {
            if(event.type==0)
            {
                System.out.println(event.start+": Process "+event.process.pid+ " is running");
            }
            else if (event.type==1)
            {
                System.out.println(event.start+": Context Switch");
            }
            else
            {
                System.out.println(event.start+": IDLE");
            }
        }
    }

    public static void firstScreen()
    {
        //textinput at (100, 100) //numOfProcesses
        //this.numOfProcesses = textinput.text

        inputScreen();
    }
    public static void inputScreen()
    {
        ArrayList<Process> processes = new ArrayList<>();
        int contextSwitch=0;
        int schedulerType=0;

        //textinput at (100, 100) //scheduler type
        //schedulerType = textinput.text

        //textinput at (100, 200) //Context switch time
        //schedulerType = textinput.text

        //for (int i=0; i<numberOfProcesses; i++)
        //  generate
        //  textinput at (100, 300+ (50*i)) //name
        //  textinput at (200, 300+ (50*i)) //time
        //  textinput at (300, 300+ (50*i)) //etc..
        //  processes.add(new process(.........));


        Scheduler scheduler;
        switch (schedulerType)
        {
            case 0:
            {
                scheduler= new PriorityScheduler(processes, contextSwitch);
            }
            case 1:
            {
                scheduler= new ShortestFirst(processes, contextSwitch);
            }
            case 2:
            {
                scheduler= new SRTF_Scheduling(processes, contextSwitch);
            }
            default:
            {
                scheduler= new AGAT_Scheduling(processes, contextSwitch);
            }
        }
        scheduler.execute();
    }

}
