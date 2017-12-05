package dao;

import model.Product;
import java.util.List;
import util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductDAO {
	public void insert(Product product) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(product);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();	
			}
			
		} finally {
			session.close();
		}
	}
	

	public void update(Product product) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(product);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();	
			}
			
		} finally {
			session.close();
		}
	}

	public Product find(Long id){
		Session session = HibernateUtil.getSessionFactory().openSession();

		Product product;
		try {
			product = (Product) session.get(Product.class, id);
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}
		
		return product;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> fetchAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Product> products = null;

		try {
			Query query = session.createQuery("from Product p");
			products = query.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return products;
	}
}
