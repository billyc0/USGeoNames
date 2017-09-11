/**
	US Geographic Name Search Application
	
	USGeoNames.java
	@author Billy Chandler
	@version 2017-09-08
	
	Description:
	This application allows the user to search for names of natural and man-made
		places within the United States
		
	Developer websites:
		http://www.billychandler.org
		http://www.github.com/billyc0
		
	Github repository:
		https://github.com/billyc0/USGeoNames
**/

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

public class USGeoNames {
	
	private boolean quit; // Used for the application loop
	private int option; // Menu option chosen by the user
	
	public USGeoNames() {
		quit = false; // Declares the application loop boolean
	}
	
	public void menu() throws MalformedURLException, IOException {
		
		// Application loop
		while (!quit) {	
			// Creates scanner object
			Scanner scan = new Scanner(System.in);
			
			// Creates a header for the main menu
			this.createHeader("US GEOGRAPHIC NAME SEARCH APPLICATION");
			
			System.out.println("Please go to the \"help / important info\" menu " +
				"before using this application for the first time.");
			
			// Application menu
			System.out.println("\nSelect an option:");
			System.out.println("\t(1) Search for features");
			System.out.println("\t(2) List of feature types");
			System.out.println("\t(3) Help / Important info");
			System.out.println("\t(4) Quit");
			
			// Asks a user to select an option from the menu
			System.out.print("\nYour option (1-4): ");
			option = scan.nextInt();
			
			// Triggers a menu item based on user input
			switch (option) {
				case 1:
					this.search();
					break;
				case 2:
					this.featureList();
					break;
				case 3:
					this.help();
					break;
				case 4:
					System.out.println("\nThank you for using this application!");
					scan.close();
					quit = true;
					break;
				default:
					System.out.println("\nERROR: Invalid input!");
					break;
			}
			
		}
	}
	
	
	// Searches for the names of geographic features
	public void search() throws MalformedURLException, IOException {
		// Declares a new scanner
		Scanner scan = new Scanner(System.in);
		
		String featureType, state, county, save, file, fileHeader; // Used for user input
		boolean saveFile = false; // Used for the file saving loop
		
		// Creates a header for searching
		this.createHeader("SEARCH FOR FEATURES");
		
		// User enters a feature type
		System.out.print("Enter a feature type: ");
		featureType = scan.nextLine();
		
		// User enters the name of a state or territory
		System.out.print("\nEnter a US state or territory name: ");
		state = scan.nextLine();
		
		// User enters the name of a county or county equivalent
		System.out.print("\nEnter a county or county equivalent name: ");
		county = scan.nextLine();
		
		// Makes the user input all lowercase letters
		featureType = featureType.toLowerCase();
		state = state.toLowerCase();
		county = county.toLowerCase();
		
		// Creates an object that searches for GNIS data
		USGeoNamesSearch search = new USGeoNamesSearch(featureType, state, county);
		// Searches the GNIS database based on user input
		search.search();
		
		// Creates new line
		System.out.println();
		
		// Checks if search results are found
		if (search.toString().equals("No search results found")) {
			// Prints a message explaining that no results were found
			search.print();
		}
		else {
			// Prints search results
			System.out.println("Search Results:");
			search.print();
			
			// File saving loop
			while (!saveFile) {
				// Asks user if they would like to save file
				System.out.print("\nWould you like to save your results? (y/n) ");
				save = scan.nextLine();
				
				// Checks user input
				if (save.equals("Y") || save.equals("y")) {
					// Asks user to enter the name for the file they are saving
					System.out.print("\nEnter the name of the file you are saving: ");
					file = scan.nextLine();
					
					// Asks the user to enter a header for the file
					System.out.println("\nEnter the header for your file:");
					fileHeader = scan.nextLine();
					
					fileHeader += "\r\n\r\n";
					
					// Saves the file
					search.save(file, fileHeader);
					
					saveFile = true; // ends the loop
				}
				else if (save.equals("N") || save.equals("n")) {
					saveFile = true; // ends the loop
				}
				else {
					// Tells the user their input was invalid
					System.out.println("\nError: Invalid input!");
				}
			}
		}
		
	}
	
	// Prints the list of feature types
	public void featureList() {
		this.createHeader("LIST OF FEATURE CLASSES");
		System.out.println("List & details of each feature can be found at:");
		System.out.println("https://geonames.usgs.gov/apex/f?p=gnispq:8");
		System.out.println("\nairport");
		System.out.println("arch");
		System.out.println("area");
		System.out.println("arroyo");
		System.out.println("bar");
		System.out.println("basin");
		System.out.println("bay");
		System.out.println("beach");
		System.out.println("bench");
		System.out.println("bend");
		System.out.println("bridge");
		System.out.println("building");
		System.out.println("canal");
		System.out.println("cape");
		System.out.println("cave");
		System.out.println("cemetery");
		System.out.println("census");
		System.out.println("channel");
		System.out.println("church");
		System.out.println("civil");
		System.out.println("cliff");
		System.out.println("crater");
		System.out.println("crossing");
		System.out.println("dam");
		System.out.println("falls");
		System.out.println("flat");
		System.out.println("forest");
		System.out.println("gap");
		System.out.println("glacier");
		System.out.println("gut");
		System.out.println("harbor");
		System.out.println("hospital");
		System.out.println("island");
		System.out.println("isthmus");
		System.out.println("lake");
		System.out.println("lava");
		System.out.println("levee");
		System.out.println("locale");
		System.out.println("military");
		System.out.println("mine");
		System.out.println("oilfield");
		System.out.println("park");
		System.out.println("pillar");
		System.out.println("plain");
		System.out.println("populated place");
		System.out.println("post office");
		System.out.println("range");
		System.out.println("rapids");
		System.out.println("reserve");
		System.out.println("reservoir");
		System.out.println("ridge");
		System.out.println("school");
		System.out.println("sea");
		System.out.println("slope");
		System.out.println("spring");
		System.out.println("stream");
		System.out.println("summit");
		System.out.println("swamp");
		System.out.println("tower");
		System.out.println("trail");
		System.out.println("tunnel");
		System.out.println("unknown");
		System.out.println("valley");
		System.out.println("well");
		System.out.println("woods");
	}
	
	// Gives the user important information about using this application
	public void help() {
		this.createHeader("HELP / IMPORTANT INFORMATION");
		
		System.out.println("README file can be found at " +
				"https://github.com/billyc0/USGeoNames/blob/master/README.md");
		
		System.out.println("\nNotes:");
		System.out.println("\tWhen writing the name for an independent city, please " +
				"write \" (city)\" after its name when asked for a county.");
		System.out.println("\tWhen writing the name for a census area, please write \" (CA)\" " + 
				"after its name when asked for a county.");
		System.out.println("\tTo look at the features located within the District of Columbia, please " + 
				"write \"District of Columbia\" when asked for a county.");
	}
	
	// Creates a header
	public void createHeader(String title) {
		System.out.println("\n" + title);
		for (int i = 0; i <=25; i++) System.out.print("-");
		System.out.println("\n");
	}
}