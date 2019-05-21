package ee402;

import java.awt.*;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class TempAndDateGui extends JFrame implements ActionListener{
	
	private JCheckBox boxes[] = new JCheckBox[5];
	private List<Integer> last20 = new ArrayList<Integer>();
	private JButton SampleRate, ClearSampleRate;
	private JButton LEDON, LEDOFF;
	private int SampleValue= 1000;
	private String ledcommand= "";
	private JTextField TextSampleRate;
	private APP canvas;
	private LEDcontroller led = new LEDcontroller();
	
	public TempAndDateGui() {
		super();
		Container cont = getContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//panel 3 - sample rate text field, sample rate button, clear text field button 
		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(1,3));	
		TextSampleRate = new JTextField(5);
		p3.add(this.TextSampleRate);
		SampleRate = new JButton("Sample Rate");
		p3.add(this.SampleRate);
		ClearSampleRate = new JButton("Clear text");
		p3.add(this.ClearSampleRate);
		SampleRate.addActionListener(this);
		ClearSampleRate.addActionListener(this);
		
		JPanel p4 = new JPanel();
		p4.setLayout(new GridLayout(2,1));	
		LEDON =new JButton("LED ON");
		p4.add(this.LEDON);
		LEDOFF = new JButton("LED OFF");
		p4.add(this.LEDOFF);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		canvas = new APP();
		p1.add(this.canvas, BorderLayout.SOUTH);
		
		//panel 2 - server check boxes
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(5,1));		
		for(int i = 0; i<5; i++) {
			boxes[i] = new JCheckBox("Server "+ (i+1));
			boxes[i].addActionListener(this);
			p2.add(this.boxes[i]);
		}

		//layout of all panels
		cont.setLayout(new BorderLayout());
		cont.add(p3, BorderLayout.NORTH);
		cont.add(p2, BorderLayout.WEST);
		cont.add(p4, BorderLayout.EAST);
		cont.add(p1, BorderLayout.SOUTH);

		this.pack();
		this.setVisible(true); 
		
		
	}
	public int getSampleRate() {
		int a = this.SampleValue;
		return a;
	}
	public void Arraytemp (List<Integer> last2) {
		this.last20 = last2;
		canvas.UpdateTemperature(last20,boxes);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("An action event has occured");
		//takes the string input in the text field 
		int sampleNum  = (new Integer(this.TextSampleRate.getText())).intValue();
		SampleValue = sampleNum;	
		//clears the text field
		if(e.getSource().equals(ClearSampleRate)) {
			this.TextSampleRate.setText("");			
		}else if(e.getSource().equals(LEDON)) {
			ledcommand= "LEDON";
		}else if(e.getSource().equals(LEDOFF)) {
			ledcommand= "LEDOFF";			
		}
		
		

	}


}
