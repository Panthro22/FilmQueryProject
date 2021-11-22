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
private String lang;
private int rentDur;
private double rate;
private int lengt;
private double repCost;
private String rating;
private String features;
private List<Actor> actors;

public Film() {
	try {
		Class.forName("com.mysql.jdbc.Driver");			
	}
	catch(ClassNotFoundException e){
		e.printStackTrace();
	}
}

public Film(int filmId, String title, String desc, short releaseYear, String lang, int rentDur, double rate, int lengt,
		double repCost, String rating, String features) {
	this.filmId = filmId;
	this.title = title;
	this.desc = desc;
	this.releaseYear = releaseYear;
	this.lang = lang;
	this.rentDur = rentDur;
	this.rate = rate;
	this.lengt = lengt;
	this.repCost = repCost;
	this.rating = rating;
	this.features = features;

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



public String getLang() {
	return lang;
}



public void setLang(String lang) {
	this.lang = lang;
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
	return Objects.hash(desc, features, filmId, lang, lengt, rate, rating, releaseYear, rentDur, repCost, title);
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
			&& lang == other.lang && lengt == other.lengt
			&& Double.doubleToLongBits(rate) == Double.doubleToLongBits(other.rate)
			&& Objects.equals(rating, other.rating) && releaseYear == other.releaseYear && rentDur == other.rentDur
			&& Double.doubleToLongBits(repCost) == Double.doubleToLongBits(other.repCost)
			&& Objects.equals(title, other.title);
}
public String toString(String limitedData) {
	String actorString="";
	for(Actor actor: actors) {
		actorString += actor.toString("wasup");
	}
	StringBuilder builder = new StringBuilder();
	builder.append("Film [").append("title=").append(title).append(", \ndesc=").append(desc)
			.append(", \nreleaseYear=").append(releaseYear).append(", \nlang=").append(lang)
			.append(", \nrating=").append(rating).append(actorString)
			.append("]");
	
	return builder.toString();
}

public List<Actor> getActors() {
	return actors;
}

public void setActors(List<Actor> actors) {
	this.actors = actors;
}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Film [filmId=").append(filmId).append(", title=").append(title).append(", desc=").append(desc)
			.append(", releaseYear=").append(releaseYear).append(", lang=").append(lang).append(", rentDur=")
			.append(rentDur).append(", rate=").append(rate).append(", lengt=").append(lengt).append(", repCost=")
			.append(repCost).append(", rating=").append(rating).append(", features=").append(features)
			.append(actors).append("]");
	return builder.toString();
}

//public List<Actor> getActors() {
//	return actors;
//}

//public void setActors(List<Actor> actors) {
//	this.actors = actors;
}

