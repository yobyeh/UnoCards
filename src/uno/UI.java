package uno;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UI extends JFrame implements ActionListener {
	
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
	JLayeredPane layerdPane;
	
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
		bot1 = new JLabel("bot1", SwingConstants.CENTER);
		blankPanel4 = new JPanel();
		blankPanel5 = new JPanel();
		
		topPanel.setLayout(new GridLayout(1,3));
		topPanel.setBackground(Color.gray);
		blankPanel4.setBackground(Color.darkGray);
		blankPanel5.setBackground(Color.darkGray);
		bot1.setPreferredSize(new Dimension(114, 150));
		
		topPanel.add(blankPanel4);
		topPanel.add(bot1);
		topPanel.add(blankPanel5);
		
		//row 2
		deckPanel = new JPanel();
		bot0 = new JLabel("bot0", SwingConstants.CENTER);
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
		
		deckPanel.add(bot0);
		deckPanel.add(blankPanel1);
		deckPanel.add(deck);
		deckPanel.add(blankPanel2);
		deckPanel.add(discard);
		deckPanel.add(blankPanel3);
		deckPanel.add(bot2);
		
		//row 3
		cardPanel = new JPanel();
		cardPanel.setBackground(Color.darkGray);
		cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.X_AXIS));
		
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
		draw.addActionListener(this);
		pass.addActionListener(this);
		
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
		discard.setText(game.discard.getTopCard().getRankAsString());
		discard.setBackground(game.discard.getTopCard().getColorAsColor());
		updateCardPanel();
	}
	
	public void updateCardPanel() {
		cardPanel.removeAll();
		for(UnoCard c: game.players.get(3).getHand()) {
			JButton newButton = new JButton(); 
			
			newButton.setText(Integer.toString(c.getRankInt()));
			newButton.setBackground(c.getColorAsColor());
			//newButton.setMinimumSize(new java.awt.Dimension(100, 100));
			newButton.setMaximumSize(new java.awt.Dimension(85, 112));
			newButton.addActionListener(this);
			cardPanel.add(newButton);
		}
		cardPanel.revalidate();
		cardPanel.repaint();
	}
	
	
	public void getGame(Game g) {
		game = g;
	}
	
	public void winDialog() {
		
		int answer = JOptionPane.showConfirmDialog(null, game.getCurrentPlayer().getName()+" Win", "Play again?", JOptionPane.YES_NO_OPTION);
		switch(answer) {
			case 0: game.nextGame();
					break;
			case 1: System.exit(0);
					break;
			case -1: System.exit(0);
					break;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == draw) {
			System.out.println("draw--------------------");
			game.userDraw();
		}else if(e.getSource() == pass) {
			System.out.println("pass--------------------");
			game.userPass();
		}else {
			JButton button = (JButton) e.getSource();
			game.userSelected(button.getText(), button.getBackground());
			
		}
		
	}
	
	

}
