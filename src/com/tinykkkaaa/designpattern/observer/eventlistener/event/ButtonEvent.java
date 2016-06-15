package com.tinykkkaaa.designpattern.observer.eventlistener.event;

import java.util.EventObject;

import com.tinykkkaaa.designpattern.observer.eventlistener.Button;

public abstract class ButtonEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;

	public ButtonEvent(Object source) {
		super(source);
	}
	
	public Button getButton(){
		return (Button)super.getSource();
	}

}
