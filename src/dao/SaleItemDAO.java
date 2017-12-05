package dao;

import java.util.List;
import model.SaleItem;
import util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SaleItemDAO {
	public void insert(SaleItem saleItem) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(saleItem);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();	
			}
			
		} finally {
			session.close();
		}
	}
	

	public void update(SaleItem saleItem) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(saleItem);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();	
			}
			
		} finally {
			session.close();
		}
	}

	public SaleItem find(Long id){
		Session session = HibernateUtil.getSessionFactory().openSession();

		SaleItem saleItem;
		try {
			saleItem = (SaleItem) session.get(SaleItem.class, id);
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}
		
		return saleItem;
	}
	
	@SuppressWarnings("unchecked")
	public List<SaleItem> fetchAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<SaleItem> saleItems = null;

		try {
			Query query = session.createQuery("from SaleItem p");
			saleItems = query.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return saleItems;
	}
}
