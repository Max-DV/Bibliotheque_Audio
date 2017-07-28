package fr.lteconsulting.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.lteconsulting.modele.Chanson;


public class ChansonDAO {
	private Connection connection;

	public ChansonDAO(MySQLDatabaseConnection connection) 
	{
	this.connection= connection.getConnection();
	}

	public Chanson findById(int id) {
		try {
			
			String sql = "SELECT * FROM `chansons` WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if( resultSet.next() )
				return createChansonFromResultSet( resultSet );
			else
				return null;
		
		} catch (SQLException e) {
			throw new RuntimeException("Impossible de réaliser l(es) opération(s)", e);
		}
	}
	
	
	public List<Chanson> findByDisqueId(String disqueId){
try {
			List<Chanson> chansons = new ArrayList<>();
			String sql = "SELECT * FROM `chansons` WHERE disque_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, disqueId);
			ResultSet resultSet = statement.executeQuery();
			while( resultSet.next() )
				
			chansons.add (createChansonFromResultSet(resultSet));
		if(chansons.size()==0){
			return null;
		}
			
				return chansons;
		
		} catch (SQLException e) {
			throw new RuntimeException("Impossible de réaliser l(es) opération(s)", e);
		}
		
	}

	public void add(Chanson chanson) {
		// TODO Auto-generated method stub
		
	}

	public void update(Chanson chanson) {
		// TODO Auto-generated method stub
		
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	public void deleteByDisqueId(String id) {
		// TODO Auto-generated method stub
		
	}
	
	private Chanson createChansonFromResultSet( ResultSet resultSet ) throws SQLException
	{
		int id = resultSet.getInt( "id" );
		String disqueId = resultSet.getString( "disque_id" );
		String nom = resultSet.getString( "nom" );
		int duree = resultSet.getInt( "duree" );

		Chanson chanson = new Chanson();

		chanson.setId( id );
		chanson.setNom( nom );
		chanson.setDureeEnSecondes( duree );
		chanson.setDisqueId( disqueId );

		return chanson;
	}
	
	
	
}
