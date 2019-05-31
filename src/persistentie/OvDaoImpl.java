package persistentie;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ovHibernate.OVchipkaart;

public class OvDaoImpl extends HibernateConnection implements OvDao {
	
	// Find All method
	public void listOv() {
		  Session session = getFactory().openSession();
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
	
	public OVchipkaart findOvById(int id) {
		  Session session = getFactory().openSession();
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
	
	 public Integer addOv(int id, double sal, int klasse, Date geldigtot, int reizigerid) {
		  Session session = getFactory().openSession();
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
	 
	 
	 public void updateOv(int id, OVchipkaart updatedOv) {
		  Session session = getFactory().openSession();
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
	 
	 public void deleteOv(int id) {
		  Session session = getFactory().openSession();
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
	 
	 

}
