package com.mycompany.a3;
/**
 * This interface is used by the GameIterator class in GameCOllection to make sure it has
 * the ability to loop through the indexes of a game collection.
 * @author Alex Furmuzan
 *
 */
public interface IIterator {

	/**
	 * Checks to see if there is more items to be processed
	 * 
	 * @return true if there is an object to be processed, otherwise false
	 */
	public boolean hasNext();
	/**
	 * Gets the next object
	 * 
	 * @return Returns the next object
	 */
	public GameObject getNext();
	
	
}
