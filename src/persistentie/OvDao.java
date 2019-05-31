package persistentie;

import java.sql.Date;

import ovHibernate.OVchipkaart;

public interface OvDao {
	
	public void listOv(); // Find all method
	public OVchipkaart findOvById(int id);
	public Integer addOv(int id, double sal, int klasse, Date geldigtot, int reizigerid);
	public void updateOv(int id, OVchipkaart updatedOv);
	public void deleteOv(int id);
	
}

