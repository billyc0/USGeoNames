<?php

	/**
		US Geographic Name Search Application
		
		usgeonames.php
		Author: Billy Chandler
		Version: 2017-09-09
		
		Description:
		This application allows the user to search for names of natural and man-made
			places within the United States
			
		Developer websites:
			http://www.billychandler.org
			http://www.github.com/billyc0
			
		Github repository:
			https://github.com/billyc0/USGeoNames
	**/
	
	// This php files creates JSON to be parsed by the Java application
	
	// Creates a new array for storing feature names
	$featureNames = array();
	
	// Retrieves the XML file from the USGS Geographic Names Information System
	$xml = simplexml_load_file("https://geonames.usgs.gov/apex/gazvector.x?fname=&state=%27" . 
		urlencode($_GET['state']) . "%27&cnty=%27" . 
		urlencode($_GET['county']) . "%27&cell=&ftype=%27" . 
		urlencode($_GET['type']) . "%27") 
		or die("ERROR: Cannot create object");
	
	// Parses each feature into the $featureNames array
	foreach ($xml->USGS as $feature) {
		array_push($featureNames, $feature->FEATURE_NAME);
	}
	
	// If the array is empty, this will print
	if (empty($featureNames)) {
		array_push($featureNames, "No search results found");
	}
	
	// Sorts the array items in alphabetical order
	sort($featureNames, SORT_NATURAL | SORT_FLAG_CASE);
	
	echo "{ \"features\": [ ";
	
	$num = count($featureNames); // counts the array items
	$it = 0; // loop iterator
	
	// Prints JSON data
	foreach ($featureNames as $feature) {
		$it++;
		if ($it === $num)
			echo "{ \"name\": \"" . $feature . "\" }";
		else
			echo "{ \"name\": \"" . $feature . "\" }, ";
	}
	
	echo " ] }";
?>