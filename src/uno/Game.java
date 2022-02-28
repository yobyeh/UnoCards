package uno;

import java.util.ArrayList;
import java.util.Collections;

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
	
	public void play() {
		ui.createUI();
		ui.getGame(this);
		
		setup();
		deal();
		ui.updateUI();
		
		for(int i = 0; i < 1; i++) {
			System.out.println("-------------------------- Game: "+(i + 1));
			while(!gameOver) {
				Player p = players.get(turnTrack);			
				if(p.takeTurn(deck, discard)) {
					if(winCheck(p)) {
					break;
				}
					checkDiscardRule();
				}
				advanceTurnTrack();
			}
			cleanUp();
			deal();
		}
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
		
		for(int i = 0; i < 4; i++) {
			Player newPlayer = new Player(names.get(i));
			newPlayer.setUser(false);
			players.add(newPlayer);
		}
		//players.add(new Player(true));
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
	}
	
	private void cleanUp() {
		for(Player p: players) {
			p.clearHand();
		}
		deck.clear();
		discard.clear();
		gameOver = false;
	}
	
	public void checkDiscardRule() {
		int r = discard.getTopCard().getRankInt();
		if(r == 10) {
			advanceTurnTrack();
		}else if (r == 11) {
			inReverse = !inReverse;
		}else if(r == 12){
			players.get(nextPlayerLocation()).drawCards(2, deck, discard); 
		}else if(r == 13) {
			players.get(nextPlayerLocation()).drawCards(4, deck, discard);
		}
		
	}
	
	private boolean winCheck(Player p) {
		if(p.getHandSize() == 0) {
			gameOver = true;
			System.out.println(p.getName()+" Wins------");
			return true;
		}else {
			return false;
		}
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

	

	
	

}
