import java.util.Map;
import java.util.TreeMap;

public class Scoreboard {

	private Map<String, String> scoreboard;
	private String win;
	private String lose;
	private String sep;
	
	public Scoreboard(String winIndicator, String loseIndicator, String separator) {
		scoreboard = new TreeMap<String, String>();
		win = winIndicator;
		lose = loseIndicator;
		sep = separator;
	}

	public void update(String player, boolean winner) {
		// Your code below here

	}
	
	@Override
	public String toString() {
		String s = "";
		// Your code below here

		// Your code above here
		return s;
	}
}
