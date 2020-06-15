package tools.cmm;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * https://hun-a.github.io/2017/11/17/hibernate_setting/
 */
public class HibernateUtil {
	
	private static SessionFactory sessionFactory;

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			return configuration.buildSessionFactory();
		}
		catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) sessionFactory = buildSessionFactory();
		System.out.println("getSessionFactory() sessionFactory="+sessionFactory);
		return sessionFactory;
	}
}
