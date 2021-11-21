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
		String sql = "SELECT film.id, film.title, film.description, film.release_year, language.name, film.rental_duration, "
				+ "film.rental_rate, film.length, film.replacement_cost, film.rating, film.special_features  "
				+ "FROM film "
				+ "JOIN language ON film.language_id = language.id "
				+ "WHERE film.id = ?;";
		PreparedStatement preSt = null;

		try {
			preSt = conn.prepareStatement(sql);

			preSt.setInt(1, filmId);
			ResultSet actorResult = preSt.executeQuery();
			if (actorResult.next()) {
				film = new Film(); // Create the object
				// Here is our mapping of query columns to our object fields:
				film.setFilmId(actorResult.getInt("film.id"));
				film.setTitle(actorResult.getString("film.title"));
				film.setDesc(actorResult.getString("film.description"));
				film.setReleaseYear(actorResult.getShort("film.release_year"));
				film.setLang(actorResult.getString("language.name"));
				film.setRentDur(actorResult.getInt("film.rental_duration"));
				film.setRate(actorResult.getDouble("film.rental_rate"));
				film.setLengt(actorResult.getInt("film.length"));
				film.setRepCost(actorResult.getDouble("film.replacement_cost"));
				film.setRating(actorResult.getString("film.rating"));
				film.setFeatures(actorResult.getString("film.special_features"));
				film.setActors(findActorsByFilmId(filmId));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		// List<Film> films = null;
		Actor actor = null;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?;";
			PreparedStatement preSt = null;
			preSt = conn.prepareStatement(sql);
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
					+ "JOIN film ON film.id = film_actor.film_id  " + "WHERE film.id = ?;";
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
			String sql = "SELECT film.id, film.title, film.description, film.release_year, language.name, film.rental_duration, ";
			sql += " film.rental_rate, film.length, film.replacement_cost, film.rating, film.special_features "
					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id "
					+ " JOIN language ON film.language_id = language.id" + " WHERE actor_id = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, actorId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("film.id");
				String title = rs.getString("film.title");
				String desc = rs.getString("film.description");
				short releaseYear = rs.getShort("film.release_year");
				String lang = rs.getString("language.name");
				int rentDur = rs.getInt("film.rental_duration");
				double rate = rs.getDouble("film.rental_rate");
				int length = rs.getInt("film.length");
				double repCost = rs.getDouble("film.replacement_cost");
				String rating = rs.getString("film.rating");
				String features = rs.getString("film.special_features");
				Film film = new Film(filmId, title, desc, releaseYear, lang, rentDur, rate, length, repCost, rating,
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
			String searchQuery = "%" + searchWord + "%";
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT film.id, film.title, film.description, film.release_year, language.name, film.rental_duration, "
					+ " film.rental_rate, film.length, film.replacement_cost, film.rating, film.special_features "
					+ " FROM film " + "JOIN language ON film.language_id = language.id"
					+ " WHERE description LIKE ? OR title LIKE  ? ";
			PreparedStatement prestmt = conn.prepareStatement(sql);
			prestmt.setNString(1, searchQuery);
			prestmt.setNString(2, searchQuery);
			ResultSet rs = prestmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("film.id");
				String title = rs.getString("film.title");
				String desc = rs.getString("film.description");
				short releaseYear = rs.getShort("film.release_year");
				String lang = rs.getString("language.name");
				int rentDur = rs.getInt("film.rental_duration");
				double rate = rs.getDouble("film.rental_rate");
				int length = rs.getInt("film.length");
				double repCost = rs.getDouble("film.replacement_cost");
				String rating = rs.getString("film.rating");
				String features = rs.getString("film.special_features");
				
				Film film = new Film(filmId, title, desc, releaseYear, lang, rentDur, rate, length, repCost, rating,features);
				film.setActors(findActorsByFilmId(filmId));
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

	@Override
	public List<Actor> findActorsByFilmWordSearch(String searchWord) {
		List<Actor> actors = new ArrayList<>();

		try {
			String searchQuery = "%" + searchWord + "%";
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor "
					+ "JOIN film_actor ON film_actor.actor_id = actor.id  "
					+ "JOIN film ON film.id = film_actor.film_id "
					+ " WHERE film.description LIKE ? OR film.title LIKE  ? ;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, searchQuery);
			pstmt.setNString(2, searchQuery);
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

}
