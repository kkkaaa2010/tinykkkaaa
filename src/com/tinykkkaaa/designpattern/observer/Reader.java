package com.tinykkkaaa.designpattern.observer;

import java.util.Observable;
import java.util.Observer;

public class Reader implements Observer {
	
	private String readerName;
	
	public Reader(String readerName){
		this.readerName = readerName;
	}
	
	//将观察者加入到被观察者对应的观察者列表中
	public void followWriter(String writerName){
		WriterManager.getInstance().getWriter(writerName).addObserver(this);
	}
	
	public void unFollowWriter(String writerName){
		WriterManager.getInstance().getWriter(writerName).deleteObserver(this);
	}

	//观察者收到通知后更新
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Writer){
			Writer writer = (Writer)o;
			System.out.println("读者("+readerName+")知道作者("+ writer.getWriterName() +")发布了新书《"+ writer.getBookName() +"》.");
		}
	}

}
