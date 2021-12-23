public class Event
{
    Process process;
    int     start;
    int     duration;

    public Event(Process process, int start, int duration)
    {
        this.process = process;
        this.start = start;
        this.duration = duration;
    }
}
