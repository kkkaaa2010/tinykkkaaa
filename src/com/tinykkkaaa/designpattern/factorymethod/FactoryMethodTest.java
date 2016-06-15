package com.tinykkkaaa.designpattern.factorymethod;

/**
 * 工厂方法模式
 * 例子：仿JDBC加载驱动，并创建数据库连接
 * @author Administrator
 * 
定义：
工厂方法(Factory Method)模式的意义是定义一个创建产品对象的工厂接口Driver，将实际创建工作推迟到子类(MysqlDriverImpl, OracleDriverImpl)当中。
创建的产品也定义一个产品接口Connection，工厂实现类(MysqlDriverImpl, OracleDriverImpl)对应负责具体的产品(MysqlConnectionImpl, OracleConnectionImpl)创建。
核心工厂类不再负责产品的创建，这样核心类成为一个抽象工厂角色，仅负责具体工厂子类必须实现的接口，这样进一步抽象化的好处是使得工厂方法模式可以使系统在不修改具体工厂角色的情况下引进新的产品。

工厂方法模式就是提供一个抽象的工厂，一个抽象的产品，在上述当中相当于Driver（数据库连接工厂）和Connection（抽象产品），
实现的一方需要提供一个具体的工厂类（比如mysql驱动）和一个具体的产品（比如mysql数据库连接）。

好处就是，从类关系上来说，它可以让客户端与具体的工厂与产品解耦，从业务角度来说，它让客户端与具体的产品解耦。
适用的场景就是我们需要一个产品帮我们完成一项任务，但是这个产品有可能有很多品牌（像这里的mysql，oracle），为了保持我们对产品操作的一致性，我们就可能要用到工厂方法模式。

注：1. 在Class.forName加载完驱动类，开始执行静态初始化代码时，会自动新建一个Driver的对象，并调用DriverManager.registerDriver把自己注册到DriverManager中去。 
   2. DriverManager为方便管理工厂类Driver而编写
 */
public class FactoryMethodTest {
	public static void main(String[] args) {
		try {
			Class.forName("com.tinykkkaaa.designpattern.factorymethod.OracleDriverImpl");
			Class.forName("com.tinykkkaaa.designpattern.factorymethod.MysqlDriverImpl");
			Connection connection1 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL");
			Connection connection2 = DriverManager.getConnection("jdbc:mysql:thin:@127.0.0.1:1521:ORCL");
			connection1.prepareStatement("INSERT INTO TABLE");
			connection2.prepareStatement("SELECT ID FROM TABLE");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
