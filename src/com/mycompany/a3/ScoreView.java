package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 * This class includes the display of the scoreview and styles the top scoreview UI.
 * It updates the observable GameWorld by showing the updated scoreview values..
 * @author Alex Furmuzan
 *
 */
public class ScoreView extends Container implements Observer{

	private Label timeValue;
	private Label livesLeftValue;
	private Label lastFlagReachedValue;
	private Label foodLevelValue;
	private Label healthLevelValue;
	private Label soundValue;
	
	
	
	/**
	 * Constructor for the Score view
	 */
	public ScoreView() {
		this.setLayout(new BoxLayout(BoxLayout.X_AXIS));

		timeValue = addLabel("Time:", timeValue);
		livesLeftValue = addLabel("Lives Left:", livesLeftValue);
		lastFlagReachedValue = addLabel("Last Flag Reached:", lastFlagReachedValue);
		foodLevelValue = addLabel("Food Level:", foodLevelValue);
		healthLevelValue = addLabel("Health Level:", healthLevelValue);
		soundValue = addLabel("Sound:", soundValue);
	}
	/**
	 * This will add a label with a specified name and value
	 * 
	 * @param labelName
	 * @param value
	 * @return Returns the value label
	 */
	private Label addLabel(String labelName, Label value) {
		Label theLabel = new Label(labelName);
     	if(labelName.equals("Time:")) {
			theLabel.getAllStyles().setMargin(LEFT, 250);
		}
		theLabel = styleLabel(theLabel);
		value = new Label("0");
		value = styleLabel(value);
		this.add(theLabel);
		this.add(value);
		return value;
	}
	/**
	 * Styles the button and returns it back
	 * 
	 * @param myLabel
	 * @return Returns myLabel that is now styled
	 */
	private Label styleLabel(Label myLabel) {
		myLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		if(myLabel.getText().equals("0")) {
			myLabel.getAllStyles().setPadding(RIGHT, 2);
			
		}
		return myLabel;
	}
	/**
	 * Update method to be called when notify observers is called
	 */
	@Override
	public void update(Observable observable, Object data) {
		this.timeValue.setText(((GameWorld) data).getCurrentClockTime()+ "   ");
		this.livesLeftValue.setText(((GameWorld) data).getLivesRemaining()+"   ");
		this.lastFlagReachedValue.setText(((GameWorld) data).getLastFlag()+"   ");
		this.foodLevelValue.setText(((GameWorld) data).getFood()+"   ");
		this.healthLevelValue.setText(((GameWorld) data).getHealth()+"   ");

    	if(((GameWorld) data).getSound() == true) {
			this.soundValue.setText("ON");
		}
		else {
			this.soundValue.setText("OFF");
		}
		this.repaint(); 
	}
}


