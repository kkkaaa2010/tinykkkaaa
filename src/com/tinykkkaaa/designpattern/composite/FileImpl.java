package com.tinykkkaaa.designpattern.composite;

public class FileImpl implements IFile {
	
	private String name;
	private IFile folder;
	
	public FileImpl(String name, IFile folder){
		this.name = name;
		this.folder = folder;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void delete() {
		folder.deleteFile(this.name);
		System.out.println("删除["+ name +"]");
	}

	//文件不支持创建新文件
	@Override
	public void createNewFile(String fileName) {
		throw new UnsupportedOperationException();
	}

	//文件不支持删除文件
	@Override
	public void deleteFile(String fileName) {
		throw new UnsupportedOperationException();
	}

	//文件不支持获取下面的文件列表 
	@Override
	public IFile getFile(int index) {
		throw new UnsupportedOperationException();
	}

}
