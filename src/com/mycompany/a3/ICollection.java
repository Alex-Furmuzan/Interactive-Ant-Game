package com.mycompany.a3;
/**
 * This interface is used by the GameCollection class to make sure it has
 * the ability to add a game object to the collection and return an iterator.
 * @author Alex Furmuzan
 *
 */
public interface ICollection {
	/**
	 * Adds the object to the game collection
	 * 
	 * @param newObject to be added
	 */
	public void add(GameObject newObject);
	/**
	 * Gets the Iterator for the collection
	 * 
	 * @return returns the Iterator
	 */
	public IIterator getIterator();
}
