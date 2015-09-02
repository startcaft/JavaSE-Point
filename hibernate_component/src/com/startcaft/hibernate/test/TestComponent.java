package com.startcaft.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.Contact;
import com.startcaft.hibernate.entity.Employee;
import com.startcaft.hibernate.util.HibernateUtil;

public class TestComponent {

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
	public void insertTest(){
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Employee emp = new Employee();
		emp.setName("startcaft");
		
		Contact contact = new Contact();
		contact.setAddress("address_1");
		contact.setEmail("email_1");
		contact.setZipCode("zipcode_1");
		
		emp.setContact(contact);
		
		session.save(emp);
		session.getTransaction().commit();
	}

}
