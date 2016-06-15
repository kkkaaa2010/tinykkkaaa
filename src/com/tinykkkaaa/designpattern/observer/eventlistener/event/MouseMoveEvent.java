package com.tinykkkaaa.designpattern.observer.eventlistener.event;

public class MouseMoveEvent extends ButtonEvent {

	private static final long serialVersionUID = 1L;
	
	private String x;
	private String y;
	
	public MouseMoveEvent(Object source, String x, String y) {
		super(source);
		this.x = x;
		this.y = y;
	}
	
	public String getX(){
		return this.x;
	}

	public String getY(){
		return this.y;
	}
}
