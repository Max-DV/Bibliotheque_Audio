package fr.lteconsulting.modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.lteconsulting.dao.DisqueDAO;
import fr.lteconsulting.outils.Saisie;

public class Bibliotheque
{
	
	private DisqueDAO disqueDAO;
	
	public void ajouterDisque(String id, String nom)
	{
		disqueDAO.add(id, nom);
	}
	
	public Bibliotheque ()
	{
		this.disqueDAO= disqueDAO;
	}

	public List<Disque> getDisques()
	{
		return disqueDAO.findAll();
	}

	public Disque rechercherDisqueParCodeBarre( String id )
	{
		return disqueDAO.findById(id);
	}

	public List<Disque> rechercherDisqueParNom (String recherche)
	{
		return disqueDAO.findByName(recherche);
	}
	public List<Disque> rechercherDisqueParNom(List<String> termes)
	{

		Map<String, Disque> validDisques= new HashMap<>();
		for (String terme: termes)
		{

			for (Disque disque: disqueDAO.findByName(terme))
				validDisques.put(disque.getCodeBarre(), disque);
		}

		return new ArrayList<> (validDisques.values());
	}

	

	public void afficher()
	{
		List<Disque> disques = disqueDAO.findAll();
		System.out.println( "BIBLIOTHEQUE avec " + disques.size() + " disques" );
		for( Disque disque : disques )
			disque.afficher();
	}

	


public static void main(String[] args) {
	
	Bibliotheque biblio = new Bibliotheque();

	while (true) {
		System.out.println("1. Afficher la liste des disques");
		System.out.println("2. Afficher un disque");
		System.out.println("3. Ajouter un disque");
		System.out.println("4. Supprimer un disque");
		System.out.println("5. Quitter l'application");
		
		DisqueDAO dao = new DisqueDAO(null, null);

		String saisie = Saisie.saisie("Faites un choix :");
		switch (saisie) {
		case "1":
			List<Disque> listDisk=dao.findAll();
			System.out.println(listDisk);
			
			break;
		
		case "2":
			String id = Saisie.saisie("Quel Id cherchez vous ?");
			Disque disqueCherche = dao.findById(id);
			System.out.println(disqueCherche);
			break;
			
		case "3":
			String idAdd = Saisie.saisie("Id du nouveau disque :");
			String nom = Saisie.saisie("Nom du nouveau disque :");
			
			Disque nouveauDisque = dao.add(idAdd, nom);

			System.out.println("Le disque "+nouveauDisque+" a bien été ajouté");
			break;
			
		case "4":
			String idDelete = Saisie.saisie("Quel est l'id du disque à supprimer ?");
			dao.delete(idDelete);
			System.out.println("Le disque "+idDelete+" a bien été supprimé");
			break;

		case "5":
		
			System.exit(0);
			
			break;
		default:
			System.out.println("Ce choix n'existe pas");
		}
	}
}

public void ajouterDisque(Disque d) {
	// TODO Auto-generated method stub
	
}
}