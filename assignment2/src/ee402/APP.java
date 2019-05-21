package ee402;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class APP extends Canvas{
	
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	public List<Integer> last20 = new ArrayList<Integer>();
	private JCheckBox boxes[] = new JCheckBox[5];
	private Color[] color = new Color[5];
	static int radius = 5;
	
	public APP() {
		this.setSize(WIDTH,HEIGHT);
		this.setBackground(Color.WHITE);
		 color[0] = Color.red;
	     color[1] = Color.blue;
	     color[2] = Color.yellow;
	     color[3] = Color.green;
	     color[4] = Color.ORANGE;
		this.repaint();
	}
	
	
	public void UpdateTemperature (List<Integer> last20,JCheckBox boxes[]) {
		this.last20 = last20;
		for(int i=0;i<5;i++) {
			this.boxes[i] = boxes[i];
		}
		this.repaint(); 
	}
	
	//paints the graph
		public void paint(Graphics g) {
		
		for(int j=0; j <5;j++) {
			if(boxes[j].isSelected()) {
			Graphics2D g2d = (Graphics2D) g; 

			g2d.setColor(color[j]);
			if(last20 != null) {
				for(int i=0; i<last20.size(); i++){			
					int y1 = last20.get(i);
					g2d.drawLine(i*25, 200+y1, (i+1)*25, 200+y1); 
					g2d.drawOval(i*25,195+ y1, 2*radius, 2*radius); 	
				}
			}
			}
		}
		}

}
