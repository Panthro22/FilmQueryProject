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
	private List<Film> films;
	

		public Actor() {
			try {
				Class.forName("com.mysql.jdbc.Driver");			
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}
		
	public Actor(int id, String firstName, String lastName, List<Film> film) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		
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



	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this.getId() == ((Actor)obj).getId())
			return true;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return  Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName);
	}
	public String toString(String limited) {
		StringBuilder builder = new StringBuilder();
		builder.append("\nActor ").append(" firstName=").append(firstName).append(", lastName=")
				.append(lastName).append(films).append("]");
		return builder.toString();
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Actor id=").append(id).append(", \nfirstName=").append(firstName).append(", \nlastName=")
				.append(lastName).append("\n"+films);
		return builder.toString();
	}

//	public List<Film> getFilms() {
//		return films;
//	}
//
//	public void setFilms(List<Film> films) {
//		this.films = films;
//	}


}
