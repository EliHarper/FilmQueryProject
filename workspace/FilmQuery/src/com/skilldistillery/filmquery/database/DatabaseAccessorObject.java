package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";
	private static final String user = "student";
	private static final String pass = "student";
	// All JDBC Code goes here

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		DatabaseAccessorObject dao = new DatabaseAccessorObject();
		
	}

	@Override
	public Film getFilmById(int filmId) {
		Film film = null;
		String sql = "SELECT title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features, id from film where id = ?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String title = rs.getString(1);
				String description = rs.getString(2);
				int releaseYear = rs.getInt(3);
				int languageId = rs.getInt(4);
				int rentalDuration = rs.getInt(5);
				double rentalRate = rs.getDouble(6);
				int length = rs.getInt(7);
				double replacementCost = rs.getDouble(8);
				String rating = rs.getString(9);
				String specialFeatures = rs.getString(10);
				int id = rs.getInt(11);
				film = new Film(id, title, description, releaseYear, languageId, rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures);
			}

			rs.close();
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return film;
	}

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Unable to load DB driver. Exiting.");
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public Actor getActorById(int actorId) {
		Actor actor = null;

		String sql = "select id, first_name, last_name from actor where id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				actor = new Actor(id, firstName, lastName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> getActorsByFilmId(int filmId) {
		List<Actor> cast = new ArrayList<>();
		
		String sql = "select a.first_name, a.last_name, a.id from film f join film_actor fa on f.id = fa.film_id join actor a on a.id = fa.actor_id where f.id = ?";
		
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String firstName = rs.getString(1);
				String lastName = rs.getString(2);
				int id = rs.getInt(3);
				
				Actor actor = new Actor(id, firstName, lastName);
				cast.add(actor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cast;
	}

}
