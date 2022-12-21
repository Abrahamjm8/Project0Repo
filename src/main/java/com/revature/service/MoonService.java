package com.revature.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Moon;
import com.revature.repository.MoonDao;

public class MoonService {

	private MoonDao dao;

	public MoonService(){
		this.dao = new MoonDao();
	}

	public List<Moon> getAllMoons() {
	try {
		List<Moon> moons = this.dao.getAllMoons();
		return moons;
	}
	catch (SQLException e) {
		System.out.println(e.getMessage());
		return new ArrayList<Moon>();
	}
	}

	public Moon getMoonByName(String username, String moonName) {
		Moon moon = this.dao.getMoonByName(username, moonName);
		return moon;
	}

	public Moon getMoonById(String username, int moonId) {
		Moon moon = this.dao.getMoonById(username, moonId);
		return moon;
	}

	public Moon createMoon(String username, Moon m) {
		return this.dao.createMoon(username, m);
	}

	public void deleteMoonById(int moonId) {
		this.dao.deleteMoonById(moonId);
	}

	public List<Moon> getMoonsFromPlanet(int planetId) {
		try {
		List<Moon> moons = this.dao.getMoonsFromPlanet(planetId);
		return moons;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return new ArrayList<Moon>();
		}
	}
}
