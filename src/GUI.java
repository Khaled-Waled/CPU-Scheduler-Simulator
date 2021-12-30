import java.awt.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import javax.swing.*;

public class GUI {
    private static ArrayList<Event> events = new ArrayList<>();
    static ArrayList<Process> processes = new ArrayList<>();
    int type = 0;
    static int cSwitch = 0;
    static JFrame frame = new JFrame();


    static void receiveEvent(Event event) {
        events.add(event);
    }

    public static void drawEvents() {
        JLabel label = new JLabel();
        label.setText("Timeline");
        label.setBounds(10, 10, 100, 60);
        label.setFont(new Font("Verdana", Font.PLAIN, 18));
        frame.add(label);

        JTable table = makeTable();
        table.setBounds(30, 300, 1200, 500);
        table.setBackground(new Color(0, 0, 0, 0));
        table.setRowHeight(30);
        table.setFont(new Font("Verdana", Font.PLAIN, 18));
        frame.add(table);
        int timeSlot = (1200) / events.size();
        int offset = 30;

        for (Event event : events) {
            JPanel panel = new JPanel();
            if (event.type == 1 && cSwitch < 1) continue;

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

    static JTable makeTable() {
        String[] title = {"Name", "ID", "Burst Time", "Arrival Time", "Turnaround Time", "Waiting Time"};
        String[][] data = new String[processes.size() + 1][6];
        data[0] = title;
        for (int i = 0; i < processes.size(); i++) {

            Process curr = processes.get(i);

            data[i + 1][0] = curr.name;
            data[i + 1][1] = Integer.toString(curr.pid);
            data[i + 1][2] = Integer.toString(curr.burstTime);
            data[i + 1][3] = Integer.toString(curr.arrivalTime);
            data[i + 1][4] = Integer.toString(curr.turnAroundTime);
            data[i + 1][5] = Integer.toString(curr.waitingTime);
        }

        return new JTable(data, title);
    }

    GUI(int t) {
        this.processes = GuiNew.processes;
        this.type = t;
        this.cSwitch = GuiNew.cSwitch;
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLayout(null);

        Scheduler scheduler;

        if (t == 0)
            scheduler = new PriorityScheduler(processes, cSwitch);
        else if (t == 1)
            scheduler = new ShortestFirst(processes, cSwitch);
        else if (t == 2)
            scheduler = new SRTF_Scheduling(processes, cSwitch);
        else
            scheduler = new AGAT_Scheduling(processes, cSwitch);

        scheduler.execute();
        scheduler.updateParameters();
        drawEvents();
        writeEvents();
    }

}
