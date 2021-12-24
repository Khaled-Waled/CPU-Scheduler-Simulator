import java.util.ArrayList;

public class GUI
{
    public static int numberOfProcess=1;
    public static int totalRunningTime=1;
    private static ArrayList<Event> events= new ArrayList<>();

    public GUI()
    {
        events = new ArrayList<>();
    }

    static void receiveEvent(Event event)
    {
        events.add(event);
    }
    public static void drawEvents()
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

}
