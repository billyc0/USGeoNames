import java.io.IOException;
import java.net.MalformedURLException;

/**
	US Geographic Name Search Application
	
	USGeoNamesApp.java
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

public class USGeoNamesApp {
	public static void main(String[] args) throws MalformedURLException, IOException {
		// Creates new application object
		USGeoNames app = new USGeoNames();
		// Opens the application menu
		app.menu();
	}
}