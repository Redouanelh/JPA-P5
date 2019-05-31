package reizigerHibernate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ovHibernate.OVchipkaart;

public class Reiziger {
	private int reizigerid;
	private String voorletter;
	private String tussenvoegsel;
	private String achternaam;
	private Date gbdatum;

	private List<OVchipkaart> ovchipkaarten = new ArrayList<OVchipkaart>();
	
	public Reiziger() {
	}
	
	
	public Reiziger(int reizigerid, String voorletter, String tussenvoegsel, String achternaam, Date gbdatum) {
		super();
		this.reizigerid = reizigerid;
		this.voorletter = voorletter;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
		this.gbdatum = gbdatum;
	}

	public int getReizigerid() {
		return reizigerid;
	}


	public void setReizigerid(int reizigerid) {
		this.reizigerid = reizigerid;
	}


	public Date getGbdatum() {
		return gbdatum;
	}


	public void setGbdatum(Date gbdatum) {
		this.gbdatum = gbdatum;
	}

	public List<OVchipkaart> getOvchipkaarten() {
		return ovchipkaarten;
	}

	public void setOvchipkaarten(List<OVchipkaart> ovchipkaarten) {
		this.ovchipkaarten = ovchipkaarten;
	}


	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
	}
	
	public void setVoorletter(String vl) {
		voorletter = vl;
	}
	
	public void setTussenVoegsel(String tv) {
		tussenvoegsel = tv;
	}
	
	public void setAchternaam(String an) {
		achternaam = an;
	}
	
	public String getTussenvoegsel() {
		return tussenvoegsel;
	}
	
	public String getAchternaam() {
		return achternaam;
	}
	
	public String getVoorletter() {
		return voorletter;
	}
	
	public void saveOv(OVchipkaart ov) {
		ovchipkaarten.add(ov);
	}
	
	
}
