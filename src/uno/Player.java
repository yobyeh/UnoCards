package uno;

import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<UnoCard> hand = new ArrayList<UnoCard>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void drawCards(int amount, CardStack deck) {
		
		for(int i = 0; i < amount; i++) {
		hand.add(deck.pullTopCard());
		}
	}
	
	public ArrayList<Integer> getPlayableCards(UnoCard card) {
		ArrayList<Integer> playableCards = new ArrayList<Integer>();
		
		for(int i = 0; i > hand.size(); i++) {
			//check discard wild
			if(card.getColorInt() == 4) {
				//check against temporary color
				if(hand.get(i).getColorInt() == card.getTempColorInt()) {
					playableCards.add(i);
					continue;
				}
			//discard not wild
			}else {
				//check against color
				if(hand.get(i).getColorInt() == card.getColorInt()) {
					playableCards.add(i);
					continue;
				}
			}
			//check against rank
			if(hand.get(i).getRankInt() == card.getRankInt()) {
				playableCards.add(i);
				continue;
			}
			//check hand wild
			if(hand.get(i).getColorInt() == 4) {
				playableCards.add(i);
			}
		}
		return playableCards;
	}
	
	public boolean checkCanPlay(CardStack discard) {
		if(getPlayableCards(discard.getTopCard()).isEmpty()) {
			return false;
		}
		return true;
	}
	
	//picks first playable
	public int pickCard(CardStack discard) {
		return getPlayableCards(discard.getTopCard()).get(0);
	}
	
}
