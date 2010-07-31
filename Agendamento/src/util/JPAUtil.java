package util;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JPAUtil {
	
	private static JPAUtil jpa;
	
	public static void init(String persistenceUnitName) throws Exception {
		if (jpa != null)
			throw new Exception("JPAUtil já inicializado");
		jpa = new JPAUtil(persistenceUnitName);
	}
	
	public static JPAUtil getInstance() throws Exception {
		if (jpa == null)
			init(Config.BANCODADOS);
		return jpa;
	}

	public static void finalizar() {
		jpa.fechar();
		jpa.emf.close();
		jpa = null;
	}
	
	private EntityManagerFactory emf;
	private EntityManager em;

	private JPAUtil(String persistenceUnitName) {
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
	}
	
	public void incluir(Object obj) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
	}

	public void excluir(Object obj) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.remove(obj);
		em.getTransaction().commit();
	}
	
	public void alterar(Object obj) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public <T> T ler(Class<T> clazz, Object codigo) {
		EntityManager em = getEntityManager();
		Object obj = em.find(clazz, codigo);
		
		return (T)obj;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> listarTodos(Class<T> clazz) {
		
		EntityManager em = getEntityManager();
		Query q = em.createQuery("from " + clazz.getName());
		List<T> l = q.getResultList();
		
		return l;
	}
	
	@SuppressWarnings("rawtypes")
	public List listarNamedQuery(String namedQuery) {
		EntityManager em = getEntityManager();
		Query q = em.createNamedQuery(namedQuery);
		List l = q.getResultList();
		
		return l;
	}
	
	public EntityManager getEntityManager() {
		if (em == null)
			em = emf.createEntityManager();
		return em;
	}
	
	public void fechar() {
		if (em != null) {
			em.close();
			em = null;
		}
	}
}
