import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class GUI {
    private static ArrayList<Event> events = new ArrayList<>();
    ArrayList<Process> processes = new ArrayList<>();
    int type = 0;
    static int cSwitch = 0;
    static JFrame frame = new JFrame();



    static void receiveEvent(Event event) {
        events.add(event);
    }

    public static void drawEvents() {
        JLabel label = new JLabel();
        label.setText("Timeline");
        label.setBounds(10,10,100,60);
        label.setFont(new Font("Verdana", Font.PLAIN, 18));
        frame.add(label);
        int timeSlot = (1200) / events.size();
        int offset = 30;

        for (Event event : events) {
            JPanel panel = new JPanel();
            if(event.type == 1 && cSwitch < 1) continue;

            panel.setBounds((timeSlot * event.start) + offset, 150, timeSlot, 50);
            frame.add(panel);
            if (event.type == 0) {
                panel.setBackground(event.process.color);
            } else if (event.type == 1) {
                if (cSwitch < 1) continue;
                panel.setBackground(Color.red);
            } else {
                panel.setBackground(Color.blue);
            }
        }
    }

    public static void writeEvents() {
        for (Event event : events) {
            if (event.type == 0) {
                System.out.println(event.start + ": Process " + event.process.pid + " is running");

            } else if (event.type == 1) {
                System.out.println(event.start + ": Context Switch");
            } else {
                System.out.println(event.start + ": IDLE");
            }
        }
    }

    GUI() {
        this.processes = GuiNew.processes;
        this.type = GuiNew.type;
        this.cSwitch = GuiNew.cSwitch;
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 400);
        frame.setLayout(null);

        Scheduler scheduler;
        switch (type) {
            case 0: {
                scheduler = new PriorityScheduler(processes, cSwitch);
            }
            case 1: {
                scheduler = new ShortestFirst(processes, cSwitch);
            }
            case 2: {
                scheduler = new SRTF_Scheduling(processes, cSwitch);
            }
            default: {
                scheduler = new AGAT_Scheduling(processes, cSwitch);
            }
        }
        scheduler.execute();
        drawEvents();
        writeEvents();
    }

}
