package uno;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UI {
	
	JFrame window;
	JPanel topPanel;
	JPanel deckPanel;
	JPanel cardPanel;
	JPanel bottomPanel;
	JPanel blankPanel1;
	JPanel blankPanel2;
	JPanel blankPanel3;
	JPanel blankPanel4;
	JPanel blankPanel5;
	JPanel blankPanel6;
	JPanel blankPanel7;
	JLabel bot0;
	JLabel bot1;
	JLabel bot2;
	JLabel deck;
	JLabel discard;
	JButton draw;
	JButton pass;
	JLabel message;
	
	public void createUI() {
		
		window = new JFrame();
		window.setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.gray);
		window.setLayout(new GridLayout(4,1));
		window.setVisible(true);
		
		//row 1
		topPanel = new JPanel();
		bot0 = new JLabel("bot0");
		blankPanel4 = new JPanel();
		blankPanel5 = new JPanel();
		
		topPanel.setLayout(new GridLayout(1,3));
		topPanel.setBackground(Color.blue);
		bot0.setForeground(Color.white);
		
		topPanel.add(blankPanel4);
		topPanel.add(bot0);
		topPanel.add(blankPanel5);
		
		//row 2
		deckPanel = new JPanel();
		bot1 = new JLabel("bot1");
		blankPanel1 = new JPanel();
		blankPanel2 = new JPanel();
		blankPanel3 = new JPanel();
		deck = new JLabel("deck");
		discard = new JLabel("discard");
		bot2 = new JLabel("bot2");
		
		deckPanel.setLayout(new GridLayout(1,7));
		deckPanel.setBackground(Color.gray);
		bot1.setForeground(Color.white);
		
		deckPanel.add(bot1);
		deckPanel.add(blankPanel1);
		deckPanel.add(deck);
		deckPanel.add(blankPanel2);
		deckPanel.add(discard);
		deckPanel.add(blankPanel3);
		deckPanel.add(bot2);
		
		//row 3
		cardPanel = new JPanel();
		cardPanel.setBackground(Color.blue);
		
		//row 4
		bottomPanel = new JPanel();
		blankPanel6 = new JPanel();
		blankPanel7 = new JPanel();
		draw = new JButton("Draw");
		pass = new JButton("Pass");
		message = new JLabel("Click a card to play");
		bottomPanel.setLayout(new GridLayout(1,5));
		bottomPanel.setBackground(Color.pink);
		bottomPanel.add(blankPanel6);
		bottomPanel.add(draw);
		bottomPanel.add(message);
		bottomPanel.add(pass);
		bottomPanel.add(blankPanel7);
		
		window.add(topPanel);
		window.add(deckPanel);
		window.add(cardPanel);
		window.add(bottomPanel);
		window.setVisible(true);
		
	}
	
	

}
