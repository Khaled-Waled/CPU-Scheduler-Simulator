public class Event
{
    Process process = null;
    int     start;
    int     type;
    // type = 0     Process
    // type = 1     Context switch
    // type = 2     Idle Time
    public Event(Process process, int start)
    {
        this.type = 0;
        this.process = process;
        this.start = start;
    }
    public Event (int start, int type) //Used only as a declaration of a context switch or Idle time
    {
        this.process = null;
        this.start = start;
        this.type = type;
    }
}
