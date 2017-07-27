package fr.lteconsulting;

import java.sql.SQLException;

import fr.lteconsulting.dao.ChansonDAO;
import fr.lteconsulting.dao.MySQLDatabaseConnection;
import fr.lteconsulting.modele.Chanson;


public class ApplicationTest2 {

	public static void main(String[] args) throws SQLException {
		MySQLDatabaseConnection databaseConnection= new MySQLDatabaseConnection();
		ChansonDAO chansonDao = new ChansonDAO( databaseConnection );
		chercherEtAfficherChanson(chansonDao, 10);
	}
	
	
	public static void chercherEtAfficherChanson(ChansonDAO chansonDao, int id){
		Chanson chanson = chansonDao.findById(id);
		if (chanson != null) {
			System.out.println("La chanson " + id + " a été trouvé :");
			chanson.afficher();
		} else {
			System.out.println("La chanson" + id + " n'existe pas");
		}

	
	}}
