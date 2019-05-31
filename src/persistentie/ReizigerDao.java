package persistentie;

import java.sql.Date;

import reizigerHibernate.Reiziger;

public interface ReizigerDao {
	
	 public void listReizigers(); // Find all method
	 public Reiziger findReizigerById(int id);
	 public Integer addReiziger(int id, String vl, String tvoegsel, String anaam, Date gbdatum);
	 public void updateReiziger(int id, Reiziger updatedReiziger);
	 public void deleteReiziger(int id);

}