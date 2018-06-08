package westbahn;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import westbahn.model.*;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Main {

	private static final Logger log = Logger.getLogger(Main.class);
	
	static SimpleDateFormat dateForm = new SimpleDateFormat("dd.MM.yyyy");
	static SimpleDateFormat timeForm = new SimpleDateFormat("dd.MM.yyyy mm:hh");

	private Main() {
		super();
	}

	public static void main(String[] args) {
		log.setLevel(Level.ALL);
		try {
			log.info("Starting \"Mapping Perstistent Classes and Associations\" (task1)");
			task01();
			log.info("Starting \"Working with JPA-QL and the Hibernate Criteria API\" (task2)");
			task02a();
			task02b();
			task02c();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void fillDB(EntityManager em) throws ParseException {
		// dateForm.parse("01.01.1930")
	}

	public static void task01() throws ParseException, InterruptedException {
		Configuration config = HibernateUtils.getConfig(new Class[]{
				Bahnhof.class,
				Benutzer.class,
				Einzelticket.class,
				Preisstaffelung.class,
				Reservierung.class,
				Sonderangebot.class,
				StatusInfo.class,
				Strecke.class,
				Ticket.class,
				TicketOption.class,
				ZeitkartenTyp.class,
				Zug.class
		});

		SessionFactory factory = null;
		Session session = null;

		factory = config.buildSessionFactory();
		session = factory.openSession();

		Bahnhof b = new Bahnhof();

		session.beginTransaction();


		session.save(b);


		session.getTransaction().commit();



		session.close();
		factory.close();
	}

	public static void task02a() throws ParseException {

	}

	public static void task02b() throws ParseException {

	}

	public static void task02c() throws ParseException {

	}

}
