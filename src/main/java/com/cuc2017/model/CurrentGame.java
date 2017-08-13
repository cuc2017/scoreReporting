package com.cuc2017.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class CurrentGame extends AbstractEntity {

	@ManyToOne
	private Field field;
	@ManyToOne
	private Game game;
	private boolean useGame = true;

	public CurrentGame() {
		// default constructor
	}

	public CurrentGame(Field field, Game game) {
		this.game = game;
	}

	@Override
	public String toString() {
		return getGame().toString();
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public boolean isUseGame() {
		return useGame;
	}

	public void setUseGame(boolean useGame) {
		this.useGame = useGame;
	}

}
