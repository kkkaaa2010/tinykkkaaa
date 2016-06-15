package com.tinykkkaaa.designpattern.observer.eventlistener.listener;

import java.util.EventListener;

import com.tinykkkaaa.designpattern.observer.eventlistener.event.MouseMoveEvent;

public interface MouseMoveListener extends EventListener {
	void mouseMove(MouseMoveEvent event);
}
