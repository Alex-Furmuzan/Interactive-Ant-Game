package com.mycompany.a3;

import java.util.ArrayList;

/**
 * This is the game collection class which implements Icollection. This class
 * holds the arraylist for the objects and provides Iterator logic to follow
 * the iterator design pattern.
 * @author Alex Furmuzan
 *
 */
public class GameCollection implements ICollection {

	private ArrayList<GameObject> theCollection;
	
	/**
	 * Constructor for GameCollection
	 */
	public GameCollection() {
		theCollection = new ArrayList<GameObject>();
	}
	
	/**
	 * Adds an object to the Game Collection
	 */
	public void add(GameObject newObject) {
		theCollection.add(newObject);
	}
	/**
	 * @return Returns the collection
	 */
	public ArrayList<GameObject> getObjects(){
		return theCollection;
	}
	
	/**
	 * Gets a new Iterator 
	 */
	public IIterator getIterator() {
		return new GameIterator();
	}
	
	
/**
 * GameIterator class to handle iteration
 * 
 * @author Alex Furmuzan
 *
 */
private class GameIterator implements IIterator{

		private int currElementIndex;
		/**
		 * Constructor for the Game Iterator
		 */
		public GameIterator() {
			currElementIndex = -1;
		}	
		/**
		 * Returns true if the Game Collection has a next object, otherwise false
		 */
	
		public boolean hasNext() {
			if(theCollection.size() <= 0) return false;
			if(currElementIndex == theCollection.size()-1) {
				return false;
			}
			return true;
		}

		/**
		 * Gets the next object in the Game Collection
		 */

		public GameObject getNext() {
			currElementIndex++;
			return(theCollection.get(currElementIndex));
		}
		
		
	} 
} 


