package com.tinykkkaaa.designpattern.composite;

/**
 * 组合模式
 * @author Administrator
 *
定义：将对象组合成树形结构以表示“部分整体”的层次结构。组合模式使得用户对单个对象和组合对象的使用具有一致性。
  
组合模式下，我们给客户端提供了统一的删除操作delete()。

使用场景：
  1、如果你想表示“部分整体”的层次结构，可以使用组合模式。
  2、如果你想让客户端可以忽略复杂的层次结构，使用统一的方式去操作层次结构中的所有对象，也可以使用组合模式。
  
例子：
模拟文件操作，文件夹与文件同时实现IFile接口，让客户端对单个对象FileImpl和组合对象FolderImpl的使用具有一致性。
但是，我们的叶子节点类（FileImpl）中，有三个不支持的方法，而之所以出现这样的情况，是因为我们在IFile接口中，提供的是宽接口，这样做的目的是为了对客户端保持透明，然而相应的却带来了不安全性。
所以有时候我们为了安全性，会相应的牺牲透明性，把IFile接口中叶子节点不支持的三个行为全部删掉，由此可见，在组合模式中，安全性和透明性是互相矛盾的，这是由于叶子节点和非叶子节点行为的不一致以及需要提供一个一致的行为接口所造成的，
是不可调和的矛盾。
而使用非透明的组合模式（接口中删除3个方法，只保留共有的方法），会相应的增加客户端操作的复杂性（因为客户端必须判断对象类型，对对象进行强制转换，来使用对应的行为）。
所有建议优先考虑透明的组合模式。
 */
public class Test {
	
	public static void main(String[] args) {
		IFile root = new FolderImpl("我的电脑");
		root.createNewFile("C");
		root.createNewFile("D");
		
		IFile dFolder = root.getFile(1);
		dFolder.createNewFile("project");
		dFolder.createNewFile("娱乐");
		
		IFile projectFolder = dFolder.getFile(0);
		projectFolder.createNewFile("test1.java");
		projectFolder.createNewFile("test2.java");
		
		IFile playFolder = dFolder.getFile(1);
		playFolder.createNewFile("aa.avi");
		playFolder.createNewFile("bb.mp3");
		
		display(null, root);
		System.out.println();
		
		projectFolder.delete();
		playFolder.getFile(0).delete();
		
		
		display(null, root);
		System.out.println();
	}
	
	// 打印文件系统
	public static void display(String prefix, IFile iFile) {
		if (prefix == null) {
			prefix = "";
		}
		System.out.println(prefix + iFile.getName());
		if (iFile instanceof FolderImpl) {
			for (int i = 0;; i++) {
				try {
					if (iFile.getFile(i) != null) {
						display(prefix + "--", iFile.getFile(i));
					}
				} catch (Exception e) {
					break;
				}
			}
		}
	}
}
