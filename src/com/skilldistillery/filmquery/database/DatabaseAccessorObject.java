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
	private static final String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String user = "student";
	private static final String pass = "student";

	@Override
	public Film findFilmById(int filmId) {

		Film film = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		String sql = "SELECT id, title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features  FROM film WHERE id = ?";
		PreparedStatement preSt = null;

		try {
			preSt = conn.prepareStatement(sql);

			preSt.setInt(1, filmId);
			ResultSet actorResult = preSt.executeQuery();
			if (actorResult.next()) {
				film = new Film(); // Create the object
				// Here is our mapping of query columns to our object fields:
				film.setFilmId(actorResult.getInt("id"));
				film.setTitle(actorResult.getString("title"));
				film.setDesc(actorResult.getString("description"));
				film.setReleaseYear(actorResult.getShort("release_year"));
				film.setLangId(actorResult.getInt("language_id"));
				film.setRentDur(actorResult.getInt("rental_duration"));
				film.setRate(actorResult.getDouble("rental_rate"));
				film.setLengt(actorResult.getInt("length"));
				film.setRepCost(actorResult.getDouble("replacement_cost"));
				film.setRating(actorResult.getString("rating"));
				film.setFeatures(actorResult.getString("special_features"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return film;
	}

	@Override
	public Actor findActorById(int actorId) {

		Actor actor = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		PreparedStatement preSt = null;

		try {
			preSt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			preSt.setInt(1, actorId);

			ResultSet actorResult = preSt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));
				// An Actor has Films
			}
			actorResult.close();
			preSt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// ...
		return actor;

	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor "
					+ "JOIN film_actor ON film_actor.actor_id = actor.id  "
					+ "JOIN film ON film.id = film_actor.film_id  " + "WHERE film.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, filmId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.next()) {
					Actor actor = new Actor();
					// Create the object
					// Here is our mapping of query columns to our object fields:
					actor.setId(rs.getInt("id"));
					actor.setFirstName(rs.getString("first_name"));
					actor.setLastName(rs.getString("last_name"));
					actors.add(actor); 
				}
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
			sql += " rental_rate, length, replacement_cost, rating, special_features "
					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, actorId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);
				films.add(film);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public List<Film> findFilmByFilmWordSearch(String searchWord) {
		List<Film> films = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, "
			 + " rental_rate, length, replacement_cost, rating, special_features " + " FROM film "
					+ " WHERE title LIKE ?";
			PreparedStatement prestmt = conn.prepareStatement(sql);
			prestmt.setString(1, "\"%" + searchWord + "%\"");
			//prestmt.setString(2, "\"%\""+searchWord+"\"%\"");
			ResultSet rs = prestmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);
				films.add(film);
			}
			rs.close();
			prestmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

}
