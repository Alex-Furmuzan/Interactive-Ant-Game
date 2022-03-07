package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
/**
 * This class holds the logic when the Brake command is invoked.
 * @author Alex Furmuzan
 *
 */
public class BrakeCommand extends Command{
	
	private GameWorld gw;
	
	public BrakeCommand(GameWorld gw) {
		super("Brake");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.brake();
		System.out.println("Brake command is invoked");
	}

}
