package com.tinykkkaaa.designpattern.adapter;

public class AnimalTarget {
	private String animalname;
	
	public AnimalTarget(String animalname){
		this.animalname = animalname;
	}
	
	public void printAnimal(){
		System.out.println("动物信息, animalname = "+this.animalname);
	}
}
