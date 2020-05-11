import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class GameManager {

	public static final Integer OPT_UNKNOWN = 0;
	public static final Integer OPT_QUIT = -1;
	public static final Integer OPT_STATUS = -2;
	public static final Integer OPT_HIGH = -3;
	public static final Integer OPT_LOW = -4;
	public static final Integer OPT_REVERSE = -5;
	public static final String POINT = "*";
	public static final String FAIL = "-";
	public static final String SEP = "";
	
	public static Scanner in;
	public static Piles piles; // models the deck of cards and the discard pile
	public static Rotation rotation; // models the order in which players play
	public static Scoreboard scoreboard; // records plays made by the players
	
	public static void main(String[] args) {
		setup();
		play();				
	}
	
	public static void setup() {
		in = new Scanner(System.in); 
		try {
			piles = getPiles();			
		}
		catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
			System.err.println("Exiting...");
			System.exit(0);
		}
		rotation = getRotation();		
		scoreboard = new Scoreboard(POINT, FAIL, SEP);
	}
	
	public static Piles getPiles() {
		int min = 1; 
		int max = 3;
		int rep = 2;

//		// Uncomment for a standard deck of playing cards
//		min = 1;  // ace always low
//		max = 13; // J==11, Q==12, K==13
//		rep = 4; 

//		// Uncomment for interactive play
//		System.out.print("  Min card value: ");
//		min = Integer.parseInt(in.nextLine());
//		System.out.print("  Max card value: ");
//		max = Integer.parseInt(in.nextLine());
//		System.out.print("Card repetitions: ");
//		rep = Integer.parseInt(in.nextLine());

		return new Piles(min, max, rep);
	}
	
	public static Rotation getRotation() {
		Rotation r = new Rotation();
		String name = getName();
		while (!name.equals("")) {
			if (!r.add(name)) {
				System.out.println("That player name is already taken.  Use a different name.");
			}
			name = getName();
		}
		return r;
	}
	
	public static String getName() {
		System.out.print("Name: ");
		return in.nextLine();
	}
	
	public static void play() {
		int option = getOption(rotation.up(), piles.up());
		while (option != OPT_QUIT) {
			if (option == OPT_STATUS) {
				System.out.println(scoreboard);
			}
			else if (option == OPT_REVERSE) {
				rotation.reverse();
			}
			else if (option != OPT_UNKNOWN) {
				int upCard = piles.up();
				int drawnCard = piles.draw();

				boolean won = isWinner(option, drawnCard, upCard);
				scoreboard.update(rotation.up(), won);
				System.out.println(won ? "WINS :-)" : "LOSE :-(");
				rotation.rotate();
			}
			option = getOption(rotation.up(), piles.up());
		}
		System.out.println("\n\nGAME OVER");
		System.out.println(scoreboard);
	}
	
	public static int getOption(String player, int card) {
		System.out.println("Up Card: " + card);
		System.out.println("Options: (h)igh (l)ow (r)everse (s)coreboard (q)uit");
		System.out.print(player + "'s Choice: ");
		String option = in.nextLine();
		
		if (option.equals("h")) return OPT_HIGH;
		if (option.equals("l")) return OPT_LOW;
		if (option.equals("r")) return OPT_REVERSE;
		if (option.equals("s")) return OPT_STATUS;
		if (option.equals("q")) return OPT_QUIT;
		return OPT_UNKNOWN;
	}
	
	public static boolean isWinner(int option, int drawnCard, int upCard) {
		return (option == OPT_HIGH && drawnCard > upCard) || (option == OPT_LOW && drawnCard < upCard);		
	}	
}
