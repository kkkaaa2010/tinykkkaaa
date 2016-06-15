package com.tinykkkaaa.designpattern.composite;

public interface IFile {
	String getName();
	void delete();
	
	void createNewFile(String fileName);
	void deleteFile(String fileName);
	IFile getFile(int index);
}
