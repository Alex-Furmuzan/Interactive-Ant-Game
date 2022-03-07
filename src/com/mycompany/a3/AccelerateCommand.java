package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * This class holds the logic when the Accelerate command is invoked.
 * @author Alex Furmuzan
 *
 */
public class AccelerateCommand extends Command{
	
	private GameWorld gw;
	
	public AccelerateCommand(GameWorld gw) {
		super("Accelerate");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.accelerate();
		System.out.println("Accelerate command is invoked");
	}
	
	
	
}
