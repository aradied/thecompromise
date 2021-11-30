package application.model;
/**
 * This class represents a person by holding the name and age of a person.
 * 
 * UTSA CS 3443
 * Fall 2021
 */

public class Person {

 private String name;
 private String place;
 
 // Constructor gets the name and age of a person and initializes its respective variables
 public Person(String name, String place) {
  
  this.name = name;
  this.place = place;
 }

 public String getName() {
  return name;
 }

 // The Getters and Setter functions for the name and age variables
 
 public void setName(String name) {
  this.name = name;
 }

public String getPlace() {
	return place;
}

public void setPlace(String place) {
	this.place = place;
} 

}