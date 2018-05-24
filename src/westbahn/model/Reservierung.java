package westbahn.model;

import westbahn.Zahlung;

import java.util.Date;

public class Reservierung {

	private long id;

	private Date datum;

	private int praemienMeilenBonus = 15;

	private int preis = 150;

	private String status;//StatusInfo

	private Zug zug;

	private Strecke strecke;

	private Benutzer benutzer;

	private Zahlung zahlung;

}
