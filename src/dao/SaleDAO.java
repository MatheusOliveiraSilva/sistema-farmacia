package dao;

import model.Sale;
import java.util.List;
import util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SaleDAO {
	public void insert(Sale sale) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(sale);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();	
			}
			
		} finally {
			session.close();
		}
	}
	

	public void update(Sale sale) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(sale);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();	
			}
			
		} finally {
			session.close();
		}
	}

	public Sale find(Long id){
		Session session = HibernateUtil.getSessionFactory().openSession();

		Sale sale;
		try {
			sale = (Sale) session.get(Sale.class, id);
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}
		
		return sale;
	}
	
	@SuppressWarnings("unchecked")
	public List<Sale> fetchAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Sale> sales = null;

		try {
			Query query = session.createQuery("from Sale p");
			sales = query.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return sales;
	}
}
