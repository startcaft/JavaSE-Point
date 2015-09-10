package com.startcaft.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.News;
import com.startcaft.hibernate.util.HibernateUtil;

public class TestSessionCache {
	
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
	
	@Test
	public void testSessionCache(){
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		//发送一条select语句
		News news1 = (News) session.get(News.class, 1);
		System.out.println(news1);
		
		//不会发送select语句，因为在同一个session实例中
		News news2 = (News) session.get(News.class, 1);
		System.out.println(news2);
		
		transaction.commit();
		session.close();
	}
	
	@Test
	public void testFlush(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		//发送一条select语句
		News news = (News) session.get(News.class, 1);
		
		news.setAuthor("SUN");
		
		//显示调用session.flush(),仅会发送sql语句，但不更改数据，因为没有提交事务。
		//session.flush();
		//System.out.println("flush");
		
		transaction.commit();//这里flush，发送update语句
		session.close();
	}
	
	@Test
	public void testSessionRefresh(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		News news = (News) session.get(News.class, 1);//发送一条select语句
		System.out.println(news);
		
		session.refresh(news);//为了获取最新数据，又发送了一条select语句
		System.out.println(news);
		
		transaction.commit();
		session.close();
	}
}
