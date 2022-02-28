package uno;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


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
	
	Game game;
	
	public void createUI() {
		
		window = new JFrame();
		window.setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.gray);
		window.setLayout(new GridLayout(4,1));
		window.setVisible(true);
		
		//row 1
		topPanel = new JPanel();
		bot0 = new JLabel("bot0", SwingConstants.CENTER);
		blankPanel4 = new JPanel();
		blankPanel5 = new JPanel();
		
		topPanel.setLayout(new GridLayout(1,3));
		topPanel.setBackground(Color.gray);
		blankPanel4.setBackground(Color.darkGray);
		blankPanel5.setBackground(Color.darkGray);
		bot0.setPreferredSize(new Dimension(114, 150));
		
//		JButton newButton = new JButton();
		//img = new ImageIcon(getClass().getClassLoader().getResource("res/Blue0.png"));
		//img2 = new ImageIcon(getClass().getClassLoader().getResource("res/Blue1.png"));
//		newButton.setIcon(img);
//		topPanel.add(newButton);
		
		topPanel.add(blankPanel4);
		topPanel.add(bot0);
		topPanel.add(blankPanel5);
		
		//row 2
		deckPanel = new JPanel();
		bot1 = new JLabel("bot1", SwingConstants.CENTER);
		blankPanel1 = new JPanel();
		blankPanel2 = new JPanel();
		blankPanel3 = new JPanel();
		deck = new JLabel("deck", SwingConstants.CENTER);
		discard = new JLabel("discard", SwingConstants.CENTER);
		bot2 = new JLabel("bot2", SwingConstants.CENTER);
		
		blankPanel1.setBackground(Color.darkGray);
		blankPanel2.setBackground(Color.darkGray);
		blankPanel3.setBackground(Color.darkGray);
		deckPanel.setLayout(new GridLayout(1,7));
		deckPanel.setBackground(Color.gray);
		discard.setOpaque(true);
		discard.setForeground(Color.white);
		
		deckPanel.add(bot1);
		deckPanel.add(blankPanel1);
		deckPanel.add(deck);
		deckPanel.add(blankPanel2);
		deckPanel.add(discard);
		deckPanel.add(blankPanel3);
		deckPanel.add(bot2);
		
		//row 3
		cardPanel = new JPanel();
		cardPanel.setBackground(Color.darkGray);
		cardPanel.setLayout(new GridLayout(1,1));
		
		//row 4
		bottomPanel = new JPanel();
		blankPanel6 = new JPanel();
		blankPanel7 = new JPanel();
		draw = new JButton("Draw");
		pass = new JButton("Pass");
		message = new JLabel("Click a card to play", SwingConstants.CENTER);
		message.setForeground(Color.darkGray);
		bottomPanel.setLayout(new GridLayout(1,5));
		bottomPanel.setBackground(Color.darkGray);
		blankPanel6.setBackground(Color.darkGray);
		blankPanel7.setBackground(Color.darkGray);
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
	
	public void updateUI() {
		
		bot0.setText(game.players.get(0).getName()+": "+game.players.get(0).getHandSize());
		bot1.setText(game.players.get(1).getName()+": "+game.players.get(1).getHandSize());
		bot2.setText(game.players.get(2).getName()+": "+game.players.get(2).getHandSize());
		deck.setText("Deck: "+game.deck.getSize());
		discard.setText("Discard: "+game.discard.getSize());
		discard.setBackground(Color.blue);
		updateCardPanel();
	}
	
	public void updateCardPanel() {
		
		for(UnoCard c: game.players.get(3).getHand()) {
			JButton newButton = new JButton(); 
			newButton.setIcon(c.getIcon());
			newButton.setText(c.getRankAsString());
			newButton.setBackground(c.getColorAsColor());
			cardPanel.add(newButton);
		}
		cardPanel.revalidate();
		cardPanel.repaint();
	}
	
	public void getGame(Game g) {
		game = g;
	}
	
	

}
