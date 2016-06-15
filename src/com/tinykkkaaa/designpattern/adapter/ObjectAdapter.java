package com.tinykkkaaa.designpattern.adapter;

public class ObjectAdapter extends BaseUserTarget {
	
	private AnimalTarget animalTarget;

	public ObjectAdapter(String username, String animalname) {
		super(username);
		this.animalTarget = new AnimalTarget(animalname);
	}
	
	public void printAnimal(){
		this.animalTarget.printAnimal();
	}
	
	public void printAll(){
		System.out.println("---全部打印---");
		printUser();
		this.animalTarget.printAnimal();
	}
}
