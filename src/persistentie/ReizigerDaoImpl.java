package persistentie;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import reizigerHibernate.Reiziger;

public class ReizigerDaoImpl extends HibernateConnection implements ReizigerDao {

	// Find All method
	public void listReizigers() {
		  Session session = getFactory().openSession();
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
				  System.out.println("Geboortedatum: " + reiziger.getGbdatum());
				  System.out.println("OVkaarten: " + reiziger.getOvchipkaarten().toString() + "\n	");
				  // één op veel relatie, hij werkt! Ik maak geen gebruik van annotaties, zie XML Reiziger.hbm.xml voor de <one-to-many> tag.
				  // Bij iedere reiziger worden de bijbehorende OV-chipkaarten meegegeven.
				  
			  }
			  t.commit();
		  } catch (HibernateException e) {
			  if(t != null) t.rollback();
			  e.printStackTrace();
		  } finally {
			  session.close();
		  }  
	  }
	
	public Reiziger findReizigerById(int id) {
		  Session session = getFactory().openSession();
		  Transaction t = null;
		  
		  Reiziger reiziger = null;
		  
		  try {
			  t = session.beginTransaction();
				  Reiziger r = (Reiziger) session.get(Reiziger.class, id);
				  
				  System.out.println("Reizigerid: " + r.getReizigerid());
				  System.out.println("Voorletters: " + r.getVoorletter());
				  System.out.println("Tussenvoegsel: " + r.getTussenvoegsel());
				  System.out.println("Achternaam: " + r.getAchternaam());
				  System.out.println("Geboortedatum: " + r.getGbdatum());
				  System.out.println("OVkaarten: " + r.getOvchipkaarten().toString() + "\n	");
				  
				  
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
	
	 public Integer addReiziger(int id, String vl, String tvoegsel, String anaam, Date gbdatum) {
		  Session session = getFactory().openSession();
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
	 
	 public void updateReiziger(int id, Reiziger updatedReiziger) {
		  Session session = getFactory().openSession();
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
	 
	 public void deleteReiziger(int id) {
		  Session session = getFactory().openSession();
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
