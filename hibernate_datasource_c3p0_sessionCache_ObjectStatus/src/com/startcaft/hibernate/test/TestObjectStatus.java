package com.startcaft.hibernate.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.News;
import com.startcaft.hibernate.util.HibernateUtil;

public class TestObjectStatus {
	
	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void initSessionFactory() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@AfterClass
	public static void free() {
		sessionFactory.close();
	}

	@Test
	public void createTable() {
		new SchemaExport(new Configuration().configure()).create(true, true);
	}
	
	/**
	 * get VS load:
	 * 
	 * 1.执行get方法：会立即加载对象，
	 *   执行load方法，若不实用该对象，则不会立即执行查询操作，而返回一个代理对象。
	 *   
	 *   get 是立即检索，而load是延迟加载。
	 *   
	 *   
	 * 2.若数据表中没有对应的记录.
	 * 	 get 返回null
	 *   load 抛出异常(因为代理对象的关系)，若不使用该对象的任何属性，则不会抛异常
	 *   
	 * 
	 * 3.load 方法可能会抛出LazyInitializationException异常:
	 *   在需要初始化代理对象之前如果已经关闭了Sesssion就会
	 * 
	 */
	@Test
	public void testGet(){
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		News news = (News)session.get(News.class, 1);//news对象被初始化了，到哪都能用
		//Assert.assertThat(news, Matchers.notNullValue());
		
		transaction.commit();
		session.close();
		
		
		System.out.println(news);//session关闭之后，还能用
	}
	@Test
	public void testLoad(){
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		News news = (News)session.load(News.class, 1);//生成的只是代理
		//System.out.println(news);不使用对象
		
		transaction.commit();
		session.close();
		
		System.out.println(news);//报错
	}
	
	/****************状态测试**********************/
	
	/**
	 * 1.save()方法
	 * ---1).使一个临时对象变为持久化对象
	 * ---2).为对象分配OID
	 * ---3).在flush缓存时，会发送一条insert语句。
	 * ---4).在save方法之前设置的id是无效的。
	 * ---5).持久化对象的ID是不能被修改的。
	 */
	@Test
	public void testSave(){
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		News news = new News();
		news.setAuthor("BB");
		news.setTitle("bb");
		news.setCreateTime(new Date());
		
		System.out.println(news);
		
		//save之前 ,主键ID是没有的
		System.out.println(news.getId());
		
		session.save(news);
		
		//save之后，主键ID
		System.out.println(news.getId());
		
		transaction.commit();
		session.close();
	}
	
	/**
	 * persist():也会执行insert操作。
	 * 和save()的区别：
	 * 在调用persist方法之前，若对象已经有id了，则不会执行insert，而会抛出异常。
	 */
	@Test
	public void testPersist(){
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		News news = new News();
		news.setAuthor("DD");
		news.setTitle("dd");
		news.setCreateTime(new Date());
		
		news.setId(200);//这里会抛出异常
		
		session.persist(news);
		
		transaction.commit();
		session.close();
	}
}
