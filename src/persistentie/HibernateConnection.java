package persistentie;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ovHibernate.OVchipkaart;
import reizigerHibernate.Reiziger;

public class HibernateConnection {
	  private static SessionFactory factory;
	  
	  public static void main(String[] args) throws SQLException, ParseException {
	      try {
	        setFactory(new Configuration().configure().buildSessionFactory());
	      } catch (Throwable ex) {
	        System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex);
	      }
	      
	      OvDao ovDao = new OvDaoImpl();
	      ReizigerDao rDao = new ReizigerDaoImpl();
	      
	      Reiziger updatedReiziger = new Reiziger(10, "HH", "Hibernate", "Update", Date.valueOf("2002-09-20"));
	      OVchipkaart updatedOv = new OVchipkaart(83928, 3.10, 2, Date.valueOf("2021-09-21"), 3);

	      
	      ovDao.listOv();
	      ovDao.findOvById(79625);
	      ovDao.addOv(83928, 2.80, 1, Date.valueOf("2020-09-21"), 3);
	      ovDao.updateOv(83928, updatedOv);
	      ovDao.deleteOv(83928);
	      
	      rDao.listReizigers();
	      rDao.findReizigerById(2);
	      rDao.addReiziger(9, "H", "de", "Hibernate", Date.valueOf("2002-09-17"));
	      rDao.updateReiziger(9, updatedReiziger);
	      rDao.deleteReiziger(2);
	   // één op veel relatie, hij werkt! Ik maak geen gebruik van annotaties, zie XML Reiziger.hbm.xml voor de <one-to-many> tag.
	   // Bij iedere reiziger worden de bijbehorende OV-chipkaarten meegegeven.
	      
	      System.out.println("success!");    
	  }

	public static SessionFactory getFactory() {
		return factory;
	}

	public static void setFactory(SessionFactory factory) {
		HibernateConnection.factory = factory;
	}
	  
  }
