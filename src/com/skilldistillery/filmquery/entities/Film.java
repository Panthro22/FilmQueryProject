package com.skilldistillery.filmquery.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Film {
private int filmId;
private String title;
private String desc;
private short releaseYear;
private int langId;
private int rentDur;
private double rate;
private int lengt;
private double repCost;
private String rating;
private String features;
private List<Actor> actor;
private static final String url = "jdbc:mysql://localhost:3306/student_play?useSSL=false";
private static final String user = "student";
private static final String pass = "student";

public Film() throws ClassNotFoundException {
	try {
		Class.forName("com.mysql.jdbc.Driver");			
	}
	catch(ClassNotFoundException e){
		e.printStackTrace();
	}
}

public Film(int filmId, String title, String desc, short releaseYear, int langId, int rentDur, double rate, int lengt,
		double repCost, String rating, String features) {
	super();
	this.filmId = filmId;
	this.title = title;
	this.desc = desc;
	this.releaseYear = releaseYear;
	this.langId = langId;
	this.rentDur = rentDur;
	this.rate = rate;
	this.lengt = lengt;
	this.repCost = repCost;
	this.rating = rating;
	this.features = features;
	this.actor = null;
}

public Film(int filmId, String title, String desc, short releaseYear, int langId, int rentDur, double rate, int lengt,
		double repCost, String rating, String features, List<Actor> actor) {
	super();
	this.filmId = filmId;
	this.title = title;
	this.desc = desc;
	this.releaseYear = releaseYear;
	this.langId = langId;
	this.rentDur = rentDur;
	this.rate = rate;
	this.lengt = lengt;
	this.repCost = repCost;
	this.rating = rating;
	this.features = features;
	this.actor = actor;
}
public Film findFilmById() {
	/*
	Film film = null;
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
	}*/

	return null;
}
public int getFilmId() {
	return filmId;
}



public void setFilmId(int filmId) {
	this.filmId = filmId;
}



public String getTitle() {
	return title;
}



public void setTitle(String title) {
	this.title = title;
}



public String getDesc() {
	return desc;
}



public void setDesc(String desc) {
	this.desc = desc;
}



public short getReleaseYear() {
	return releaseYear;
}



public void setReleaseYear(short releaseYear) {
	this.releaseYear = releaseYear;
}



public int getLangId() {
	return langId;
}



public void setLangId(int langId) {
	this.langId = langId;
}



public int getRentDur() {
	return rentDur;
}



public void setRentDur(int rentDur) {
	this.rentDur = rentDur;
}



public double getRate() {
	return rate;
}



public void setRate(double rate) {
	this.rate = rate;
}



public int getLengt() {
	return lengt;
}



public void setLengt(int lengt) {
	this.lengt = lengt;
}



public double getRepCost() {
	return repCost;
}



public void setRepCost(double repCost) {
	this.repCost = repCost;
}



public String getRating() {
	return rating;
}



public void setRating(String rating) {
	this.rating = rating;
}



public String getFeatures() {
	return features;
}



public void setFeatures(String features) {
	this.features = features;
}



@Override
public int hashCode() {
	return Objects.hash(desc, features, filmId, langId, lengt, rate, rating, releaseYear, rentDur, repCost, title);
}



@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Film other = (Film) obj;
	return Objects.equals(desc, other.desc) && Objects.equals(features, other.features) && filmId == other.filmId
			&& langId == other.langId && lengt == other.lengt
			&& Double.doubleToLongBits(rate) == Double.doubleToLongBits(other.rate)
			&& Objects.equals(rating, other.rating) && releaseYear == other.releaseYear && rentDur == other.rentDur
			&& Double.doubleToLongBits(repCost) == Double.doubleToLongBits(other.repCost)
			&& Objects.equals(title, other.title);
}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Film [filmId=").append(filmId).append(", title=").append(title).append(", desc=").append(desc)
			.append(", releaseYear=").append(releaseYear).append(", langId=").append(langId).append(", rentDur=")
			.append(rentDur).append(", rate=").append(rate).append(", lengt=").append(lengt).append(", repCost=")
			.append(repCost).append(", rating=").append(rating).append(", features=").append(features)
			.append(", actor=").append(actor).append("]");
	return builder.toString();
}



}
