import junit.framework.TestCase;
import org.junit.Test;
import westbahn.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestQueries  extends TestCase {

    private EntityManagerFactory factory;
    private EntityManager em;
    private SimpleDateFormat dateForm;
    private SimpleDateFormat timeForm;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.factory = Persistence.createEntityManagerFactory("westbahn");
        this.em = factory.createEntityManager();
        this.dateForm = new SimpleDateFormat("dd.MM.yyyy");
        this.timeForm = new SimpleDateFormat("dd.MM.yyyy mm:hh");
        clearDatabase();
    }

    @Test
    public void testFindAllReservierungenByEmail() throws ParseException {
        Benutzer b1 = new Benutzer(
                "Richard",
                "Wutscher",
                "rwutscher@student.tgm.ac.at",
                "rootpw",
                "06503070070"
        );

        Benutzer b2 = new Benutzer(
                "Nemanja",
                "Tesanovic",
                "ntesanovic@student.tgm.ac.at",
                "123",
                "06647654321"
        );

        //Create Linz Bahnhof
        Bahnhof bahnhofLinz = new Bahnhof(
                "Linz",
                50,
                150,
                false
        );

        //Create Salzburg Bahnhof
        Bahnhof bahnhofSalzburg = new Bahnhof(
                "Salzburg",
                50,
                200,
                true
        );
        //Create Zug
        Zug zug1 = new Zug(
                new java.sql.Date(this.dateForm.parse("09.03.2018").getTime()),
                500, 50, 10,
                bahnhofLinz,
                bahnhofSalzburg
        );

        //Create Westbahnhof
        Bahnhof bahnhofWienWestbahnhof = new Bahnhof(
                "Wien Westbahnhof",
                10,
                100,
                true
        );

        //Create Strecke Wien-Salzburg
        Strecke strecke = new Strecke(
                bahnhofWienWestbahnhof,
                bahnhofSalzburg
        );

        Zahlung z = new Maestro();

        Date d = new java.sql.Date(dateForm.parse("09.03.2018").getTime());

        Reservierung r1 = new Reservierung(d, 20, 30, StatusInfo.ONTIME, zug1, strecke, b1, z);
        Reservierung r2 = new Reservierung(d, 20, 30, StatusInfo.ONTIME, zug1, strecke, b1, z);

        Reservierung r3 = new Reservierung(d, 20, 30, StatusInfo.ONTIME, zug1, strecke, b2, z);

        //Persist Objects
        em.getTransaction().begin();

        em.persist(bahnhofSalzburg);
        em.persist(bahnhofWienWestbahnhof);
        em.persist(bahnhofLinz);
        em.persist(strecke);
        //em.persist(d);
        em.persist(zug1);
        em.persist(strecke);
        em.persist(b1);
        em.persist(b2);
        em.persist(r1);
        em.persist(r2);
        em.persist(r3);

        em.getTransaction().commit();

        //Load Ticket
        em.getTransaction().begin();
        ArrayList<Reservierung> reservierungs = (ArrayList<Reservierung>) em.createNamedQuery("Reservierung.findAllReservierungensByEmail")
                .setParameter("email", "rwutscher@student.tgm.ac.at")
                .getResultList();
        em.getTransaction().commit();

        assertEquals(2, reservierungs.size());
    }

    @Test
    public void testGetBenutzerWithMonatskarte() throws ParseException {
        //Create Westbahnhof
        Bahnhof bahnhofWienWestbahnhof = new Bahnhof(
                "Wien Westbahnhof",
                10,
                100,
                true
        );

        //Create Salzburg Bahnhof
        Bahnhof bahnhofSalzburg = new Bahnhof(
                "Salzburg",
                50,
                200,
                true
        );

        //Create Strecke Wien-Salzburg
        Strecke strecke = new Strecke(
                bahnhofWienWestbahnhof,
                bahnhofSalzburg
        );

        Date d = new java.sql.Date(dateForm.parse("09.03.2018").getTime());

        Zahlung z = new Maestro();

        Benutzer b1 = new Benutzer(
                "Richard",
                "Wutscher",
                "rwutscher@student.tgm.ac.at",
                "rootpw",
                "06503070070"
        );

        Ticket t1 = new Zeitkarte(strecke, z, d, ZeitkartenTyp.MONATSKARTE);

        b1.setTickets(new ArrayList<>(Arrays.asList(t1)));

        Benutzer b2 = new Benutzer(
                "Nemanja",
                "Tesanovic",
                "ntesanovic@student.tgm.ac.at",
                "123",
                "06647654321"
        );

        Ticket t2 = new Zeitkarte(strecke, z, d, ZeitkartenTyp.JAHRESKARTE);

        b2.setTickets(new ArrayList<>(Arrays.asList(t2)));

        //Persist Objects
        em.getTransaction().begin();

        em.persist(bahnhofSalzburg);
        em.persist(bahnhofWienWestbahnhof);
        em.persist(strecke);
        //em.persist(d);
        em.persist(t1);
        em.persist(t2);
        em.persist(b1);
        em.persist(b2);

        em.getTransaction().commit();

        //Load Ticket
        em.getTransaction().begin();
        Benutzer benutzer = (Benutzer) em.createNamedQuery("Benutzer.findBenutzerWithMonatskarte")
                .getResultList().get(0);
        em.getTransaction().commit();

        assertEquals(b1.geteMail(), benutzer.geteMail());

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void clearDatabase(){
        try{
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Bahnhof").executeUpdate();
            em.createQuery("DELETE FROM Benutzer").executeUpdate();
            em.createQuery("DELETE FROM Reservierung").executeUpdate();
            em.createQuery("DELETE FROM Sonderangebot").executeUpdate();
            em.createQuery("DELETE FROM Strecke").executeUpdate();
            em.createQuery("DELETE FROM Ticket").executeUpdate();
            em.createQuery("DELETE FROM Zug").executeUpdate();
            em.getTransaction().commit();
        }catch(Exception e){

        }
    }
}
