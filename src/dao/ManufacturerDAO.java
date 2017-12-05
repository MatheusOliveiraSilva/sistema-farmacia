package dao;

import java.util.List;
import model.Manufacturer;
import util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManufacturerDAO {
	public void insert(Manufacturer manufacturer) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(manufacturer);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();	
			}
			
		} finally {
			session.close();
		}
	}
	
	public void update(Manufacturer manufacturer) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(manufacturer);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();	
			}
			
		} finally {
			session.close();
		}
	}

	public Manufacturer find(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();

		Manufacturer manufacturer;
		
		try {
			manufacturer = (Manufacturer) session.get(Manufacturer.class, id);
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return manufacturer;
	}
	
	@SuppressWarnings("unchecked")
	public List<Manufacturer> fetchAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Manufacturer> manufacturers = null;

		try {
			Query query = session.createQuery("from Manufacturer p");
			manufacturers = query.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return manufacturers;
	}
}
