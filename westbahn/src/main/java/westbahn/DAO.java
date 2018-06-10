package westbahn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.hibernate.jpa.AvailableSettings.PERSISTENCE_UNIT_NAME;

public class DAO <T>{

    private static EntityManagerFactory factory;
    private EntityManager em;

    public DAO(){
        this.factory = Persistence.createEntityManagerFactory("westbahn");
        em = factory.createEntityManager();
    }

    public void save(T object){

        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
        em.close();
    }

    public void close(){
        this.factory.close();
    }

    // add more methods to the dao.
}