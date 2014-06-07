package game.states;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Esta classe representa o highscore do jogo, contem um array de arrays com
 * nome, score e data de um determinado score
 * 
 * @author André Pires, Filipe Gama
 * @see GameState
 */
public class HighscoreState implements Serializable {

	private static final long serialVersionUID = -8372755097262962101L;

	private ArrayList<ArrayList<String>> highscores;

	public HighscoreState() {
		highscores = new ArrayList<ArrayList<String>>();
		initializeArray();
	}

	/**
	 * Inicializa o array de highscores com a data de criacao, scores todos a
	 * zero e nomes igual a "default"
	 */
	public void initializeArray() {
		for (int i = 0; i < 10; i++)
			addHighscore("default", 0);
	}

	public ArrayList<ArrayList<String>> getHighscores() {
		return highscores;
	}

	public void setHighscores(ArrayList<ArrayList<String>> highscores) {
		this.highscores = highscores;
	}

	/**
	 * Adiciona um score ao highscore, se for o caso, sendo calculada a data
	 * atual
	 * 
	 * @param name
	 *            nome do jogador
	 * @param score
	 *            score a guardar
	 */
	public void addHighscore(String name, int score) {
		ArrayList<String> h = new ArrayList<String>();
		h.add(name);
		h.add(Integer.toString(score));

		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR);
		String date = Integer.toString(day) + "/" + Integer.toString(month)
				+ "/" + Integer.toString(year);
		h.add(date);

		int i;
		if ((i = getScoreIndex(score)) < 10) {
			highscores.trimToSize();
			highscores.add(i, h);
			while (highscores.size() > 10)
				highscores.remove(10);
		}
	}

	/**
	 * Retorna o indice pertencente ao @param, serve, tambem, para verificar se
	 * o score é um highscore, isto é, se o indice for inferior a 10
	 * 
	 * @param score
	 *            score a verificar
	 * @return o indice
	 */
	public int getScoreIndex(int score) {
		int i = 0;

		for (ArrayList<String> s : highscores) {
			if (Integer.parseInt(s.get(1)) < score)
				break;
			i++;
		}
		return i;
	}

	public int numHighscores() {
		return highscores.size();
	}
}
