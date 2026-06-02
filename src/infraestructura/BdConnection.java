package infraestructura;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class BdConnection<T> {
	
	public BdConnection() {}
	
	public EntityManager connect() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
		return emf.createEntityManager();
	}
	
	public void save(T entidad, EntityManager em) {
		try {
			em.getTransaction().begin();
			em.persist(entidad);
			em.getTransaction().commit();
		}
		catch(Exception e) {
			em.getTransaction().rollback();
		}
		finally {
			em.close();
		}
	}
}
