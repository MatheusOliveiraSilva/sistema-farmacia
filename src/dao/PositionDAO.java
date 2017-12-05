package dao;

import java.util.List;
import model.Position;
import util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PositionDAO {
	public void insert(Position position) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(position);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();	
			}
			
		} finally {
			session.close();
		}
	}
	
	public void update(Position position) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(position);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();	
			}
			
		} finally {
			session.close();
		}
	}

	public Position find(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();

		Position position;
		try {
			position = (Position) session.get(Position.class, id);
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}
		
		return position;
	}

	@SuppressWarnings("unchecked")
	public List<Position> fetchAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Position> positions = null;

		try {
			Query query = session.createQuery("from Position p");
			positions = query.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return positions;
	}
}
