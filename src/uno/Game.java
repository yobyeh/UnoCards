package uno;


public class Game {
	
	
	
	
	
	public CardStack deck;
	public CardStack discard;
	public Player bot0;
	public Player bot1;
	public Player bot2;
	public Player user;
	
	public void play() {
		setup();
		deal();
		
	}
	
	private void setup() {
		deck = new CardStack();
		deck.populateDeck();
		discard = new CardStack();
		bot0 = new Player();
		bot1 = new Player();
		bot2 = new Player();
		user = new Player();
	}
	
	private void deal() {
		for(int i = 0; i < 7; i++) {
		bot0.drawCards(1, deck);
		bot1.drawCards(1, deck);
		bot2.drawCards(1, deck);
		user.drawCards(1, deck);
		}
		discard.putCardOnTop(deck.pullTopCard());
	}

}
