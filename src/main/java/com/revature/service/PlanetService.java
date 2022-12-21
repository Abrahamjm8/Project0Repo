package com.revature.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Planet;
import com.revature.repository.PlanetDao;

public class PlanetService {

	private PlanetDao dao;

	public PlanetService(){
		this.dao = new PlanetDao();
	}

	public List<Planet> getAllPlanets() {
		try {
		return this.dao.getAllPlanets();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return new ArrayList<Planet>();
		}
	}

	public Planet getPlanetByName(String owner, String planetName) {
		return this.dao.getPlanetByName(owner, planetName);
	}

	public Planet getPlanetById(String username, int planetId){
		return this.dao.getPlanetByID(username, planetId);
	}

	public Planet createPlanet(String username, Planet p) { //changed this to an int instead of int
		return this.dao.createPlanet(username, p);
	}

	public void deletePlanetById(int planetId) {
		this.dao.deletePlanetById(planetId);
	}
}
