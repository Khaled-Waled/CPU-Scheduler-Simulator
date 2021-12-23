public class Event
{
    Process process;
    int     start;
    int     duration;

    public Event(Process process, int duration)
    {
        this.process = process;
        this.duration = duration;
    }
}
