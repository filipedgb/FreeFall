package game.states;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class HighscoreState implements GameState, Serializable {

	private static final long serialVersionUID = -8372755097262962101L;

	private ArrayList<ArrayList<String>> highscores;

	public HighscoreState() {
		highscores = new ArrayList<ArrayList<String>>();

		initializeArray();
	}

	public void initializeArray() {
		for(int i=0; i<10; i++)
			addHighscore("default", 0);
	}

	public ArrayList<ArrayList<String>> getHighscores() {
		return highscores;
	}

	public void setHighscores(ArrayList<ArrayList<String>> highscores) {
		this.highscores = highscores;
	}

	public void addHighscore(String name, int score) {
		ArrayList<String> h = new ArrayList<String>();
		h.add(name);
		h.add(Integer.toString(score));

		Calendar c = Calendar.getInstance(); 
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR);
		String date = Integer.toString(day) + "/" +Integer.toString(month) + "/" + Integer.toString(year); 
		h.add(date);

		int i;
		if((i = getScoreIndex(score)) < 10) {
			highscores.trimToSize();
			highscores.add(i, h);
		}
	}

	private int getScoreIndex(int score) {
		int i=0;

		for(ArrayList<String> s : highscores) {
			if(Integer.parseInt(s.get(1)) < score)
				return i;
			i++;
		}
		return i;
	}
}
