package uno;

import java.awt.Color;

public class UnoCard {

	
	
	private int rankInt;
	private int colorInt;
	private int tempColorInt;
	
	public UnoCard(int r, int c) {
		rankInt = r;
		colorInt = c;
	}
	
	public int getTempColorInt() {
		return tempColorInt;
	}

	public void setTempColorInt(int tempColorInt) {
		this.tempColorInt = tempColorInt;
	}

	public String getRankAsString() {
		if(rankInt <= 9) {
			return Integer.toString(rankInt);
		}
		else {
			switch(rankInt) {
				case 10: return "Skip";
				case 11: return "Reverse";
				case 12: return "Draw2";
				case 13: return "Wild";
				case 14: return "Wild+4";
				default: return "Error";
			}
		}
	}
	
	public String getColorAsString() {
		switch(colorInt) {
		case 0: return "Red";
		case 1: return "Yellow";
		case 2: return "Blue";
		case 3: return "Green";
		case 4: return "Black";
		default: return "Error";
		}
	}
	
	public int getRankInt() {
		return rankInt;
	}

	
	public int getColorInt() {
		return colorInt;
	}
	
	public Color getColorAsColor() {
		switch (colorInt) {
		case 0: return Color.red;
		case 1: return Color.yellow;
		case 2: return Color.blue;
		case 3: return Color.green;
		case 4: return Color.gray;
		default : return Color.pink;
		}
	}
	
	public void printRankAndColor() {
		System.out.println(getRankAsString()+""+getColorAsString());
	}
	
	
	
	
}
