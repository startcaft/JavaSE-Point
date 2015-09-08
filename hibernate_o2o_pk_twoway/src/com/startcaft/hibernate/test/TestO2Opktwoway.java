package com.startcaft.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.Husband;
import com.startcaft.hibernate.entity.Wife;
import com.startcaft.hibernate.util.HibernateUtil;

public class TestO2Opktwoway {
	
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
	public void saveTest(){
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Husband hus = new Husband();
		hus.setName("h");
		
		Wife wife = new Wife();
		wife.setName("w");
		
		
		hus.setWife(wife);
		wife.setHusband(hus);
		
		session.save(hus);
		session.getTransaction().commit();
	}
}
