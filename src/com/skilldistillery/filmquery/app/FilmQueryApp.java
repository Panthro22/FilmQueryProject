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

//	private void test() {
//		Film film = db.findFilmById(1);
//		System.out.println(film);
//	}

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
				Film oneFilm = db.findFilmById(userChoice);
				if( oneFilm != null) {
				System.out.println(oneFilm.toString("OhDearWork!"));
				subMenuOption(input, oneFilm);
				}
				else {
					System.out.println("Unable to find able to find any match");
				}
				break;
			case 2:
				System.out.println("Please enter the Actor's ID: ");
				userChoice = input.nextInt();
				input.nextLine();
				Actor actor = db.findActorById(userChoice);
				if(actor != null) {
				System.out.println(actor.toString("IFeelThisShouldWork"));
				subMenuOption(actor, input);
			}
			else {
				System.out.println("Unable to find able to find any match");
			}
				break;
			case 3:
				System.out.println("Please enter the Actors in a film by film's ID: ");
				userChoice = input.nextInt();
				input.nextLine();
				List<Actor> actors = db.findActorsByFilmId(userChoice);
				if(!actors.isEmpty()) {
				for (Actor acter : actors) {
					System.out.println(acter.toString("ComeOnBaby"));
				}
				subMenuOptionList(actors, input);
				}
				else {
					System.out.println("Unable to find able to find any match");
				}
				break;
			case 4:
				System.out.println("Please enter the word you wish to check title or "
						+ "description of film to get a List of \n films that meet that requirement: ");
				String userChoiceWord = input.next();
				input.nextLine();
				List<Film> films = db.findFilmByFilmWordSearch(userChoiceWord);
				// List<Actor> actors = db.findActorsByFilmWordSearch(userChoiceWord);
				if(!films.isEmpty()) {
				for (Film film : films) {
					System.out.println(film.toString(userChoiceWord));

				}
				subMenuOptionList(input, films);
				}
				else {
					System.out.println("Unable to find able to find any match");
				}
				break;
			case 0:
				System.out.println("Have a wonderful day, Goodbye!");
				System.out.println("App is closing down....");
				break;
			default:
				System.err.println("Please enter a valid option: \n user inputted \"" + userChoice + "\" is not valid");

			}

		} while (userChoice != 0);

	}

	private void subMenuOptionList(List<Actor> actors, Scanner input) {
		int whileCheck = 0;
		do {
			System.out.println("Select from the following options:");
			System.out.println("Option 1: Show all the details of the Film.");
			System.out.println("Option 2: return to main menu");
			System.out.println("");
			System.out.println("");
			System.out.println("Please enter number for the option.");
			int userChoice = input.nextInt();
			whileCheck = userChoice;
			input.nextLine();
			switch (userChoice) {
			case 1:
				System.out.println("Printing Information");
				System.out.println(actors.toString());
				System.out.println("Printing Complete");

				userChoice = 2;
				break;
			case 2:
				System.out.println("Returning to main menu.");

				break;
			default:
				System.err.println("Please enter a valid option: \n user inputted \"" + userChoice + "\" is not valid");

			}
		} while (whileCheck == 2);

	}

	private void subMenuOptionList(Scanner input, List<Film> films) {
		int whileCheck = 0;
		do {
			System.out.println("Select from the following options:");
			System.out.println("Option 1: Show all the details of the Film.");
			System.out.println("Option 2: return to main menu");
			System.out.println("");
			System.out.println("");
			System.out.println("Please enter number for the option.");
			int userChoice = input.nextInt();
			whileCheck = userChoice;
			input.nextLine();
			switch (userChoice) {
			case 1:
				System.out.println("Printing Information");
				for (Film film : films) {
					System.out.println(film.toString());

				}
				System.out.println("Printing Complete");

				userChoice = 2;
				break;
			case 2:
				System.out.println("Returning to main menu.");

				break;
			default:
				System.err.println("Please enter a valid option: \n user inputted \"" + userChoice + "\" is not valid");

			}
		} while (whileCheck == 2);

	}

	private void printMenu() {

		System.out.println("Menu of Aviable options:");
		System.out.println("Option 1: Find film by id");
		System.out.println("Option 2: Find actor by id");
		System.out.println("Option 3: Find actors by film id");
		System.out.println("Option 4: Find Film(s) by containing a part of word or phrase to be found.");
		System.out.println("");
		System.out.println("");
		System.out.println("Please Select Option by its number or enter 0 to quit: ");

	}

	private void subMenuOption(Scanner input, Film film) {
		int whileCheck = 0;
		do {
			System.out.println("Select from the following options:");
			System.out.println("Option 1: Show all the details of the Film.");
			System.out.println("Option 2: return to main menu");
			System.out.println("");
			System.out.println("");
			System.out.println("Please enter number for the option.");
			int userChoice = input.nextInt();
			whileCheck = userChoice;
			input.nextLine();
			switch (userChoice) {
			case 1:
				System.out.println("Printing Information");
				System.out.println(film.toString());
				System.out.println("Printing Complete");

				userChoice = 2;
				break;
			case 2:
				System.out.println("Returning to main menu.");

				break;
			default:
				System.err.println("Please enter a valid option: \n user inputted \"" + userChoice + "\" is not valid");

			}
		} while (whileCheck == 2);
	}

	private void subMenuOption(Actor actor, Scanner input) {
		int whileCheck = 0;
		do {
			System.out.println("Select from the following options:");
			System.out.println("Option 1: Show all the details of the Film.");
			System.out.println("Option 2: return to main menu");
			System.out.println("");
			System.out.println("");
			System.out.println("Please enter number for the option.");
			int userChoice = input.nextInt();
			whileCheck = userChoice;
			input.nextLine();
			switch (userChoice) {
			case 1:
				System.out.println("Printing Information");
				System.out.println(actor.toString());
				System.out.println("Printing Complete");

				userChoice = 2;
				break;
			case 2:
				System.out.println("Returning to main menu.");

				break;
			default:
				System.err.println("Please enter a valid option: \n user inputted \"" + userChoice + "\" is not valid");

			}
		} while (whileCheck == 2);
	}

}
