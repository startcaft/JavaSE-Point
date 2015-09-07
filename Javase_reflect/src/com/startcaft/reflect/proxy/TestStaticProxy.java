package com.startcaft.reflect.proxy;


interface clothFactory{
	void productCloth();
}

//被代理类
class NikeClothFactory implements clothFactory {

	@Override
	public void productCloth() {
		System.out.println("Nike工厂生产了一批衣服!");
	}
}

//代理类
class ClothFactoryProxy implements clothFactory{
	
	private clothFactory cf;
	
	public ClothFactoryProxy(clothFactory cf) {
		this.cf = cf;
	}
	
	@Override
	public void productCloth() {
		System.out.println("开始代理，需要收费了!");
		cf.productCloth();
	}
}

/**
 * 静态代理类，在编译器就确定了被代理类的类型；
 * 如果有很多的类需要代理，那么就需要很多代理类，不太方便；
 */
public class TestStaticProxy {
	
	public static void main(String[] args) {
		NikeClothFactory ncf = new NikeClothFactory();
		ClothFactoryProxy proxy = new ClothFactoryProxy(ncf);
		proxy.productCloth();
	}
}
