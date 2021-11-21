package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void test() {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		int user = 0;

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		int userChoice;
		System.out.println("Welcome to the Film Query App!");
		do {

			printMenu();
			userChoice = input.nextInt();
			input.nextLine();
			switch (userChoice) {
			case 1:
				System.out.println("Please enter the Film's ID: ");
				userChoice = input.nextInt();
				input.nextLine();
				System.out.println(db.findFilmById(userChoice));
				break;
			case 2:
				System.out.println("Please enter the Actor's ID: ");
				userChoice = input.nextInt();
				input.nextLine();
				System.out.println(db.findActorById(userChoice));
				break;
			case 3:
				System.out.println("Please enter the Actors in a film by film's ID: ");
				userChoice = input.nextInt();
				input.nextLine();
				System.out.println(db.findActorsByFilmId(userChoice));
				break;
			case 4:
				System.out.println("Please enter the word you wish to check title or "
						+ "description of film to get a List of \n films that meet that requirement: ");
				String userChoiceWord = input.next();
				input.nextLine();
				List<Film> films = db.findFilmByFilmWordSearch(userChoiceWord);
				List<Actor> actors = db.findActorsByFilmWordSearch(userChoiceWord);
				for (Film film : films) {
					System.out.println(film);
					
				}
				break;
			case 0:
				System.out.println("Have a wonderful day, Goodbye!");
				System.out.println("App is closing down....");
				break;
			default:
				System.err.println("Please enter a valid option: \n user inputted " + userChoice + " is not valid");

			}

		} while (userChoice != 0);

	}

	private void printMenu() {

		System.out.println("Menu of Aviable options:");
		System.out.println("Option 1: Find film by id");
		System.out.println("Option 2: Find actor by id");
		System.out.println("Option 3: Find actors by film id");
		System.out.println("Option 4: Find Film(s) by containing a part of word or phrase to be found.");
		System.out.println("");
		System.out.println("Please Select Option by its number or enter 0 to quit: ");

	}

}
