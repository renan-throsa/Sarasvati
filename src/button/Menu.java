package button;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JPanel {
	int x, y, width, height;
	Color color;
	JFrame parent;
	
	Point onCliked = new Point(0,0);
	
	public Menu(int x, int y, int width, int height, Color color, JFrame parent) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.parent = parent;
		
		setBounds(x, y, width, height);
		setVisible(true);
		
		setBackground(Color.WHITE);
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				onCliked = new Point(e.getX(), e.getY());
			}
		
			
			@Override
			public void mouseDragged(MouseEvent e) {
				Point mouse = MouseInfo.getPointerInfo().getLocation();
				parent.setLocation(mouse.x - onCliked.x, mouse.y - onCliked.y);
			}
		};
		
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Graphics2D g2 = Helper.getSmoothedGraphics(g);
		
		g2.setColor(Color.GREEN);
		g2.setFont(new Font("Consolas",Font.BOLD,12));
		
		String title = "Sarasvat";
		
		FontMetrics fm = g2.getFontMetrics(new Font("Consolas",Font.ITALIC,12));
		
		g2.drawString(title, width / 2 - fm.stringWidth(title) / 2, height / 2 + fm.getHeight() / 2 - fm.getDescent());
	}
}
