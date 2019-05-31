package hu.nl.hibernate;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ovHibernate.OVchipkaart;
import reizigerHibernate.Reiziger;


public class Main {
  private static SessionFactory factory;
  public static void main(String[] args) throws SQLException, ParseException {
      try {
        factory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) {
        System.err.println("Failed to create sessionFactory object." + ex);
        throw new ExceptionInInitializerError(ex);
      }
      
      Reiziger updatedReiziger = new Reiziger(10, "HH", "Hibernate", "Update", Date.valueOf("2002-09-20"));
      OVchipkaart updatedOv = new OVchipkaart(83928, 3.10, 2, Date.valueOf("2021-09-21"), 3);
      
      Main m = new Main();
      
//      m.addReiziger(9, "H", "de", "Hibernate", Date.valueOf("2002-09-17"));
//      m.updateReiziger(9, updatedReiziger);
//      m.deleteReiziger(9);
//      m.listReizigers();
      m.findReizigerById(2);
            
//      m.addOv(83928, 2.80, 1, Date.valueOf("2020-09-21"), 3);
//      m.updateOv(83928, updatedOv);
//      m.deleteOv(83928);
//      m.findOvById(35283);
//    	m.listOv();
//      m.findOvByReiziger(2);
      
      System.out.println("success!");    
  }
  
  public Integer addOv(int id, double sal, int klasse, Date geldigtot, int reizigerid) {
	  Session session = factory.openSession();
	  Transaction t = null;
	  Integer ovid = null;
	  
	  try {
		  t = session.beginTransaction();
		  OVchipkaart ovkaart = new OVchipkaart(id, sal, klasse, geldigtot, reizigerid);
		  ovid = (Integer) session.save(ovkaart);
		  t.commit();
	  } catch (HibernateException e) {
		  if (t != null) t.rollback();
		  e.printStackTrace();
	  } finally {
		  session.close();
	  }
	  return ovid;
  }
  
  public Integer addReiziger(int id, String vl, String tvoegsel, String anaam, Date gbdatum) {
	  Session session = factory.openSession();
	  Transaction t = null;
	  Integer reizigerid = null;
	  
	  try {
		  t = session.beginTransaction();
		  Reiziger reiziger = new Reiziger(id, vl, tvoegsel, anaam, gbdatum);
		  reizigerid = (Integer) session.save(reiziger);
		  t.commit();
	  } catch (HibernateException e) {
		  if (t != null) t.rollback();
		  e.printStackTrace();
	  } finally {
		  session.close();
	  }
	  return reizigerid;
  }
  
  public OVchipkaart findOvById(int id) {
	  Session session = factory.openSession();
	  Transaction t = null;
	  
	  OVchipkaart ov = null;
	  
	  try {
		  t = session.beginTransaction();
			  OVchipkaart ovkaart = (OVchipkaart) session.get(OVchipkaart.class, id);

			  System.out.println("Kaartnummer: " + ovkaart.getKaartnummer());
			  System.out.println("Geldig tot: " + ovkaart.getGeldig());
			  System.out.println("Klasse: " + ovkaart.getKlasse());
			  System.out.println("Saldo: " + ovkaart.getSaldo());
			  System.out.println("Reizigerid: " + ovkaart.getReizigerid() + "\n");
			  
			  ov = ovkaart;
		  t.commit();
	  } catch (HibernateException e) {
		  if(t != null) t.rollback();
		  e.printStackTrace();
	  } finally {
		  session.close();
	  }
	return ov;  
  }
  
  public Reiziger findReizigerById(int id) {
	  Session session = factory.openSession();
	  Transaction t = null;
	  
	  Reiziger reiziger = null;
	  
	  try {
		  t = session.beginTransaction();
			  Reiziger r = (Reiziger) session.get(Reiziger.class, id);
			  
			  System.out.println("Reizigerid: " + r.getReizigerid());
			  System.out.println("Voorletters: " + r.getVoorletter());
			  System.out.println("Tussenvoegsel: " + r.getTussenvoegsel());
			  System.out.println("Achternaam: " + r.getAchternaam());
			  System.out.println("Geboortedatum: " + r.getGbdatum() + "\n");
			  
			  r.setOvchipkaarten((findOvByReiziger(id)));
			  
			  reiziger = r;
		  t.commit();
	  } catch (HibernateException e) {
		  if(t != null) t.rollback();
		  e.printStackTrace();
	  } finally {
		  session.close();
	  }
	return reiziger;  
  }
  
  public List<OVchipkaart> findOvByReiziger(int id) {
	  Session session = factory.openSession();
	  Transaction t = null;
	  
	  List<OVchipkaart> ov = new ArrayList<OVchipkaart>();
	  
	  try {
		  t = session.beginTransaction();
		  List ovkaarten = session.createQuery("FROM OVchipkaart where reizigerid = " + id).list();
		  for (Iterator iterator = ovkaarten.iterator(); iterator.hasNext();) {
			  OVchipkaart ovkaart = (OVchipkaart) iterator.next();
			  System.out.println("Kaartnummer: " + ovkaart.getKaartnummer());
			  System.out.println("Geldig tot: " + ovkaart.getGeldig());
			  System.out.println("Klasse: " + ovkaart.getKlasse());
			  System.out.println("Saldo: " + ovkaart.getSaldo());
			  System.out.println("Reizigerid: " + ovkaart.getReizigerid() + "\n");
			  
		  }
		  ov = ovkaarten;
		  t.commit();
	  } catch (HibernateException e) {
		  if(t != null) t.rollback();
		  e.printStackTrace();
	  } finally {
		  session.close();
	  }
	return ov;  
  }
  
  public void listOv() {
	  Session session = factory.openSession();
	  Transaction t = null;
	  
	  try {
		  t = session.beginTransaction();
		  List ovkaarten = session.createQuery("FROM OVchipkaart").list();
		  for (Iterator iterator = ovkaarten.iterator(); iterator.hasNext();) {
			  OVchipkaart ovkaart = (OVchipkaart) iterator.next();
			  System.out.println("Kaartnummer: " + ovkaart.getKaartnummer());
			  System.out.println("Geldig tot: " + ovkaart.getGeldig());
			  System.out.println("Klasse: " + ovkaart.getKlasse());
			  System.out.println("Saldo: " + ovkaart.getSaldo());
			  System.out.println("Reizigerid: " + ovkaart.getReizigerid() + "\n");

		  }
		  t.commit();
	  } catch (HibernateException e) {
		  if(t != null) t.rollback();
		  e.printStackTrace();
	  } finally {
		  session.close();
	  }  
  }
  
  public void listReizigers() {
	  Session session = factory.openSession();
	  Transaction t = null;
	  
	  try {
		  t = session.beginTransaction();
		  List reizigers = session.createQuery("FROM Reiziger").list();
		  for (Iterator iterator = reizigers.iterator(); iterator.hasNext();) {
			  Reiziger reiziger = (Reiziger) iterator.next();
			  
			  System.out.println("Reizigerid: " + reiziger.getReizigerid());
			  System.out.println("Voorletters: " + reiziger.getVoorletter());
			  System.out.println("Tussenvoegsel: " + reiziger.getTussenvoegsel());
			  System.out.println("Achternaam: " + reiziger.getAchternaam());
			  System.out.println("Geboortedatum: " + reiziger.getGbdatum() + "\n");
			  
			  reiziger.setOvchipkaarten((findOvByReiziger(reiziger.getReizigerid())));

		  }
		  t.commit();
	  } catch (HibernateException e) {
		  if(t != null) t.rollback();
		  e.printStackTrace();
	  } finally {
		  session.close();
	  }  
  }
  
  public void updateOv(int id, OVchipkaart updatedOv) {
	  Session session = factory.openSession();
	  Transaction t = null;
	  
	  try {
		  t = session.beginTransaction();
		  OVchipkaart ovkaart = (OVchipkaart) session.get(OVchipkaart.class, id);
		  ovkaart.setSaldo(updatedOv.getSaldo());
		  ovkaart.setGeldig(updatedOv.getGeldig());
		  ovkaart.setKlasse(updatedOv.getKlasse());
		  ovkaart.setReizigerid(updatedOv.getReizigerid());
		  
		  session.update(ovkaart);
		  t.commit();
	  } catch (HibernateException e) {
		  if (t != null) t.rollback();
		  e.printStackTrace();
	  } finally {
		  session.close();
	  }
  }
  
  public void updateReiziger(int id, Reiziger updatedReiziger) {
	  Session session = factory.openSession();
	  Transaction t = null;
	  
	  try {
		  t = session.beginTransaction();
		  Reiziger reiziger = (Reiziger) session.get(Reiziger.class, id);
		  reiziger.setVoorletter(updatedReiziger.getVoorletter());
		  reiziger.setTussenvoegsel(updatedReiziger.getTussenvoegsel());
		  reiziger.setAchternaam(updatedReiziger.getAchternaam());
		  reiziger.setGbdatum(updatedReiziger.getGbdatum());
		  
		  session.update(reiziger);
		  t.commit();
	  } catch (HibernateException e) {
		  if (t != null) t.rollback();
		  e.printStackTrace();
	  } finally {
		  session.close();
	  }
  }
  
  public void deleteOv(int id) {
	  Session session = factory.openSession();
	  Transaction t = null;
	  
	  try {
		  t = session.beginTransaction();
		  OVchipkaart ovkaart = (OVchipkaart) session.get(OVchipkaart.class, id);
		  
		  session.delete(ovkaart);
		  t.commit();	  
	  } catch (HibernateException e) {
		  if (t != null) t.rollback();
		  e.printStackTrace();
	  } finally {
		  session.close();
	  }
	    
  }
  
  public void deleteReiziger(int id) {
	  Session session = factory.openSession();
	  Transaction t = null;
	  
	  try {
		  t = session.beginTransaction();
		  Reiziger reiziger = (Reiziger) session.get(Reiziger.class, id);
		  
		  session.delete(reiziger);
		  t.commit();	  
	  } catch (HibernateException e) {
		  if (t != null) t.rollback();
		  e.printStackTrace();
	  } finally {
		  session.close();
	  }
	    
  }
  
  
  
  
}
