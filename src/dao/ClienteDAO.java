package dao;

import model.Cliente;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

public class ClienteDAO {
	private Session session;



	public ClienteDAO(){
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public int countClientesTotal() {
		Criteria criteria = session.createCriteria(Cliente.class); 
		criteria.setProjection(Projections.rowCount());
		return((Long)criteria.list().get(0)).intValue();
    }


}