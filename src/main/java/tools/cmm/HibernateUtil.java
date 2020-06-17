package tools.cmm;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://hun-a.github.io/2017/11/17/hibernate_setting/
 */
public class HibernateUtil {
	
	static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
	
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
		logger.debug("getSessionFactory() sessionFactory={}", sessionFactory);
		return sessionFactory;
	}
}
