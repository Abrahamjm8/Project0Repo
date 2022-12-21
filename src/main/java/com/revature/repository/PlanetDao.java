package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Planet;
import com.revature.utilities.ConnectionUtil;

public class PlanetDao {

	/*
	 * added the throws clause to the method signature bc the alterncative
	 * is to return an empty arrayList, but the method could succeed with no planets
	 * returned, so this is not an ideal solution, 
	 * Instead we will let the service layer and/pr API handle the excep being thrown
	 * 
	 */

	 //this is done 
    public List<Planet> getAllPlanets() throws SQLException{ 
		try (Connection connection = ConnectionUtil.createConnection()){ //this is the try with resources
			String sql = "select * from planets";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql); 
			rs.next();
			List<Planet> planets = new ArrayList<>();
			while(rs.next()){
				Planet planet = new Planet();
				planet.setId(rs.getInt(1));
				planet.setName(rs.getString(2));
				planet.setOwnerId(rs.getInt(3));
				planets.add(planet);
			}
			return planets;
		} 
	}

	public Planet getPlanetByName(String owner, String planetName) {
		try (Connection connection = ConnectionUtil.createConnection()){ //this is the try with resources
			String sql = "select * from planets where name=?"; //need to look at which column to check for and look at SQL syntax to see where
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, planetName);
			ResultSet rs = statement.executeQuery();
			rs.next();
			Planet planet = new Planet();
			planet.setId(rs.getInt(1));
			planet.setName(rs.getString(2));
			planet.setOwnerId(rs.getInt(3));
			return planet;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return new Planet();
		}
	}


	//
	public Planet getPlanetByID(String username, int planetId) {
		try (Connection connection = ConnectionUtil.createConnection()){ //this is the try with resources
			String sql = "select * from planets where id = ?"; //need to look at which column to check for and look at SQL syntax to see where
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, planetId);
			ResultSet rs = statement.executeQuery();
			rs.next();
			Planet planet = new Planet();
			planet.setId(planetId);
			planet.setName(rs.getString(2));
			planet.setOwnerId(rs.getInt(3));
			return planet;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return new Planet();
		}
	}

	public Planet createPlanet(String username, Planet p)  { //changed this to an int for ownerId, CHECK IN with eric
		try (Connection connection = ConnectionUtil.createConnection()){ //this is the try with resources
			String sql = "Insert into planets values (default, ?, ?)"; //need to look at which column to check for and look at SQL syntax to see where
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, p.getName());
			statement.setInt(2, p.getOwnerId());
			statement.execute(); // execute the statement
            ResultSet rs = statement.getGeneratedKeys();
			rs.next(); //do you have to somehow get the user's id with the username? which corresponds to planets foreign id
			p.setId(rs.getInt(1));
			return p;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return new Planet();
		}
	}

	public void deletePlanetById(int planetId) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "delete from planets where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, planetId);
			int rowsAffected = ps.executeUpdate();
			System.out.println("Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			System.out.println(e.getMessage()); //good place for logging
		}
	}


	public static void main(String[] args) {
		PlanetDao planetDao = new PlanetDao();
		Planet p = new Planet();
		p.setName("Mars");
		p.setOwnerId(4);
		System.out.println(planetDao.createPlanet("Java", p));
		//System.out.println(planetDao.getPlanetByName("Java", "Mars"));
		//System.out.println(planetDao.getPlanetByID("Java", 8));
		// try (Connection connection = ConnectionUtil.createConnection())
		// {System.out.println(planetDao.getAllPlanets());
		// }catch (SQLException e) {
		// 	System.out.println(e.getMessage());
		// }
	}
}
