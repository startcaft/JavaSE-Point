package com.startcaft.hibernate.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.util.HibernateUtil;

public class TestC3P0 {

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
	public void testC3P0Connection(){
		
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
			
				//打印的结果是com.mchange.v2.c3p0.impl.xxxxxxxxxx
				System.out.println(connection);
			}
		});
		
		transaction.commit();
	}
}
