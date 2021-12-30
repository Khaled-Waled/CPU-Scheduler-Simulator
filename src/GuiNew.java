import java.awt.Color ;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField; 
public class GuiNew extends JFrame implements ActionListener {
	
	private static GuiNew gui ; 
	public static GuiNew getInstance () {
		if (gui == null)
		{
			gui = new GuiNew() ; 
		}
		return gui;
	}
	
	JLabel name = new JLabel ("Process Name") ; 
	JLabel id = new JLabel ("Process ID ") ; 
	JLabel color = new JLabel ("process Color ") ; 
	JLabel arrivalTime = new JLabel ("Arrival Time ") ;
	JLabel priority = new JLabel ("Priority") ;
	JLabel quantum = new JLabel ("Quantum ") ; 
	JLabel contextSwitch = new JLabel ("context swit ") ; 
	JLabel burst = new JLabel("burst time") ; 
	
	
	JButton colorChooser = new JButton ("choose Color ") ; 
	JButton Add = new JButton ("Add") ; 
	JButton Run = new JButton ("Run") ; 
	
	
	JTextField txtName = new JTextField() ;
	JTextField txtID = new JTextField() ;
	JTextField txtArrival = new JTextField() ;
	JTextField txtPriority = new JTextField() ;
	JTextField txtQuantum = new JTextField() ;
	JTextField txtFactor = new JTextField() ;
	JTextField txtBurst = new JTextField () ; 
	
	JPanel runningPanel = new JPanel () ; 
	JPanel arr[] = new JPanel[100] ; 	
	JPanel trie = new JPanel() ; 
	JPanel panel2 = new JPanel() ; 
	
	JRadioButton priorityScheduling = new JRadioButton("Priority") ; 
	JRadioButton sjf = new JRadioButton("SJF") ; 
	JRadioButton srtf = new JRadioButton("SRTF") ; 
	JRadioButton agat = new JRadioButton("AGAT") ; 
	ButtonGroup group = new ButtonGroup () ; 
	
	
	Color chooserColor ;
	int arrival ; 
	int quantumTime ; 
	int factor ; 
	int pid ; 
	int priorityx ; 
	int burstx ; 
	static int cSwitch = 0  ;
	static int counter = 0 ;
	static int type = 0; 
	
	static ArrayList<Process> processes = new ArrayList<>();
	
	public static int numberOfProcess=1;
	public static int totalRunningTime=1;
	

	
	private GuiNew()
	{
		this.setVisible(true); 
		this.setSize(500,600) ; 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.add(name) ; 
		name.setBounds(0,0,100,50) ;
		this.add(txtName) ; 
		txtName.setBounds(110,18,100,20); 
		this.add(id) ; 
		id.setBounds(0,50,100,50) ; 
		this.add(txtID) ; 
		txtID.setBounds(110,70,100,20) ; 
		this.add(color) ; 
		color.setBounds(0,100,100,50) ; 
		this.add(colorChooser) ; 
		colorChooser.setBounds(110,117,120,20) ; 
		colorChooser.addActionListener(this);
		this.add(arrivalTime) ; 
		arrivalTime.setBounds(0,150,100,50) ; 
		this.add(txtArrival) ; 
		txtArrival.setBounds(110,170,100,20) ; 
		this.add(priority) ; 
		priority.setBounds(0,200,100,50) ; 
		this.add(txtPriority) ; 
		txtPriority.setBounds(110,220,100,20) ; 
		this.add(contextSwitch) ; 
		contextSwitch.setBounds(0,250,100,50) ; 
		this.add(txtFactor) ; 
		txtFactor.setBounds(110,270,100,20) ;
		this.add(burst) ; 
		burst.setBounds(0 , 300 , 100 , 30 ) ; 
		this.add(txtBurst) ; 
		txtBurst.setBounds(110,310,100,20);
		this.add(Add) ; 
		Add.setBounds(0,350,100,30) ;
		Add.addActionListener(this);
		this.add(Run) ; 
		Run.setBounds(0,400,100,30) ; 
		Run.addActionListener(this);
		this.add(priorityScheduling) ; 
		this.add(sjf) ;
		this.add(srtf); 
		this.add(agat) ; 
		priorityScheduling.setBounds(50, 450 , 100, 50); 
		sjf.setBounds(200, 450 , 100, 50);
		srtf.setBounds(320, 450 , 100, 50);
		agat.setBounds(400, 450 , 100, 50);
		group.add(priorityScheduling);
		group.add(srtf);
		group.add(sjf);
		group.add(agat);
		//this.add(runningPanel) ; 
		runningPanel.setBounds(0,600,1280,80) ; 
		runningPanel.setBackground(Color.gray);
		//this.add(trie) ; 
		trie.setBounds(400,0,400,400);
		trie.setBackground(Color.white); 
		
		trie.setLayout(new FlowLayout()) ; 
		
		//JTextArea outputTextArea = new JTextArea("",5,20);
	    JScrollPane scrollPane = new JScrollPane();    
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	   // runningPanel.add(scrollPane);
	    scrollPane.setPreferredSize(new Dimension(1200,10));
//	    txtName.setPreferredSize(new Dimension(20,20));
//	    trie.add(txtName) ; 
	    for (int i = 0 ; i < 3 ; i ++)
	    {
	    	JPanel p = new JPanel() ; 
	    	p.setPreferredSize(new Dimension(20,20));
	    	p.setBackground(Color.red);
	    	runningPanel.add(p) ; 
	    	try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    }
		
		
	}
	
	
	
	
	



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == colorChooser)
		{
			JColorChooser choose = new JColorChooser() ;
			chooserColor = choose.showDialog(null, "Choose the Process color", Color.red);
					
			
		}
		else if (e.getSource() == Add)
		{
			
			arrival = Integer.parseInt(txtArrival.getText()) ; 
			quantumTime = Integer.parseInt(txtPriority.getText()) ; 
			cSwitch = Integer.parseInt(txtFactor.getText()) ; 
			pid = Integer.parseInt(txtID.getText()) ; 
			priorityx = Integer.parseInt(txtPriority.getText()) ; 
			burstx = Integer.parseInt(txtBurst.getText());
			processes.add(new Process(txtName.getText(), pid, chooserColor, arrival, burstx, priorityx, quantumTime));
			panel2.setBackground(chooserColor);
			panel2.setPreferredSize(new Dimension(20,20));
			//trie.add(panel2); 
			//arr[counter] = panel2 ; 
			JLabel data = new JLabel() ; 
			data.setText(txtName.getText());
			trie.add(data) ; 
			System.out.println("reached") ; 
			
//			panel2.setBounds(200,299,40,40) ; 
//			this.add(panel2) ; 
			arr[counter] = panel2 ; 
			trie.add(arr[counter]) ; 
			counter ++ ;
			
			txtName.setText("") ;
			txtID.setText("");
			txtArrival.setText("");
			txtPriority.setText("");
			txtQuantum.setText("");
			txtFactor.setText("");
			txtBurst.setText("");
			
			
		}
		else if (e.getSource() == Run) 
		{
			this.dispose() ; 
			GUI gui = new GUI() ; 

		}
			
		else if (e.getSource() == priorityScheduling)
		{
			type = 0 ;
		}
		else if (e.getSource() == sjf)
		{
			type = 1 ;
		}	
		else if (e.getSource() == srtf)
		{
			type = 2;
		}		
		else if (e.getSource() == agat)
		{
			type = 3;
		}	
		}
		
	
	


}




