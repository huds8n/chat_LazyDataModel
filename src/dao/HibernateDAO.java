package dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

public class HibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {
	private Session session;
	public Class<T> entidade;

	@SuppressWarnings("unchecked")
	public HibernateDAO() {
		session = HibernateUtil.getSessionFactory().openSession();
		Type tipo = this.getClass().getGenericSuperclass();
		if (tipo instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) tipo;
			Type[] fieldArgTypes = pt.getActualTypeArguments();
			this.entidade = (Class<T>) fieldArgTypes[0];
		}
	}

	public HibernateDAO(Class<T> entidade) {
		session = HibernateUtil.getSessionFactory().openSession();
		this.entidade = entidade;
	}

	public void destroy() {
		if (session != null) {
			session.clear();
			session.close();
		}
	}

	// --------------------------------------------------------------------------------------------
	public boolean inclui(T registro) throws HibernateException,
	ConstraintViolationException {
		Transaction tx = session.beginTransaction();
		try {
			session.save(registro);
			tx.commit();
		} catch (ConstraintViolationException e) {
			if (tx != null)
				tx.rollback();
			throw new ConstraintViolationException(
					"Falha de inclusão: Objeto já existe.", null,
					"Registro duplicado.");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw new HibernateException("Falha de inclusão no BD: ", e);
		} finally {
			session.close();
		}
		return true;
	}

	public boolean exclui(T registro) throws HibernateException,
	ObjectNotFoundException {
		Transaction tx = session.beginTransaction();
		try {
			session.delete(registro);
			tx.commit();
		} catch (ObjectNotFoundException e) {
			if (tx != null)
				tx.rollback();
			throw new ObjectNotFoundException(
					"Falha de consulta: Objeto não localizado.",
					"ERRO! Objeto não localizado");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw new HibernateException("Falha de exclusão no BD: ", e);
		} finally {
			session.close();
		}
		return true;
	}

	public boolean altera(T registro) throws HibernateException,
	ObjectNotFoundException {
		Transaction tx = session.beginTransaction();
		try {
			session.update(registro);
			tx.commit();
		} catch (StaleStateException e) {
			if (tx != null)
				tx.rollback();
			throw new ObjectNotFoundException(
					"Falha de consulta: Objeto não localizado ",
					"ERRO! Objeto não localizado");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw new HibernateException("Falha de alteração no BD: ", e);
		} finally {
			session.close();
		}
		return true;
	}

	public Object consulta(ID id) throws HibernateException,
	ObjectNotFoundException {
		Object registro;
		try {
			registro = session.get(entidade, id);
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException(
					"Falha de consulta: Objeto não localizado ",
					"ERRO! Objeto não localizado");
		} catch (HibernateException e) {
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			session.close();
		}
		return registro;
	}




	


	@SuppressWarnings("unchecked")
	public List<T> listaTudo() throws HibernateException {
		List<T> listagem;
		try {
			listagem = session.createCriteria(entidade).setMaxResults(100).setFirstResult(0).list();
		} catch (HibernateException e) {
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			session.close();
		}
		return listagem;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listaAll() throws HibernateException {
		List<T> listagem;
		try {
			listagem = session.createCriteria(entidade).list();
		} catch (HibernateException e) {
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			session.close();
		}
		return listagem;
	}

	@SuppressWarnings("unchecked")
	public List<T> pagina(int inicio, int quantia) throws HibernateException {
		List<T> listagem;
		try {
			listagem = session.createCriteria(entidade).setMaxResults(quantia)
					.setFirstResult(inicio).list();
		} catch (HibernateException e) {
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			session.close();
		}
		return listagem;
	}

	@SuppressWarnings("unchecked")
	public List<T> listaLike(String nomeColuna, String texto)
			throws HibernateException {
		List<T> listagem;
		try {
			listagem = session.createCriteria(this.entidade).add(Restrictions.like(nomeColuna, texto)).setMaxResults(100).setFirstResult(0).list();
		} catch (HibernateException e) {
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			session.close();
		}
		return listagem;
	}
	

}
