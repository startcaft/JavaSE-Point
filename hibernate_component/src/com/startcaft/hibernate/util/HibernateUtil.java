package com.startcaft.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public final class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml------Hibernate3.x
			// return new Configuration().configure().buildSessionFactory();
			// Create the SessionFactory from hibernate.cfg.xml------Hibernate4.x
			Configuration config = new Configuration().configure();
			ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(
					config.getProperties()).buildServiceRegistry();
			return config.buildSessionFactory(registry);
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
