import java.util.ArrayList;
import java.awt.Color; 
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
	private static ArrayList<Event> events= new ArrayList<>();
	ArrayList<Process> processes = new ArrayList<>();
	int type = 0 ; 
	static int cSwitch = 0 ; 
	static JFrame frame = new JFrame() ; 
	static void receiveEvent(Event event)
	{
	    events.add(event);
	}
	public static void drawEvents()
	{
		int timeSlot = 1200 / events.size() ; 
		
		 for(Event event:events)
		 {
			 JPanel panel = new JPanel() ; 
			 panel.setBounds((timeSlot *event.start) , 30 , timeSlot , 30 ) ; 
			 frame.add(panel) ;
			if (event.type == 0 )
			{
				 panel.setBackground(event.process.color) ; 
			}
			else if (event.type == 1 && cSwitch >0 )
			{
				 panel.setBackground(Color.white) ; 
			}
			else 
			{
				 panel.setBackground(Color.black) ; 
			}
		 }
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
	GUI()
	{
		this.processes = GuiNew.processes ; 
		this.type = GuiNew.type ; 
		this.cSwitch = GuiNew.cSwitch ; 
		frame.setVisible(true) ; 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280,720) ; 
		frame.setLayout(null);
		
		Scheduler scheduler ; 
		switch (type)
        {
            case 0:
            {
                scheduler= new PriorityScheduler(processes, cSwitch);
            }
            case 1:
            {
                scheduler= new ShortestFirst(processes, cSwitch);
            }
            case 2:
            {
                scheduler= new SRTF_Scheduling(processes, cSwitch);
            }
            default:
            {
                scheduler= new AGAT_Scheduling(processes, cSwitch);
            }
        }
        scheduler.execute();
        drawEvents();
        writeEvents();
	}
	
}
