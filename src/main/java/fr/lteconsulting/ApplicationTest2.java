package fr.lteconsulting;

import java.sql.SQLException;
import java.util.List;

import fr.lteconsulting.dao.ChansonDAO;
import fr.lteconsulting.dao.MySQLDatabaseConnection;
import fr.lteconsulting.modele.Chanson;


public class ApplicationTest2 {

	public static void main(String[] args) throws SQLException {
		MySQLDatabaseConnection databaseConnection= new MySQLDatabaseConnection();
		ChansonDAO chansonDao = new ChansonDAO( databaseConnection );
		//chercherEtAfficherChanson(chansonDao, 10);
		chercherEtAfficherChansonById(chansonDao, "4");
	}
	
	
	public static void chercherEtAfficherChanson(ChansonDAO chansonDao, int id){
		Chanson chanson = chansonDao.findById(id);
		if (chanson != null) {
			System.out.println("La chanson " + id + " a été trouvé");
			chanson.afficher();
		} else {
			System.out.println("La chanson" + id + " n'existe pas");
		}
}
	public static void chercherEtAfficherChansonById(ChansonDAO chansonDao, String disqueId){
		List<Chanson> chansons = chansonDao.findByDisqueId(disqueId);
		if (chansons != null) {
			for(Chanson chanson: chansons)
			System.out.println("La chanson " + chanson.getNom() + " a été trouvé :");
		
		} else {
			System.out.println("La chanson" + disqueId + " n'existe pas");
		}
}}
