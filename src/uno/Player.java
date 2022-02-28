package uno;

import java.util.ArrayList;

public class Player {
	private String name;
	private boolean isUser;
	private ArrayList<UnoCard> hand = new ArrayList<UnoCard>();
	
	public Player(String name) {
		setName(name);
	}
	
	public Player (boolean isUser) {
		this.isUser = isUser;	
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isUser() {
		return isUser;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}

	public int getHandSize() {
		return hand.size();
	}
	
	public void clearHand() {
		hand.clear();
	}

	public void drawCards(int amount, CardStack deck, CardStack discard) {
		
		for(int i = 0; i < amount; i++) {
			if(deck.isEmpty()) {
				reShuffle(deck, discard);
				System.out.println("reshuffle----------------");
			}
		hand.add(deck.pullTopCard());
		}
	}
	
	private void reShuffle(CardStack deck, CardStack discard) {
		discard.removeTempWilds();
		while(discard.getSize() > 1) {
			deck.putCardOnTop(discard.pullBottomCard());
		}
		deck.shuffle();
	}
	
	
	public ArrayList<Integer> getPlayableCards(UnoCard card) {
		ArrayList<Integer> playableCards = new ArrayList<Integer>();
		
		//System.out.println(hand.size());
		for(int i = 0; i < hand.size(); i++) {
			//check discard wild
			if(card.getColorInt() == 4) {
				//check against temporary color
				if(hand.get(i).getColorInt() == card.getTempColorInt()) {
					playableCards.add(i);
					//continue;
				}
			//discard not wild
			}else {
				//check against color
				if(hand.get(i).getColorInt() == card.getColorInt()) {
					playableCards.add(i);
					//continue;
				}
			}
			//check against rank
			if(hand.get(i).getRankInt() == card.getRankInt()) {
				playableCards.add(i);
				//continue;
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
	
	public int pickWildColor() {
		int colorAmount[] = {0,0,0,0};
		for(UnoCard c: hand) {
			switch (c.getColorInt()) {
			case 0: colorAmount[0]++;
			case 1: colorAmount[1]++;
			case 2: colorAmount[2]++;
			case 3: colorAmount[3]++;
				default:
			}
		}
		int maxColor = 0;
		for(int i = 0; i < 3; i++) {
			if (colorAmount[i] < colorAmount[i+1]) {
				maxColor = i +1;
			}		
		}
		return maxColor;
	}
	
	public void playCard(CardStack discard) {
		int p = pickCard(discard);
		UnoCard c = hand.get(p);
		
		if(c.getColorInt() == 4) {
			c.setTempColorInt(pickWildColor());
			System.out.println("Wild color is "+c.getTempColorAsString());
		}
		if(hand.size() == 2) {
			System.out.println("UNO!");
		}
		System.out.println(name +" plays a "+c.getColorAsString()+" "+c.getRankAsString());
		discard.putCardOnTop(hand.remove(p));
		
	}
	
	public void takeTurn(CardStack deck, CardStack discard) {
		if(checkCanPlay(discard)) {
			playCard(discard);
		}else {
			drawCards(1, deck, discard);
			if(checkCanPlay(discard)) {
				playCard(discard);
			}else {
				System.out.println(name+" PASS");
			}
			
		}
		
	}
	
	
	
	
	
	
	
	
	
}
