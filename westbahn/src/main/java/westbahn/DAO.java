package westbahn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.hibernate.jpa.AvailableSettings.PERSISTENCE_UNIT_NAME;

public class DAO <T>{
    private static EntityManagerFactory factory;

    public DAO(){
        this.factory = Persistence.createEntityManagerFactory("westbahn");
    }

    public void save(T object){
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(object);
        // do what ever you need
        em.getTransaction().commit();
        em.close();
    }

    public void close(){
        this.factory.close();
    }

    // add more methods to the dao.
}