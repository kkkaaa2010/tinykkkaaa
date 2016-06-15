package com.tinykkkaaa.designpattern.proxy.staticstate;

/**
 * 静态代理
 * @author Administrator
优点：
业务类只需要关注业务逻辑本身，保证了业务类的重用性。这是代理的共有优点。 
缺点： 
1）代理对象的一个接口只服务于一种类型的对象，如果要代理的方法很多，势必要为每一种方法都进行代理，静态代理在程序规模稍大时就无法胜任了。 
2）如果接口增加一个方法，除了所有实现类需要实现这个方法外，所有代理类也需要实现此方法。增加了代码维护的复杂度。 
 */
public class UserServiceProxy implements IUserService {
	
	private IUserService service;
	
	public UserServiceProxy(IUserService service){
		this.service = service;
	}

	@Override
	public void saveUser() {
		before();
		
		this.service.saveUser();
		
		after();
	}
	
	private void before(){
		System.out.println("---UserServiceProxy.before()---");
	}
	private void after(){
		System.out.println("---UserServiceProxy.after()---");
	}

}
