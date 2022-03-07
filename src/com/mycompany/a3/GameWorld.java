package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.codename1.charts.models.Point;
/**
 * This class initializes the objects in the game world and handles the logic of the game.
 * It extends observable since GameWorld is an observable class by mapview and scoreview.
 * @author Alex Furmuzan
 */

public class GameWorld extends Observable {
	private int currentClockTime;
	private int livesRemaining;
	private int winningFlagNumber;
	private int decreaseFoodTimerCheck;
	private int actualClock;
	private boolean allowPositioning;
	private boolean sound;
	private ArrayList<GameObject> collisionList1;
	private GameCollection theGameCollection;
	
	private Sound collisionSound;
	private Sound crunchSound;
	private Sound flagSound;
	private BGSound bgSound;
	private boolean isPaused;
	
	Ant ant;
	Random random;
	/**
	 * Initializes the Game.
	 */
	public void init() {
		collisionList1 = new ArrayList<GameObject>();
		isPaused = false;
		allowPositioning = false;
		actualClock = 0;
		sound = false;
		livesRemaining = 3;
		currentClockTime = 0;
		decreaseFoodTimerCheck = 0;
		theGameCollection = new GameCollection();
		// singleton ANT
		ant = Ant.getAnt(this);
		theGameCollection.add(ant);

		random = new Random();
		addGameObjects();
		setAntToFirstFlagLocation();
		updateObservers();
	}
	
	
	/**
	 * Prints out the map view in text form.
	 */
	public void showMap() {
		IIterator theElements = theGameCollection.getIterator() ;
		while ( theElements.hasNext() )
		{ 
			GameObject obj = (GameObject) theElements.getNext() ;
			System.out.println ( obj ) ; 
		} 
	}
	/**
	 * Toggles the sound OFF and ON
	 */
	public void soundToggle() {
		sound = !sound;
		System.out.println("Sound is now: " + sound);
		
		updateObservers();
	}
	
	/*
	 * Sets the paused value of the game.
	 */
	public void setPaused(boolean b) {
		isPaused = b;
	}
	
	/**
	 * Gets the paused value of the game.
	 * @return If the game is Paused or not.
	 */
	public boolean getPaused() {
		return isPaused;
	}
	
	/**
	 * Creates the sounds in the game.
	 */
	public void createSounds() {
		collisionSound = new Sound("collision.wav");
		crunchSound = new Sound("crunch.wav");
		flagSound = new Sound("flagsound.wav");
		bgSound = new BGSound("bgm.wav");
	}
	
	/**
	 * Gets the background sound.
	 * @return Background Sound.
	 */
	public BGSound getBGSound() {
		return bgSound;
	}
	/**
	 * tell the game world to accelerate (increase the speed of) the ant by a 1.
	 * Note that the effect of acceleration is to be limited based on health level, food level,
	 * and maximum speed.
	 */
	public void accelerate() {
		
		// if the ants speed is less than its max speed, then we can accelerate.
		if (ant.getSpeed() < ant.getMaxSpeed()) {
			ant.setSpeed(ant.getSpeed()+1);
			System.out.println("Accelerating ant");
		}
		else {
			System.out.println("Reached maximum speed!!");
		}
		
		updateObservers();
	}
	/**
	 * tell the game world to brake (reduce the speed of) the ant by 1.
	 */
	public void brake() {
		System.out.println("Braking..");
		
		//if the ants speed isn't already at 0, then brake.
		if (ant.getSpeed() > 0) {
			ant.setSpeed(ant.getSpeed()-1);
		}
		
		updateObservers();
	}
	/**
	 * tell the game world to change the heading of the ant by 5 degrees to the
	 * left (in the negative direction on the compass).
	 */
	public void leftTurn() {
		System.out.println("Turning Left");
		ant.turnLeft();
		
		updateObservers();
	}
	/**
	 * tell the game world to change the heading of the ant by 5 degrees to the
	 * right (in the positive direction on the compass).
	 */
	public void rightTurn() {
		System.out.println("Turning Right");
		ant.turnRight();
		
		updateObservers();
	}
	/**
	 * PRETEND that the ant has collided with (walked over) the flag
	 * number x (which must have a value between 1-9); tell the game world that this
	 * collision has occurred. The effect of walking over a flag is to check to see whether the
	 * number x is exactly one greater than the flag indicated by lastFlagReached field of
	 * the ant and if so, update the lastFlagReached field of the ant by increasing it by one
	 * 
	 * @param x Specifies which flag to collide with
	 */
	public void collisionFlags(int x) {
		// temp value keeps the next flag in order
		int temp = ant.getLastFlagReached()+1;
		
		// if next flag that needs to be reached == our wanted collision x
		if (temp == x) {
			System.out.println("Collided with flag " + x);
			ant.setLastFlagReached(x);
			
			// if our ants flag == the winningFlagNumber then we win the game.
			if (ant.getLastFlagReached() == winningFlagNumber) {
				System.out.println("Game Over, You win! Total Time: " + actualClock);
				System.exit(0);
			}
		}
		else if (x == ant.getLastFlagReached()) {
			System.out.println("You are at this flag already!");
		}
		else {
			System.out.println("Wrong order!");
		}
		
		updateObservers();
	}
	/**
	 * PRETEND that the ant has collided with a food station; tell the game world that this
     * collision has occurred. The effect of colliding with a food station is to increase the
     * ants food level by the capacity of the food station, reduce the capacity of the food station to
     * zero, fade the color of the food station, and add a new food station with randomly-specified size and location into the game. 
     * Also adds 1 health point for colliding with a food station.
	 */
	public void collisionFoodStation(FoodStation theStation) {
		
		IIterator it = theGameCollection.getIterator();
		while(it.hasNext()) {
			GameObject fs = (GameObject) it.getNext();
			
			if (fs == theStation) {
				//if this foodstations capacity > 0, then we can collide with it and break out of the loop.
				if (((FoodStation) fs).getCapacity() > 0) {
					System.out.println("Collided with FoodStation and gained " + ((FoodStation) fs).getCapacity() + " Food Energy!");
					if(ant.getHealthLevel() < ant.getMaxHealthLevel()) {
						ant.setHealthLevel(ant.getHealthLevel()+1);
						System.out.println("You have gained 1 HP!");
						ant.setMaximumSpeedBasedOnHealth();
					}
					ant.setFoodLevel(ant.getFoodLevel() + ((FoodStation) fs).getCapacity());
					((FoodStation) fs).setCapacity(0);
					((FoodStation) fs).setColor(205, 255, 201); // light green
					theGameCollection.add(new FoodStation());
					break;
				}
			}
			
		}	
		updateObservers();
	}
	/**
	 * PRETEND that a spider has gotten to the same location and collided with the ant.
	 * The effect of colliding with a spider is to decrease the health level of the ant,
	 * fade the color of the ant from black to gray, and (if necessary) reduce the
	 * speed of the ant so that speed-limitation rule is enforced. Since
	 * currently no change is introduced to the spider after the collision, it does not matter
	 * which spider is picked. 
	 */
	public void collisionSpider() {
		System.out.println("Collided with Spider");
		ant.setColor(105, 105, 105); //FADES BLACK TO GRAY
		ant.decreaseHealthLevel();
		
		if (ant.getHealthLevel() <= 0) {
			ant.setHealthLevel(0);
			ant.setMaximumSpeedBasedOnHealth();
			ant.setSpeed(0);
			decreaseLivesRemaining();
			ant.resetAnt();
			System.out.println("You have died, you have " + livesRemaining + " Lives left");
		}
		if (livesRemaining <= 0) {
			System.out.println("Game over, you failed!");
			System.exit(0);
		}
		if(ant.getSpeed() <= 0) {
			ant.setSpeed(0);
		}
		updateObservers();
	}
	/**
	 * Tell the game world that the game clock has ticked. A clock tick in the game world
	 * has the following effects: 
	 * (1) Spiders update their heading as indicated above. 
	 * (2) all moveable objects are told to update their positions according to their current heading and speed, and 
	 * (3) the ants food level is reduced by the amount indicated by its foodConsumptionRate,
	 * (4) the elapsed time game clock is incremented by one (the
	 * game clock for this assignment is simply a variable which increments by one with each
	 * tick). Note that all commands take immediate effect and not depend on t command
	 * (e.g., if a is hit, the ants speed value would be increased right away without waiting for
	 * the next t command to be entered). 
	 */
	public void clockTick() {
		currentClockTime++;
		actualClock = currentClockTime / 50;
		
		if (livesRemaining <= 0) {
			System.out.println("Game over, you failed!");
			System.exit(0);
		}
		IIterator it = theGameCollection.getIterator();
		IIterator it2 = theGameCollection.getIterator();
		while(it.hasNext()) {
			GameObject temp = (GameObject) it.getNext();
			while(it2.hasNext()) {
				GameObject otherObject = (GameObject) it2.getNext();
				if (temp instanceof Ant) {
					((Ant) temp).move(20);

					if(temp != otherObject) {
						if  (((Ant) temp).collidesWith(otherObject)){
							if(!collisionList1.contains(otherObject)) {
								((Ant) temp).handleCollision(otherObject);
								if(!collisionList1.contains(otherObject)) {
									collisionList1.add(otherObject);
								}
							}
						}
						else {
							collisionList1.remove(otherObject);
						}
						
					}
					if(actualClock == decreaseFoodTimerCheck ) {
						((Ant) temp).consumeFood();
						decreaseFoodTimerCheck++;
					}
					if (((Ant) temp).getHealthLevel() <= 0 || ((Ant) temp).getFoodLevel() <= 0) {
						decreaseLivesRemaining();
						((Ant) temp).resetAnt();
						System.out.println("You have died, you have " + livesRemaining + " Lives left");
					}
			}
			
			}
			if (temp instanceof Spider) {
				((Spider) temp).move(20);
			}
			
		}
		updateObservers();
	}
	/**
	 * Generate a display by outputting lines of text on the console describing the current
	 * game/ant state values. The display should include (1) the number of lives left, (2) the
	 * current clock value (elapsed time), (3) the highest flag number the ant has reached
	 * sequentially so far (i.e., lastFlagReached), (4) the ants current food level (i.e.,
	 * foodLevel), and (5) the ants health level (i.e., healthLevel).
	 */
	public void displayGameState() {
		System.out.println("____________________________________________________________________________\n");
		System.out.println("Lives left: " + livesRemaining);
		System.out.println("Time: " + currentClockTime);
		System.out.println("Highest Flag Reached: " + ant.getLastFlagReached());
		System.out.println("Current Food Level: " + ant.getFoodLevel());
		System.out.println("Health Level: " + ant.getHealthLevel());
		System.out.println("____________________________________________________________________________");
	}
	/**
	 * Sets the Ants location to the First flags location.
	 */
	public void setAntToFirstFlagLocation() {
		IIterator it = theGameCollection.getIterator();
		while(it.hasNext()) {
			GameObject temp = (GameObject) it.getNext();
			if(temp instanceof Flag) {
				ant.setLocation(((Flag)temp).getLocation());
				break;
			}
		}
		updateObservers();
	}
	/**
	 * Populates my ArrayList of GameObjects and Instantiates them.
	 */
	public void addGameObjects() {
		
		// add flags
		for (int i = 0; i < 4; i++) {
			theGameCollection.add(new Flag(i+1));
			winningFlagNumber = i+1;
		}
		
		//add spiders
		for (int i = 0; i < 3; i++) {
			theGameCollection.add(new Spider());
		}
		
		//add Food Stations
		for (int i = 0; i < 3; i++) {
			theGameCollection.add(new FoodStation());
		}
		updateObservers();
	}
	/**
	 * Gets the games lives remaining.
	 * 
	 * @return livesRemaining 
	 */
	public int getLivesRemaining() {
		return livesRemaining;
	}
	/**
	 * Gets the current clock time.
	 * 
	 * @return currentClockTime
	 */
	public int getCurrentClockTime() {
		return actualClock;
	}
	/**
	 * returns the game collection
	 * 
	 * @return theGameCollection
	 */
	public GameCollection getCollection() {
		return theGameCollection;
	}
	/**
	 * Returns last flag reached
	 * 
	 * @return last flag reached
	 */
	public int getLastFlag() {
		return ant.getLastFlagReached();
	}
	/**
	 * Returns ants food level
	 * 
	 * @return Ants food level
	 */
	public int getFood() {
		return ant.getFoodLevel();
	}
	/**
	 * Returns ands current health level
	 * 
	 * @return Ants health level
	 */
	public int getHealth() {
		return ant.getHealthLevel();
	}
	/**
	 * Gets the sound
	 * 
	 * @return Sound
	 */
	public boolean getSound() {
		return sound;
	}
	/**
	 * Decreases Lives Remaining.
	 */
	public void decreaseLivesRemaining() {
		if(livesRemaining <= 0) {
			return;
		}
		livesRemaining--;
		
		updateObservers();
	}
	/**
	 * Sets the clock time to v.
	 * 
	 * @param v Clock time to set to.
	 */
	public void setCurrentClockTime(int v) {
		currentClockTime = v;
		updateObservers();
	}
	/**
	 * Notifies the observers.
	 */
	private void updateObservers() {
		setChanged();
		notifyObservers(this);
	}


	public void positionObject(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		IIterator it = theGameCollection.getIterator();
		while (it.hasNext()) {
			GameObject temp = it.getNext();
			if(temp instanceof ISelectable && ((ISelectable) temp).isSelected() && allowPositioning == true) {
				int newX = (int) (pPtrRelPrnt.getX() - pCmpRelPrnt.getX());
				int newY = (int) (pPtrRelPrnt.getY() - pCmpRelPrnt.getY());
				Point newLoc = new Point(newX, newY);
				temp.setLocation(newLoc);
				((ISelectable) temp).setSelected(false);
			}
			else {
				if(temp instanceof ISelectable && ((ISelectable) temp).contains(pPtrRelPrnt, pCmpRelPrnt)) {
					((ISelectable) temp).setSelected(true);
				}
				else if(temp instanceof ISelectable) {
					((ISelectable) temp).setSelected(false);
				}
			}
		}
		allowPositioning = false;
		updateObservers();
	}

/**
 * Toggles whether or not to allow positioning.
 */
	public void allowPositioning() {
		allowPositioning = true;
	}
	/**
	 * Returns if positioning is allowed.
	 * @return Positioning value.
	 */
	public boolean isPositioningAllowed() {
		return allowPositioning;
	}
}
