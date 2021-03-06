package graphicalElements;

import javax.swing.*;

import gameCommons.IFrog;
import util.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FroggerGraphic extends JPanel implements IFroggerGraphics, KeyListener {
	private ArrayList<Element> elementsToDisplay;
	private int pixelByCase = 16;
	private int width;
	private int height;
	private IFrog frog1;
	private IFrog frog2;
	private JFrame frame;

	public FroggerGraphic(int width, int height) {
		this.width = width;
		this.height = height;
		elementsToDisplay = new ArrayList<Element>();

		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(width * pixelByCase, height * pixelByCase));

		JFrame frame = new JFrame("Frogger");
		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Element e : elementsToDisplay) {
			g.setColor(e.color);
			g.fillRect(pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), pixelByCase, pixelByCase - 1);
		}
	}

	public void keyTyped(KeyEvent e) {
	}


	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			frog1.move(Direction.up);
			break;
		case KeyEvent.VK_DOWN:
			frog1.move(Direction.down);
			break;
		case KeyEvent.VK_LEFT:
			frog1.move(Direction.left);
			break;
		case KeyEvent.VK_RIGHT:
			frog1.move(Direction.right);
			break;
		case KeyEvent.VK_Z:
			frog2.move2(Direction.up);
			break;
		case KeyEvent.VK_S:
			frog2.move2(Direction.down);
			break;
		case KeyEvent.VK_Q:
			frog2.move2(Direction.left);
			break;
		case KeyEvent.VK_D:
			frog2.move2(Direction.right);
		}
	}

	public void clear() {
		this.elementsToDisplay.clear();
	}

	public void add(Element e) {
		this.elementsToDisplay.add(e);
	}

	public void setFrog(IFrog frog) {
		this.frog1 = frog;
	}
	public void setFrog2(IFrog frog1, IFrog frog2) {
		this.frog1 = frog1;
		this.frog2 = frog2;
	}

	public void endGameScreen(String s) {
		frame.remove(this);
		JLabel label = new JLabel(s);
		label.setFont(new Font("Verdana", 1, 13));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setSize(this.getSize());
		frame.getContentPane().add(label);
		frame.repaint();

	}


	public void endGameScreen2(String s1, String s2, String s3, String s4) {
		frame.remove(this);
		JLabel label1 = new JLabel(s1);
		label1.setFont(new Font("Verdana", 1, 15));
		label1.setLocation(145, height/2 -40);
		label1.setSize(this.getSize());

		JLabel label2 = new JLabel(s2);
		label2.setFont(new Font("Verdana", 1, 15));
		label2.setLocation(140, height/2-20);
		label2.setSize(this.getSize());

		JLabel label3 = new JLabel(s3);
		label3.setFont(new Font("Verdana", 1, 15));
		label3.setLocation(140, height/2);
		label3.setSize(this.getSize());

		JLabel label4 = new JLabel(s4);
		label4.setFont(new Font("Verdana", 1, 15));
		label4.setLocation(165, height/2 + 20);
		label4.setSize(this.getSize());

		frame.getContentPane().add(label1);
		frame.getContentPane().add(label2);
		frame.getContentPane().add(label3);
		frame.getContentPane().add(label4);
		frame.repaint();
	}

}
