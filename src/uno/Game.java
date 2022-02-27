package uno;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
	
	//draw card when re-shuffle needed
	
	
	
	private CardStack deck;
	private CardStack discard;
	private ArrayList<Player> bots;
	private Player user;
	private boolean inReverse;
	private boolean gameOver;
	private ArrayList<String> names;
	
	public void play() {
		setup();

		
		deal();
			
		for(Player p: bots) {
			p.takeTurn(deck, discard);
			winCheck(p);
			checkDiscardRule();
		}
			
			
		
		
		cleanUp();
		deal();
		
		
		
	}
	
	
	private void setup() {
		inReverse = false;
		deck = new CardStack();
		deck.populateDeck();
		discard = new CardStack();
		bots = new ArrayList<Player>();
		names = new ArrayList<String>();
		names.add("John");
		names.add("Alec");
		names.add("Alex");
		names.add("Tammy");
		names.add("Autumn");
		names.add("Steve");
		Collections.shuffle(names);
		
		for(int i = 0; i < 3; i++) {
			Player newPlayer = new Player(names.get(i));
			bots.add(newPlayer);
		}
	}
	
	private void deal() {
		deck.clear();
		discard.clear();
		deck.populateDeck();
		deck.shuffle();
		for(int i = 0; i < 7; i++) {
			for(Player p: bots) {
				p.drawCards(1, deck);
			}
		}
		discard.putCardOnTop(deck.pullTopCard());
	}
	
	private void cleanUp() {
		for(Player p: bots) {
			p.clearHand();
		}
		deck.clear();
		discard.clear();
	}
	
	private void reShuffle() {
		discard.removeTempWilds();
		while(!discard.isEmpty()) {
			deck.putCardOnTop(discard.pullTopCard());
		}
	}
	
	public void checkDiscardRule() {
		
	}
	
	private boolean winCheck(Player p) {
		if(p.getHandSize() == 0) {
			gameOver = true;
			return true;
		}else {
			return false;
		}
	}

	

	
	

}
