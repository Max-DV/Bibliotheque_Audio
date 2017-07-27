package fr.lteconsulting.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import fr.lteconsulting.modele.Chanson;
import fr.lteconsulting.modele.Disque;

public class DisqueDAO {
	private Connection connection;
	private ChansonDAO chansonDAO;

	public DisqueDAO(MySQLDatabaseConnection connection, ChansonDAO chansonDAO) {
		this.connection= connection.getConnection();
		this.chansonDAO= chansonDAO;
	}

	public Disque findById(String id) {
		try {
			String sql = "SELECT * FROM `disques` WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String nom = resultSet.getString("nom");
				Disque disque = new Disque(id, nom);
				return disque;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Impossible de réaliser l(es) opération(s)", e);
		}
	}

	public List<Disque> findAll() {
		try {
			String sql = "SELECT * FROM `disques`";
			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet resultSet = statement.executeQuery();
			List<Disque> d = new ArrayList<>();

			while (resultSet.next()) {
				String nom = resultSet.getString("nom");
				String id = resultSet.getString("id");
				Disque disque = new Disque(id, nom);

				d.add(disque);
			}
			return d;

		} catch (SQLException e) {
			throw new RuntimeException("Impossible de réaliser l(es) opération(s)", e);
		}

	}

	public Disque add(String id, String nom) {
		try {
			Disque disque = new Disque(id, nom);
			String sqlQuery = "INSERT INTO disques (`id`, `nom`) VALUES (?, ?)";

			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, id);
			statement.setString(2, disque.getNom());

			int nbEnregistrementInseres = statement.executeUpdate();
			if (nbEnregistrementInseres == 0)
				throw new RuntimeException("Aucun disque inséré");

			disque.setCodeBarre(id);
			return disque;
		} catch (SQLException e) {
			throw new RuntimeException("Impossible d'ajouter le disque", e);
		}
	}

	public void update(Disque disque) {

		try {
			String sqlQuery = "UPDATE disques SET `nom` = ? WHERE id = ?";

			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, disque.getNom());
			statement.setString(2, disque.getCodeBarre());

			statement.executeUpdate();
		} catch (SQLException e) {

			throw new RuntimeException("Impossible de réaliser l(es) opération(s)", e);
		}

	}

	public void delete(String id) {
		try {

			PreparedStatement statement = connection.prepareStatement("DELETE FROM `disques` WHERE id = ?");

			statement.setString(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Impossible de réaliser l(es) opération(s)", e);
		}
	
	}

	public List<Disque> findByName(String search) {
		{ search= search.toLowerCase();
		try {
			List<Disque> disques = new ArrayList<>();
		
			String sql = "SELECT * FROM `disques` WHERE LOWER (`nom`) LIKE ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "%" + search + "%");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String nom = resultSet.getString("nom");
				String id = resultSet.getString("id");
				Disque disque = new Disque(id, nom);
				disques.add(disque);
			} if(disques.size()==0){
				return null;
			}return disques;
			}
		 catch (SQLException e) {
			throw new RuntimeException("Impossible de réaliser l(es) opération(s)", e);
		}
	}
	}}
	