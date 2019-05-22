package com.panda.util;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;




public class HibernateSessionFactory {
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();//创建局部线程对象
	private static SessionFactory sessionFactory;
	private static Configuration config = new Configuration();
	private static ServiceRegistry serviceRegistry;
	static{
		try {
			config.configure();
			 serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
			sessionFactory = config.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}
	
	public static Session getSession() throws HibernateException{
		Session session = (Session)threadLocal.get();
		
		if(session == null ||!session.isOpen()){
			if(sessionFactory == null){
				rebuildSessionFactory();
			}
			session = (sessionFactory != null)?sessionFactory.openSession():null;
			threadLocal.set(session);
		}
		return session;
	}

	private static void rebuildSessionFactory() {
		try {
			config.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
			sessionFactory = config.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	public static Configuration getConfig() {
		return config;
	}
}
