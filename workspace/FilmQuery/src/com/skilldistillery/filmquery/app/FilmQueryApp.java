package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
    
    
    app.launch();
    
  }

//  private void test() {
//	List<Actor> actors = db.getActorsByFilmId(1);
//    System.out.println(actors);
//  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
	  
	  
	  int choice = 0;
	  while (choice != 3) {
//	  	  Nice lil menu here
		  System.out.println("Would you like to...");
		  System.out.println("____________________");
		  System.out.println("1. Look up a film by id");
		  System.out.println("2. Look up a film by a search keyword");
		  System.out.println("3. Exit" + "\n");
		  
		  try {
			choice = input.nextInt();
		  } 
		  catch (InputMismatchException e) {
			choice = 0;
			input.nextLine();
		}
		  
		  switch (choice) {
		   case 1: 
			  		System.out.print("Please enter the id you would like to search for: ");
			  		System.out.println();
			  		int fid = 0;
				
			  		fid = input.nextInt();
			  		
			  		if (fid > 0 && fid < 1001) {
			  			Film film = db.getFilmById(fid);
			  			System.out.println("\n" + film.getTitle() + ", released in " + film.getReleaseYear() + ", is a(n) " + film.getLanguage() + " film about " + film.getDescription() + "\n\nCast:\n");
			  			
			  			for (Actor a : film.getCast()) {
			  				System.out.println(a);
			  			}
			  		}
			  		else {
			  			System.out.println("The number must be an integer over 0 and under 1001.\n");
			  		}
			  		
			  		//startUserInterface(input);
			  		
		  			break;
		  			
		  case 2: 
			  Film film = null;
			  System.out.print("Please enter your search keyword: ");
			  String keyword = input.next();
			  try {
				film = db.getFilmByKeyword(keyword);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			  
			  if (film == null) {
				  System.out.println("Your query did not yield any results, please try again.");
				  choice = 2;
				  continue;
			  }
			  
			  System.out.println("\n" + film.getTitle() + " a(n) " + film.getLanguage()+ " film, released in " + film.getReleaseYear() + " is " + film.getDescription() + "\n\nRated: " + film.getRating() + "\n\nCast:\n");
			  
			  for (Actor a : film.getCast()) {
	  				System.out.println(a);
	  			}
			  
			  break;
			  
		  case 3:
			  
			  System.out.println("K thx bye!");
			  
			  break;
			  
		default:
			System.out.println("\nYou have to enter 1, 2, or 3... I know that must be difficult for you to follow, ya goober.\n");
			
//			startUserInterface(input); <- Calling method from within the method was causing recursion problems where  
//			SQL maintained multiple connections after getting an empty set after a query
			
			break;
		  }
	  }
  }

}
