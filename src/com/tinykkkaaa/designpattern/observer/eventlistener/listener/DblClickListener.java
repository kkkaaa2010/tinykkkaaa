package com.tinykkkaaa.designpattern.observer.eventlistener.listener;

import java.util.EventListener;
import com.tinykkkaaa.designpattern.observer.eventlistener.event.DblClickEvent;

public interface DblClickListener extends EventListener {
	void dblClick(DblClickEvent event);
}
