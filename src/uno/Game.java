package uno;

import java.util.ArrayList;
import java.util.Collections;
import java.awt.Color;

public class Game {
	
	//draw card when re-shuffle needed
	
	public UI ui = new UI();
	
	public CardStack deck;
	public CardStack discard;
	private int turnTrack;
	public ArrayList<Player> players;
	private boolean inReverse;
	private boolean gameOver;
	private ArrayList<String> names;
	
	
	//take turn
	//win check
	//discard rule check
	//advance turn rack
	public void play() {
		ui.createUI();
		ui.getGame(this);
		
		setup();
		deal();
		ui.updateUI();
		botTrun();
		
		
	}
	
	
	private void setup() {
		deck = new CardStack();
		deck.populateDeck();
		discard = new CardStack();
		players = new ArrayList<Player>();
		names = new ArrayList<String>();
		names.add("John");
		names.add("Alec");
		names.add("Alex");
		names.add("Tammy");
		names.add("Autumn");
		names.add("Steve");
		names.add("Angel");
		Collections.shuffle(names);
		
		for(int i = 0; i < 3; i++) {
			Player newPlayer = new Player(names.get(i));
			newPlayer.setUser(false);
			players.add(newPlayer);
		}
		Player newPlayer = new Player("You");
		newPlayer.setUser(true);
		players.add(newPlayer);
	}
	
	private void deal() {
		turnTrack = 0;
		inReverse = false;
		deck.clear();
		discard.clear();
		deck.populateDeck();
		deck.shuffle();
		for(int i = 0; i < 7; i++) {
			for(Player p: players) {
				p.drawCards(1, deck, discard);
			}
		}
		discard.putCardOnTop(deck.pullTopCard());
		while(discard.getTopCard().getColorInt() == 4) {
			discard.putCardOnTop(deck.pullTopCard());
		}
	}
	
	private void cleanUp() {
		for(Player p: players) {
			p.clearHand();
		}
		turnTrack = 0;
		deck.clear();
		discard.clear();
		gameOver = false;
	}
	
	public void botTrun() {
		
				
		while(players.get(turnTrack).isUser() != true) {
			Player p = players.get(turnTrack);
			if(p.takeTurn(deck, discard)) {
				if(winCheck(p)) {
					return;
				}
					
				checkDiscardRule();
				}
				advanceTurnTrack();
		}
		
		ui.updateUI();
	}
	
	public void checkDiscardRule() {
		int r = discard.getTopCard().getRankInt();
		if(r == 10) {
			advanceTurnTrack();
		}else if (r == 11) {
			inReverse = !inReverse;
		}else if(r == 12){
			players.get(nextPlayerLocation()).drawCards(2, deck, discard); 
		}else if(r == 14) {
			players.get(nextPlayerLocation()).drawCards(4, deck, discard);
		}
		
	}
	
	private boolean winCheck(Player p) {
		if(p.getHandSize() == 0) {
			gameOver = true;
			System.out.println(p.getName()+" Wins------");
			ui.winDialog();
			return true;
		}
		return false;
			
	}
	
	private void advanceTurnTrack() {
		if(!inReverse) {
			turnTrack++;
			if(turnTrack == 4) {
				turnTrack = 0;
			}
		}else {
			turnTrack--;
			if(turnTrack == -1) {
				turnTrack = 3;
			}
		}
	}
	
	private int nextPlayerLocation() {
		
		if(!inReverse) {
			if(turnTrack == 3) {
				return 0;
			}
			return turnTrack + 1;
		}else {
			if(turnTrack == 0) {
				return 3;
			}
			return turnTrack -1;
		}
	}
	
	public void userDraw() {
		players.get(turnTrack).drawCards(1, deck, discard);
		ui.updateCardPanel();
	}
	
	public void userPass() {
		advanceTurnTrack();
		botTrun();
		
	}
	
	public Player getCurrentPlayer() {
		return players.get(turnTrack);
	}
	
	public void nextGame() {
		cleanUp();
		deal();
		botTrun();
	}
	
	public void setDiscardsTempWildColor(int c) {
		discard.getTopCard().setTempColorInt(c);
	}
	
	public void userSelected(String text, Color color) {
		int i = getCurrentPlayer().userPlay(text, color, discard);
		if(winCheck(getCurrentPlayer())) {
			return;
		}
		
		if(i == 2) {
			ui.wildDialog();
		}
		if(i == 1 || i == 2) {
			System.out.println(getCurrentPlayer().getName() +" play a "+discard.getTopCard().getColorAsString()
					+" "+discard.getTopCard().getRankAsString());
			checkDiscardRule();
			advanceTurnTrack();
			botTrun();
		}
		
		
	}


}
