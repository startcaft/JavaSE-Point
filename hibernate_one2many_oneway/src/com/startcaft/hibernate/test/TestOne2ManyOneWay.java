package com.startcaft.hibernate.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.Classess;
import com.startcaft.hibernate.entity.Student;
import com.startcaft.hibernate.util.HibernateUtil;

public class TestOne2ManyOneWay {
	
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
	public void testInsert(){
		
		
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Student stu1 = new Student();
		stu1.setName("zhangsan");
		session.save(stu1);//外键值为空
		
		Student stu2 = new Student();
		stu2.setName("lisi");
		session.save(stu2);//外键值为空
		
		Set<Student> stus = new HashSet<Student>();
		stus.add(stu1);
		stus.add(stu2);
		
		
		Classess clazz = new Classess();
		clazz.setName("电脑教室1");
		clazz.setStudents(stus);
		
		session.save(clazz);//Set中有多少个对象，就会多发出多少条update语句来更新many放对象的外键值
		tx.commit();
	}
	
	@Test
	public void testGet(){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Classess claz = (Classess) session.load(Classess.class, 2);//发送一条语句
		System.out.println("classess.name=" + claz.getName());
		
		Set<Student> stus = claz.getStudents();//发送一条语句查询关联的many对象集合
		for(Iterator<Student> it = stus.iterator();it.hasNext();){
			Student stu = it.next();
			System.out.println(stu.getName());
		}
		
		tx.commit();
	}
}
