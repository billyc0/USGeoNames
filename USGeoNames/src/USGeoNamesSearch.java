/**
	US Geographic Name Search Application
	
	USGeoNamesSearch.java
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

import java.net.*;
import java.io.*;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.json.simple.*;

public class USGeoNamesSearch {
	
	// Strings used for user input and JSON parsing
	private String featureType, state, county, results;
	
	// Declares each string based on user input
	public USGeoNamesSearch(String _featureType, String _state, String _county) {
		featureType = _featureType;
		state = _state;
		county = _county;
		results = "";
	}
	
	// Parses JSON data from the GNIS database
	public void search() throws MalformedURLException, IOException {
		// URL of the JSON needing to be parsed
		String url = "http://www.billychandler.org/projects/usgeonames.php?state=" + 
			state + "&county=" + county + "&type=" + featureType;
		
		// Replaces spaces with "%20"
		url = url.replaceAll(" ", "%20");
		
		// Parses the JSON into a string and JSON object
		String json = IOUtils.toString(new URL(url));
		JSONObject obj = (JSONObject) JSONValue.parse(json);
		
		// Creates a JSON array of the data
		JSONArray features = (JSONArray) (obj.get("features"));
		
		// Adds each feature item into a string
		for (int i=0;i<features.size();i++) {
			JSONObject f = (JSONObject) features.get(i);
			if (i == features.size() -1)
				results += f.get("name").toString();
			else
				results += f.get("name").toString() + "\r\n";
		}
		
	}
	
	// Returns the string of search results
	public String toString() {
		return results;
	}
	
	// Prints the string of search results
	public void print() {
		System.out.println(this.toString());
	}
	
	// Saves the search results into a .txt file
	public void save(String fileName, String fileHeader) {
		try {
			// Creates file writing objects
			FileWriter out = new FileWriter("savedfiles/" + fileName + ".txt");
			BufferedWriter point = new BufferedWriter(out);
			
			// Writes the file and closes the pointer
			point.write(fileHeader + this.toString());
			point.close();
		} catch (Exception e) {
			// Prints error message if there is an error in saving the file
			System.err.println("Error: " + e.getMessage());
		}
	}
	
}