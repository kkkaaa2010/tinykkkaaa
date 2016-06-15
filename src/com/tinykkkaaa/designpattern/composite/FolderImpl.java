package com.tinykkkaaa.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

public class FolderImpl implements IFile {
	
	private String name;
	private IFile folder;
	private List<IFile> files;
	
	public FolderImpl(String name){
		this(name, null);
	}
	
	public FolderImpl(String name, IFile folder){
		this.name = name;
		this.folder = folder;
		this.files = new ArrayList<IFile>();
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public void delete() {
		List<IFile> copyList = new ArrayList<IFile>(this.files);
		System.out.println("删除子文件...");
		for(IFile file : copyList){
			file.delete();
		}
		System.out.println("删除子文件结束...");
		if(this.folder != null){
			folder.deleteFile(this.name);
		}
		System.out.println("删除["+ this.name +"]");
	}

	@Override
	public void createNewFile(String fileName) {
		if(fileName.contains(".")){
			this.files.add(new FileImpl(fileName, this));
		}else{
			this.files.add(new FolderImpl(fileName, this));
		}

	}

	@Override
	public void deleteFile(String fileName) {
		for(IFile file : files){
			if(file.getName().equals(fileName)){
				this.files.remove(file);
				break;
			}
		}

	}

	@Override
	public IFile getFile(int index) {
		return this.files.get(index);
	}

}
