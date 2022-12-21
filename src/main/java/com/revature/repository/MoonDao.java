package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Moon;
import com.revature.utilities.ConnectionUtil;

public class MoonDao {
    
    public List<Moon> getAllMoons() throws SQLException {
		try (Connection connection = ConnectionUtil.createConnection()){ //this is the try with resources
			String sql = "select * from moons";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			List<Moon> moons = new ArrayList<>();
			while(rs.next()){
				Moon moon = new Moon();
				moon.setId(rs.getInt(1));
				moon.setName(rs.getString(2));
				moon.setMyPlanetId(rs.getInt(3));
				moons.add(moon);
			}
			return moons;
		} 
	}

	public Moon getMoonByName(String username, String moonName) {
		try (Connection connection = ConnectionUtil.createConnection()){ //this is the try with resources
			String sql = "select * from moons where name = ?"; //need to look at which column to check for and look at SQL syntax to see where
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, moonName);
			ResultSet rs = statement.executeQuery();
			rs.next();
			Moon moon = new Moon();
			moon.setId(rs.getInt(1));
			moon.setMyPlanetId(rs.getInt(3));
			moon.setName(moonName); 
			return moon;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return new Moon();
		}
	}

	public Moon getMoonById(String username, int moonId) {
		try (Connection connection = ConnectionUtil.createConnection()){ //this is the try with resources
			String sql = "select * from moons where id = ?"; //need to look at which column to check for and look at SQL syntax to see where
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, moonId);
			ResultSet rs = statement.executeQuery();
			rs.next();
			Moon moon = new Moon();
			moon.setId(moonId);
			moon.setName(rs.getString(2));
			moon.setMyPlanetId(rs.getInt(3));
			return moon;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return new Moon();
		}
	}

	public Moon createMoon(String username, Moon m) {
		try (Connection connection = ConnectionUtil.createConnection()){ //this is the try with resources
			String sql = "Insert into moons values (default, ?, ?)"; //need to look at which column to check for and look at SQL syntax to see where
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, m.getName());
			statement.setInt(2, m.getMyPlanetId());
			statement.execute();
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			m.setId(rs.getInt(1));
			return m;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return new Moon();
		}
	}

	public void deleteMoonById(int moonId) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "delete from moons where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, moonId);
			int rowsAffected = ps.executeUpdate();
			System.out.println("Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			System.out.println(e.getMessage()); 
		}
	}

	public List<Moon> getMoonsFromPlanet(int planetId) throws SQLException{ 
		try (Connection connection = ConnectionUtil.createConnection()){ //this is the try with resources
			String sql = "select * from moons where myPlanetId = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, planetId);
			ResultSet rs = statement.executeQuery();
			List<Moon> moons = new ArrayList<>();
			while(rs.next()){
				Moon moon = new Moon();
				moon.setMyPlanetId(planetId);
				moon.setId(rs.getInt(1));
				moon.setName(rs.getString(2));
				moons.add(moon);
			}
			return moons;
		} 
	}
}
