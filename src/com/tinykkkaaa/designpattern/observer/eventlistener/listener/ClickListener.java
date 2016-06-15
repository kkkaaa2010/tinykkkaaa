package com.tinykkkaaa.designpattern.observer.eventlistener.listener;

import java.util.EventListener;

import com.tinykkkaaa.designpattern.observer.eventlistener.event.ClickEvent;

public interface ClickListener extends EventListener {
	void click(ClickEvent event);
}
