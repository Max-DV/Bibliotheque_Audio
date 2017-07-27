package fr.lteconsulting;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.lteconsulting.dao.ChansonDAO;
import fr.lteconsulting.dao.DisqueDAO;
import fr.lteconsulting.dao.MySQLDatabaseConnection;
import fr.lteconsulting.modele.Disque;

public class ApplicationTest {
	public static void main(String[] args) throws SQLException {
		MySQLDatabaseConnection databaseConnection = new MySQLDatabaseConnection();

		ChansonDAO chansonDao = new ChansonDAO( databaseConnection );
		DisqueDAO dao = new DisqueDAO( databaseConnection, chansonDao );


		chercherEtAfficherDisque(dao, "pptt");
		chercherEtAfficherDisque(dao, "ppttdddd");
		afficherTousLesDisques(dao);
		ajouterDisque(dao, "souk", "cdecf" );
		updaterDisque(dao, "yopu", "yooooooooo");
		afficherTousLesDisques(dao);
		supprimerDisque(dao, "souk");
		trouverParNom(dao, "c");
	}

	private static void chercherEtAfficherDisque(DisqueDAO dao, String id) {
		Disque disque = dao.findById(id);
		if (disque != null) {
			System.out.println("Le disque " + id + " a été trouvé :");
			disque.afficher();
		} else {
			System.out.println("Le disque " + id + " n'existe pas");
		}

	}

	private static void afficherTousLesDisques(DisqueDAO dao) {
		List<Disque> d = new ArrayList<>();
		d = dao.findAll();
		if (d != null) {
			System.out.println("BIBLIOTHEQUE avec " + d.size() + " disques");
			System.out.println("Voici la liste des disques:" + d.toString());

		} else {
			System.out.println("Les disques n'ont pas été trouvé");
		}
	}

	private static void ajouterDisque(DisqueDAO dao, String nom, String id) throws SQLException {
		Disque disque = new Disque(nom, id);
		dao.add(nom, id);
		if (nom != null) {

			System.out.println(" Le disque a été ajouté: " + disque.toString());
		
		}else{
			System.out.println("Le disque " + disque.getNom() + "existe déja");
		}

	}

	private static void supprimerDisque(DisqueDAO dao, String id) {
		Disque disque = dao.findById(id);
		if (id != null) {
			System.out.println("Le disque " + disque.toString() + " a été supprimé");
			dao.delete(id);
		}else{
			System.out.println("Vous ne supprimé pas la");
		}
	}

	private static void updaterDisque(DisqueDAO dao, String id, String nom) throws SQLException{
		Disque disque = new Disque (id, nom);
		dao.update(disque);
		if (nom != null) {
			System.out.println("le disque " + disque.toString() + " a été updaté");
		}
		else{
			System.out.println("Vous ne modifiez pas la");
		}
	}
	
	private static void trouverParNom(DisqueDAO dao, String recherche){
		List<Disque>search= dao.findByName(recherche);
				if(search != null){
					System.out.println("Le(s) disque(s) contenant :" + recherche + " : " + search);
				} else{
					System.out.println("Il n'y a pas de disque contenant: " + recherche);
				}
				
	}
}
