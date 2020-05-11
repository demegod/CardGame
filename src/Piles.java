import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Piles {

	private Stack<Integer> cardPile;
	private Stack<Integer> discardPile;
	
	private int min;
	private int max;
	private int rep;
	
	private final int NO_REMOVAL = 0;
	
	public Piles(int minValue, int maxValue, int numRepeats) {
		min = minValue;
		max = maxValue;
		rep = numRepeats;
		setup(NO_REMOVAL);
	}
	
	private void setup(int remCard) {
		validate(); // ensures that we can make a deck

		// makeDeck returns an ArrayList of cards
		// if remCard is NO_REMOVAL, then the ArrayList is a full deck
		// otherwise the list has had one copy card removed (remCard)
		// For example, for a U.S. deck of playing cards:
		//   if remCard is NO_REMOVAL, then returns the usual 52-card deck
		//   if remCard is 2, then returns a 51-card deck with one deuce removed		
		ArrayList<Integer> cards = makeDeck(remCard);

		cardPile = new Stack<Integer>();
		discardPile = new Stack<Integer>();

		// Your code below here

	}
	
	private void validate() {
		String msg = "";
		if (min <= NO_REMOVAL)   msg += "\nMin card value must be " + (NO_REMOVAL + 1) + " or more.";
		if (min > max) msg += "\nMin card value must be smaller than max card value.";
		if (rep < 1)   msg += "\nNumber of repeated cards must be 1 or more.";

		if (msg.length() > 0) {
			throw new IllegalArgumentException(msg);
		}
	}
	
	private ArrayList<Integer> makeDeck(int remCard) {
		ArrayList<Integer> cards = new ArrayList<Integer>((max - min + 1) * rep);
		for (int card = min; card <= max; card++) {
			int numCards = (remCard == card) ? rep-1 : rep;
			for (int i = 0; i < numCards; i++) {
				cards.add(card);
			}
		}
		Collections.shuffle(cards);
		
		return cards;
	}
	
	public int up() {
		return NO_REMOVAL; // delete this line
		// Your code below here

	}
	
	public int draw() {
		return NO_REMOVAL; // delete this line
		// Your code below here

	}
}
