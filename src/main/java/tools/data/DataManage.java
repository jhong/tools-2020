package tools.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import tools.cmm.HibernateUtil;

public class DataManage {

	/**
	 * DATA 단건 조회
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DataInfo getData(int id) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		Query query = session.createQuery("FROM ZZ_DATA where id = :id");
		query.setParameter("id", id);
		
		DataInfo data = (DataInfo)query.uniqueResult();
		System.out.println(data);
		
		session.close();
		return data;
	}
	
	/**
	 * DATA 등록
	 * @param data
	 * @throws Exception
	 */
	public void insertData(DataInfo data) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.save(data);
		
		session.getTransaction().commit();
		session.close();
	}
	
}
