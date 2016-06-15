package com.tinykkkaaa.designpattern.memento;

import com.tinykkkaaa.designpattern.memento.game.GameMemento;

public class Caretaker {
	private GameMemento memento;

	public GameMemento getMemento() {
		return memento;
	}

	public void setMemento(GameMemento memento) {
		this.memento = memento;
	}
}
