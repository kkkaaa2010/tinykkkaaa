package com.tinykkkaaa.designpattern.observer;

import java.util.Observable;

public class Writer extends Observable {
	private String writerName;
	private String bookName;
	
	public Writer(String writerName){
		this.writerName = writerName;
		WriterManager.getInstance().addWriter(this);
	}

	public String getWriterName() {
		return writerName;
	}
	public String getBookName() {
		return bookName;
	}
	
	public void addBook(String bookName){
		System.out.println("作者("+ writerName + ")发布了新书《"+ bookName +"》.");
		this.bookName = bookName;
		setChanged(); //设置变化标志
		notifyObservers(); //通知观察者
	}
}
