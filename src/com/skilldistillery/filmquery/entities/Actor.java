package com.skilldistillery.filmquery.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Actor {

		private int id;
		private String firstName;
		private String lastName;
		private List<Film> film;
		private static final String url = "jdbc:mysql://localhost:3306/student_play?useSSL=false";
		private static final String user = "student";
		private static final String pass = "student";
		
public Actor() throws ClassNotFoundException {
	try {
		Class.forName("com.mysql.jdbc.Driver");			
	}
	catch(ClassNotFoundException e){
		e.printStackTrace();
	}
}
public Actor findActorById(int actorId) throws SQLException, ClassNotFoundException {
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
	
	  preSt.setInt(1,actorId);
	  ResultSet actorResult = preSt.executeQuery();
	  if (actorResult.next()) {
	    actor = new Actor(); // Create the object
	    // Here is our mapping of query columns to our object fields:
	    actor.setId(actorResult.getInt("id"));
	    actor.setFirstName(actorResult.getString("first_name"));
	    actor.setLastName(actorResult.getString("last_name"));
	    actor.setFilm(findFilmsByActorId(actorId)); // An Actor has Films
	  }
	  //...
	  return actor;
	}


	public List<Film> findFilmsByActorId(int actorId) {
	  List<Film> films = new ArrayList<>();
	  try {
	    Connection conn = DriverManager.getConnection(url, user, pass);
	    String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
	                sql += " rental_rate, length, replacement_cost, rating, special_features "
	               +  " FROM film JOIN film_actor ON film.id = film_actor.film_id "
	               + " WHERE actor_id = ?";
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
	      Film film = new Film(filmId, title, desc, releaseYear, langId,
	                           rentDur, rate, length, repCost, rating, features);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Film> getFilm() {
		return film;
	}


	public void setFilm(List<Film> film) {
		this.film = film;
	}
	@Override
	public int hashCode() {
		return Objects.hash(film, firstName, id, lastName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return Objects.equals(film, other.film) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName);
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Actor [id=").append(id).append(", firstName=").append(firstName).append(", lastName=")
				.append(lastName).append(", film=").append(film).append("]");
		return builder.toString();
	}



}
