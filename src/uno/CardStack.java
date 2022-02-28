package uno;

import java.util.ArrayList;
import java.util.Collections;

public class CardStack {
	
private ArrayList<UnoCard> cardsInStack = new ArrayList<UnoCard>();
	
	public void populateDeck() {
		cardsInStack.clear();
		
		//colors
		for(int j = 0; j < 4; j++) {
			cardsInStack.add(new UnoCard(0, j));
			
			//ranks
			for(int k = 1; k < 13; k++) {
				cardsInStack.add(new UnoCard(k, j));
				cardsInStack.add(new UnoCard(k, j));
			}
		}
		
		//wild
		for(int i = 0; i < 4; i++) {
			cardsInStack.add(new UnoCard(13, 4));
			cardsInStack.add(new UnoCard(14, 4));
		}
	}
	
	public void shuffle() {
		Collections.shuffle(cardsInStack);
	}
	
	public UnoCard getTopCard() {
		return cardsInStack.get(cardsInStack.size() -1);
	}
	
	public void putCardOnTop (UnoCard card) {
		cardsInStack.add(card);
	}
	
	public UnoCard pullTopCard() {
		return cardsInStack.remove(cardsInStack.size() -1);
	}
	public UnoCard pullBottomCard() {
		return cardsInStack.remove(0);
	}
	
	public void printSize() {
		System.out.println(cardsInStack.size());
	}
	
	public int getSize() {
		return cardsInStack.size();
	}
	
	public void printCardsInStack() {
		for(UnoCard c: cardsInStack) {
			c.printRankAndColor();
		}
	}
	
	public void clear() {
		cardsInStack.clear();
	}
	
	public int size() {
		return cardsInStack.size();
	}
	
	public boolean isEmpty() {
		if(cardsInStack.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public void removeTempWilds() {
		for(UnoCard c: cardsInStack) {
			c.setTempColorInt(-1);
		}
	}


}
