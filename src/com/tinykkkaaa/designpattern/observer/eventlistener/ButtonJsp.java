package com.tinykkkaaa.designpattern.observer.eventlistener;

import com.tinykkkaaa.designpattern.observer.eventlistener.event.ClickEvent;
import com.tinykkkaaa.designpattern.observer.eventlistener.event.DblClickEvent;
import com.tinykkkaaa.designpattern.observer.eventlistener.event.MouseMoveEvent;
import com.tinykkkaaa.designpattern.observer.eventlistener.listener.ClickListener;
import com.tinykkkaaa.designpattern.observer.eventlistener.listener.DblClickListener;
import com.tinykkkaaa.designpattern.observer.eventlistener.listener.MouseMoveListener;

public class ButtonJsp {
	private Button button;
	
	public ButtonJsp(){
		this.button = new Button();
		
		this.button.setId("buttonid");
		this.button.setValue("提交");
		this.button.setOnclick(new ClickListener() {
			@Override
			public void click(ClickEvent event) {
				System.out.println("点击按钮事件触发...");
			}
		});
		
		this.button.setOnDblClick(new DblClickListener() {
			
			@Override
			public void dblClick(DblClickEvent event) {
				System.out.println("双击按钮事件触发 -> id=" + event.getButton().getId());
			}
		});
		
		this.button.setOnMouseMove(new MouseMoveListener() {
			
			@Override
			public void mouseMove(MouseMoveEvent event) {
				// TODO Auto-generated method stub
				System.out.println("移动按钮事件触发 -> x=" + event.getX() + ", y=" + event.getY());
			}
		});
	}
	
	public Button getButton(){
		return this.button;
	}
}
